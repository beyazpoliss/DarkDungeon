package tr.beyazpolis.dungeon.world;

import net.minecraft.server.v1_12_R1.EntitySkeleton;
import net.minecraft.server.v1_12_R1.EntitySkeletonWither;
import net.minecraft.server.v1_12_R1.EntityZombie;
import net.minecraft.server.v1_12_R1.World;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import tr.beyazpolis.common.CommonPlugin;
import tr.beyazpolis.dungeon.entity.BukkitLivingEntity;
import tr.beyazpolis.dungeon.entity.impl.DungeonEntityZombie;
import tr.beyazpolis.dungeon.entity.impl.DungeonSkeleton;
import tr.beyazpolis.dungeon.entity.impl.DungeonWitherSkeleton;
import tr.beyazpolis.dungeon.teams.DungeonTeam;
import tr.beyazpolis.entity.DungeonEntity;
import tr.beyazpolis.entity.DungeonLivingEntity;
import tr.beyazpolis.world.LocationWrapper;
import tr.beyazpolis.world.MethodAdapter;
import tr.beyazpolis.world.PlayerWrapper;
import tr.beyazpolis.world.WorldWrapper;

public class BukkitWorld extends WorldWrapper {

  private String staticWorldName;

  public BukkitWorld(final MethodAdapter methodAdapter) {
    super(methodAdapter);
    this.staticWorldName = null;
  }

  public String getStaticWorldName() {
    return staticWorldName;
  }

  @Override
  public String dungeonWorld() {
    return staticWorldName;
  }

  @Override
  public void setDungeonWorldName(final String name) {
    this.staticWorldName = name;
  }

  @Override
  public CommonPlugin getPlugin() {
    return getMethodAdapter().getPlugin();
  }

  @Override
  public World getWorld(@NotNull final String worldName) {
    return this.getCraftWorld(worldName).getHandle();
  }

  @Override
  public CraftWorld getCraftWorld(@NotNull final String worldName) {
    return (CraftWorld) getMethodAdapter().getWorld(worldName);
  }

  @Override
  public void teleportPlayer(@NotNull final String playerName, @NotNull final LocationWrapper wrapper) {
    getMethodAdapter().teleportPlayer(playerName,wrapper);
  }

  @Override
  public void spawnEntitiesTeam(@NotNull final DungeonEntity dungeonEntity,
                                @NotNull final DungeonTeam dungeonTeam,
                                @NotNull final LocationWrapper spawnLocation) {
    final DungeonLivingEntity livingEntity = createEntity(dungeonEntity,spawnLocation.getWorldName());
    Bukkit.getScheduler().runTask((Plugin) this.getPlugin().getPluginObject(), new Runnable() {
      @Override
      public void run() {
        livingEntity.save(spawnLocation.getWorldName());
        livingEntity.sendPacketTeam(spawnLocation,dungeonTeam);
      }
    });
  }

  public DungeonLivingEntity createEntity(final DungeonEntity dungeonEntity,final String wName){
    switch (dungeonEntity.entityType()){
      case 1:
        return new DungeonSkeleton(dungeonEntity,new EntitySkeleton(getWorld(wName)));
      case 2:
        return new DungeonEntityZombie(dungeonEntity,new EntityZombie(getWorld(wName)));
      case 3:
        return new DungeonWitherSkeleton(dungeonEntity,new EntitySkeletonWither(getWorld(wName)));
    }
    return null;
  }
}
