package tr.beyazpolis.dungeon.entity;

import net.minecraft.server.v1_12_R1.PlayerConnection;
import net.minecraft.server.v1_12_R1.World;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import tr.beyazpolis.entity.DungeonEntity;
import tr.beyazpolis.entity.impl.EntityLiving;
import tr.beyazpolis.world.PlayerWrapper;

public abstract class BukkitLivingEntity extends EntityLiving {

  public BukkitLivingEntity(final DungeonEntity entity,
                            final Object entityType) {
    super(entity, entityType);
  }

  @Override
  public World getWorld(final String name){
    return ((CraftWorld) Bukkit.getWorld(name)).getHandle();
  }

  @Override
  public PlayerConnection getPlayerConnection(final PlayerWrapper playerWrapper) {
    return (PlayerConnection) playerWrapper.getPlayerConnection();
  }


}
