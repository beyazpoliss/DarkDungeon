package tr.beyazpolis.dungeon;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import java.lang.reflect.Field;
import java.util.Random;
import java.util.UUID;
import net.minecraft.server.v1_12_R1.EntityInsentient;
import net.minecraft.server.v1_12_R1.EntityLiving;
import net.minecraft.server.v1_12_R1.PacketPlayOutSpawnEntityLiving;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import tr.beyazpolis.common.DungeonPlugin;
import tr.beyazpolis.config.Config;
import tr.beyazpolis.config.ConfigRunner;
import tr.beyazpolis.dungeon.command.BukkitDungeonCommand;
import tr.beyazpolis.dungeon.config.ConfigAdapterBukkit;
import tr.beyazpolis.dungeon.config.ConfigBukkit;
import tr.beyazpolis.dungeon.entity.BukkitEntity;
import tr.beyazpolis.dungeon.teams.DungeonTeam;
import tr.beyazpolis.dungeon.world.BukkitPlayer;
import tr.beyazpolis.dungeon.world.BukkitWorld;
import tr.beyazpolis.entity.DungeonEntity;
import tr.beyazpolis.profile.player.DungeonPlayer;
import tr.beyazpolis.world.LocationWrapper;
import tr.beyazpolis.world.MethodAdapter;
import tr.beyazpolis.world.PlayerWrapper;
import tr.beyazpolis.world.WorldWrapper;

public class BukkitPlugin extends DungeonPlugin {

  private final JavaPlugin plugin;
  private final BukkitMethodAdapter methodAdapter;
  private final WorldWrapper worldWrapper;
  private final ProtocolManager protocolManager;

  public BukkitPlugin(final JavaPlugin plugin) {
    this.plugin = plugin;
    this.methodAdapter = new BukkitMethodAdapter(this);
    this.worldWrapper = new BukkitWorld(methodAdapter);
    this.protocolManager = ProtocolLibrary.getProtocolManager();
  }

  @NotNull
  @Override
  public MethodAdapter getMethodAdapter() {
    return methodAdapter;
  }

  @Override
  public WorldWrapper getWorldWrapper() {
    return worldWrapper;
  }

  @Override
  public Plugin getPluginObject() {
    return plugin;
  }

  @Override
  public void addPacketListener() {
    protocolManager.
      addPacketListener(new PacketAdapter(plugin, ListenerPriority.NORMAL,
        PacketType.Play.Server.SPAWN_ENTITY_LIVING) {
        @Override
        public void onPacketSending(PacketEvent event) {
          if (event.getPacketType() != PacketType.Play.Server.SPAWN_ENTITY_LIVING) return;
          if (!(event.getPlayer().getWorld().getName().equalsIgnoreCase(getWorldWrapper().dungeonWorld()))) return;
          final PacketPlayOutSpawnEntityLiving entityLiving = (PacketPlayOutSpawnEntityLiving) event.getPacket().getHandle();
          try {
            Field[] field = entityLiving.getClass().getDeclaredFields();
            field[1].setAccessible(true);
            final UUID id = (UUID) field[1].get(entityLiving);
            final DungeonTeam team = DungeonManager.getEntityLivingByTeam(id);
            if (team == null) return;
            if (!team.hasPlayerIsTeam(event.getPlayer().getName())) {
              event.setCancelled(true);
            }
          } catch (IllegalAccessException e) {
            System.out.println("exception!!");
          }
        }
      });
  }

  @Override
  public Object getProtocolManager() {
    return protocolManager;
  }

  @Override
  public Config createConfig(final String name, final ConfigRunner configRunner) {
    return new ConfigBukkit(new ConfigAdapterBukkit(this.plugin.getDataFolder().getPath(),name)) {
      @Override
      public void start() {
        configRunner.start();
      }
    };
  }

  @Override
  public DungeonEntity createEntity(final int entityType, final String entityCustomName,final int maxHealth, final int attackDamage) {
    return new BukkitEntity(entityType,entityCustomName,maxHealth,attackDamage);
  }

  @Override
  public PlayerWrapper createPlayer(final String name, final UUID uuid, final LocationWrapper locationWrapper) {
    return new BukkitPlayer(name,uuid,locationWrapper);
  }

  @Override
  public void registerEvents() {
    Bukkit.getPluginManager().registerEvents(new Listener() {
      @EventHandler
      public void onDeathEntity(EntityDeathEvent event){
        final DungeonTeam dungeonTeam = DungeonManager.getEntityLivingByTeam(event.getEntity().getUniqueId());
        if (dungeonTeam == null) return;
        dungeonTeam.addCurrentKilledMob();
        if (dungeonTeam.isNextRoom()){
          methodAdapter.startRoomTimer(dungeonTeam);
        }
        event.getDrops().forEach(stack -> stack.setType(Material.AIR));
      }

      @EventHandler
      public void onDeathPlayer(EntityDeathEvent event){
        if((event.getEntity() instanceof Player)){
          final Player player = (Player) event.getEntity();
          final DungeonPlayer dungeonPlayer = DungeonPlayer.of(player.getName(),player.getUniqueId());
          final DungeonTeam dungeonTeam = DungeonManager.of(dungeonPlayer);
          if (dungeonTeam == null) return;
          getMethodAdapter().sendMessage(dungeonPlayer.name(),dungeonTeam.currentRoom().getRoomMessages().deathMessage());
          if (dungeonTeam.players().size() == 1){
            dungeonTeam.removeTeam(dungeonPlayer);
            dungeonTeam.currentRoom().entities().forEach((integer, dungeonEntity) -> {
              final CraftEntity craftEntity = (CraftEntity) Bukkit.getEntity(dungeonEntity.entityId());
              craftEntity.getHandle().killEntity();
            });
          } else {
            dungeonTeam.removePlayer(dungeonPlayer);
          }
        }
      }
      
      
      @EventHandler
      public void onTarget(EntityTargetLivingEntityEvent event){
        if (!(event.getEntity().getWorld().getName().equalsIgnoreCase(getWorldWrapper().dungeonWorld()))) return;
        final DungeonTeam dungeonTeam = DungeonManager.getEntityLivingByTeam(event.getEntity().getUniqueId());
        if (dungeonTeam == null) return;
        if (event.getTarget() instanceof Player){
          final Player player = (Player) event.getTarget();
          if (!dungeonTeam.hasPlayerIsTeam(player.getName())) {
            event.setCancelled(true);
            final Random random = new Random();
            final DungeonPlayer randomPlayer = dungeonTeam.players().get(random.nextInt(dungeonTeam.players().size()));
            if (Bukkit.getEntity(randomPlayer.getUUID()) == null) return;
            event.setTarget(Bukkit.getEntity(randomPlayer.getUUID()));
          }
        }
      }

      @EventHandler
      public void onDamageEntity(EntityDamageByEntityEvent event){
        if (!(event.getDamager().getWorld().getName().equalsIgnoreCase(getWorldWrapper().dungeonWorld()))) return;

        final DungeonTeam dungeonTeam = DungeonManager.getEntityLivingByTeam(event.getDamager().getUniqueId());
        if (dungeonTeam == null) return;

        if (!(event.getEntity() instanceof Player)) return;
        final Player player = (Player) event.getEntity();

        if (!dungeonTeam.hasPlayerIsTeam(player.getName())){
          event.setCancelled(true);
          final Random random = new Random();
          final DungeonPlayer randomPlayer = dungeonTeam.players().get(random.nextInt(dungeonTeam.players().size()));
          if (event.getDamager() instanceof EntityInsentient){
            final EntityInsentient damager = (EntityInsentient) event.getDamager();
            damager.setGoalTarget((EntityLiving) Bukkit.getEntity(randomPlayer.getUUID()));
          }
        }
      }

      @EventHandler
      public void onDamagePlayer(EntityDamageByEntityEvent event){
        if (!(event.getDamager() instanceof  Player)) return;
        final Player player = (Player) event.getDamager();
        if (!(player.getWorld().getName().equalsIgnoreCase(getWorldWrapper().dungeonWorld()))) return;
        final Entity livingEntity = event.getEntity();

        final DungeonTeam dungeonTeam = DungeonManager.getEntityLivingByTeam(livingEntity.getUniqueId());
        if (dungeonTeam == null) return;

        if (!(dungeonTeam.hasPlayerIsTeam(player.getName()))){
          event.setCancelled(true);
        }
      }

      @EventHandler
      public void onShootPlayer(EntityShootBowEvent event){
        if (!(event.getEntity() instanceof  Player)) return;
        final Player player = (Player) event.getEntity();
        if (!(player.getWorld().getName().equalsIgnoreCase(getWorldWrapper().dungeonWorld()))) return;
        final Entity livingEntity = event.getProjectile();

        final DungeonTeam dungeonTeam = DungeonManager.getEntityLivingByTeam(livingEntity.getUniqueId());
        if (dungeonTeam == null) return;

        if (!(dungeonTeam.hasPlayerIsTeam(player.getName()))){
          event.setCancelled(true);
        }
      }
    },plugin);
  }

  @Override
  public void registerCommand() {
    this.plugin.getCommand("dungeon").setExecutor(new BukkitDungeonCommand(this));
  }
}
