package tr.beyazpolis.world;

import tr.beyazpolis.config.Config;

public class LocationWrapper {

  private final int x;
  private final int y;
  private final int z;
  private final String worldName;

  public LocationWrapper(final int x, final int y, final int z, final String worldName) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.worldName = worldName;
  }

  public String getWorldName() {
    return worldName;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getZ() {
    return z;
  }

  public static LocationWrapper getRoomSpawnLocationById(final Config config, final int id){
    return new LocationWrapper(((int)config.get("Rooms.room" + id +".Locations.spawnLocation" +".x")),
      ((int)config.get("Rooms.room" + id +".Locations.spawnLocation" +".y")),
      ((int)config.get("Rooms.room" + id +".Locations.spawnLocation" +".z")),
      ((String)config.get("Rooms.room" + id +".Locations.spawnLocation" +".world")));
  }

  public static LocationWrapper getRoomMobSpawnLocationById(final Config config, final int id){
    return new LocationWrapper(((int)config.get("Rooms.room" + id +".Locations.mobSpawnLocation" +".x")),
      ((int)config.get("Rooms.room" + id +".Locations.mobSpawnLocation" +".y")),
      ((int)config.get("Rooms.room" + id +".Locations.mobSpawnLocation" +".z")),
      ((String)config.get("Rooms.room" + id +".Locations.mobSpawnLocation" +".world")));
  }
}
