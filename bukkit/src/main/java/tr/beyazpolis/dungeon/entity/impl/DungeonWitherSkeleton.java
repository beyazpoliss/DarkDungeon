package tr.beyazpolis.dungeon.entity.impl;

import java.util.Random;
import net.minecraft.server.v1_12_R1.DataWatcher;
import net.minecraft.server.v1_12_R1.EntityLiving;
import net.minecraft.server.v1_12_R1.EntitySkeletonWither;
import net.minecraft.server.v1_12_R1.EntitySkeletonWither;
import net.minecraft.server.v1_12_R1.EntityZombie;
import net.minecraft.server.v1_12_R1.EnumItemSlot;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.PacketPlayOutEntityEquipment;
import net.minecraft.server.v1_12_R1.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_12_R1.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_12_R1.PlayerConnection;
import net.minecraft.server.v1_12_R1.World;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import tr.beyazpolis.dungeon.entity.BukkitLivingEntity;
import tr.beyazpolis.dungeon.teams.DungeonTeam;
import tr.beyazpolis.dungeon.world.BukkitLocation;
import tr.beyazpolis.dungeon.world.BukkitPlayer;
import tr.beyazpolis.entity.DungeonEntity;
import tr.beyazpolis.profile.player.DungeonPlayer;
import tr.beyazpolis.world.LocationWrapper;
import tr.beyazpolis.world.PlayerWrapper;

public class DungeonWitherSkeleton extends BukkitLivingEntity {

  public DungeonWitherSkeleton(final DungeonEntity entity, final Object entityType) {
    super(entity, entityType);
  }

  @Override
  public void save(final String worldName) {
    this.entityType  = new EntitySkeletonWither(this.getWorld(worldName));
    //Bukkit.MaxHealth
    NBTTagCompound nbtTagCompound = new NBTTagCompound();
    nbtTagCompound.setFloat("Bukkit.MaxHealth",getDungeonEntity().getMaxHealth());
    ((EntitySkeletonWither)entityType).getAttributeMap().a("generic.attackDamage").setValue(getDungeonEntity().attackDamage());
    ((EntitySkeletonWither)entityType).a(nbtTagCompound);
    ((EntitySkeletonWither)entityType).c(nbtTagCompound);

    getDungeonEntity().setEntityId(((EntitySkeletonWither) entityType).getUniqueID());
  }

  @Override
  public void sendPacketTeam(final LocationWrapper spawnLocation,
                             @NotNull final DungeonTeam dungeonTeam) {
    ((EntitySkeletonWither)entityType).setLocation(spawnLocation.getX(), spawnLocation.getY(), spawnLocation.getZ(), 3,3);
    ((EntitySkeletonWither)entityType).setCustomName(getDungeonEntity().getCustomName());
    ((EntitySkeletonWither)entityType).setHealth(getDungeonEntity().getMaxHealth());
    ((EntitySkeletonWither)entityType).setEquipment(EnumItemSlot.CHEST, CraftItemStack.asNMSCopy(new ItemStack(Material.DIAMOND_CHESTPLATE)));
    getWorld(spawnLocation.getWorldName()).addEntity((EntitySkeletonWither) entityType);
    PacketPlayOutSpawnEntityLiving spawnPacket = new PacketPlayOutSpawnEntityLiving(((EntitySkeletonWither)entityType));
    PacketPlayOutEntityEquipment e = new PacketPlayOutEntityEquipment(((EntitySkeletonWither)entityType).getId(),EnumItemSlot.MAINHAND, CraftItemStack.asNMSCopy(new ItemStack(Material.GOLD_SWORD)));
    PacketPlayOutEntityMetadata a = new PacketPlayOutEntityMetadata(((EntitySkeletonWither)entityType).getId(),new DataWatcher(((EntitySkeletonWither)entityType)),false);
    dungeonTeam.players().forEach(dungeonPlayer -> {
      final PlayerWrapper playerWrapper = new BukkitPlayer(dungeonPlayer.name(),dungeonPlayer.getUUID(), new BukkitLocation(Bukkit.getPlayer(dungeonPlayer.getUUID()).getLocation()));
      this.getPlayerConnection(playerWrapper).sendPacket(spawnPacket);
      this.getPlayerConnection(playerWrapper).sendPacket(e);
      this.getPlayerConnection(playerWrapper).sendPacket(a);
    });
  }
}
