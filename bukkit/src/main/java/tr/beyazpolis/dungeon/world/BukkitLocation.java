package tr.beyazpolis.dungeon.world;

import org.bukkit.Location;
import tr.beyazpolis.world.LocationWrapper;

public class BukkitLocation extends LocationWrapper {

  private final Location location;

  public BukkitLocation(final int x, final int y, final int z, final String worldName) {
    super(x, y, z, worldName);
    this.location = null;
  }

  public BukkitLocation(final Location location){
    super(location.getBlockX(), location.getBlockY(), location.getBlockZ(), location.getWorld().getName());
    this.location = location;
  }

  public Location getLocation() {
    return new Location(location.getWorld(),location.getBlockX(),location.getBlockY(),location.getZ(),3,3);
  }
}
