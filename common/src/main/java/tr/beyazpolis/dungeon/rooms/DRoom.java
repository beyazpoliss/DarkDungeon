package tr.beyazpolis.dungeon.rooms;

import java.util.HashMap;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import tr.beyazpolis.common.CommonPlugin;
import tr.beyazpolis.entity.DungeonEntity;
import tr.beyazpolis.world.LocationWrapper;

public class DRoom implements DungeonRoom {

  private final int id;
  private final CommonPlugin commonPlugin;

  private final HashMap<Integer, DungeonEntity> entityHashMap;
  private LocationWrapper spawnLocation;
  private LocationWrapper mobSpawnLocation;
  private int mobAmount;
  private int startTime;
  private RoomMessages roomMessages;
  private RoomSounds roomSounds;

  public DRoom(@NotNull final CommonPlugin commonPlugin, final int id) {
    this.id = id;
    this.commonPlugin = commonPlugin;

    this.entityHashMap = new HashMap<>();

    this.spawnLocation = null;
    this.mobSpawnLocation = null;
    this.mobAmount = 0;
    this.startTime = 0;
    this.roomMessages = null;
    this.roomSounds = null;
  }

  @Override
  public CommonPlugin getCommonPlugin() {
    return commonPlugin;
  }

  @Override
  public HashMap<Integer, DungeonEntity> entities() {
    return entityHashMap;
  }

  @Override
  public LocationWrapper spawnLocation() {
    return spawnLocation;
  }

  @Override
  public LocationWrapper mobSpawnLocations() {
    return mobSpawnLocation;
  }

  @Override
  public int mobAmount() {
    return mobAmount;
  }

  @Override
  public int startTime() {
    return startTime;
  }

  @Override
  public RoomMessages getRoomMessages() {
    return roomMessages;
  }

  @Override
  public RoomSounds getRoomSounds() {
    return roomSounds;
  }

  @Override
  public void setRoomSounds(@NotNull final RoomSounds roomSounds) {
    this.roomSounds = roomSounds;
  }

  @Override
  public  boolean isEntityIsDungeonEntity(final UUID livingId){
    for (DungeonEntity dungeonEntity : entityHashMap.values()){
      if (dungeonEntity.entityId() == livingId){
        return true;
      }
    }
    return false;
  }

  @Override
  public int id() {
    return id;
  }

  @Override
  public void setMobAmount(final int mobAmount) {
    this.mobAmount = mobAmount;
  }

  @Override
  public void setMobSpawnLocation(final LocationWrapper mobSpawnLocation) {
    this.mobSpawnLocation = mobSpawnLocation;
  }

  @Override
  public void setRoomMessages(final RoomMessages roomMessages) {
    this.roomMessages = roomMessages;
  }

  @Override
  public void setSpawnLocation(final LocationWrapper spawnLocation) {
    this.spawnLocation = spawnLocation;
  }

  @Override
  public void setStartTime(final int startTime) {
    this.startTime = startTime;
  }

  @Override
  public void addEntity(@NotNull final DungeonEntity entity) {
    this.entityHashMap.put(mobAmount,entity);
    mobAmount++;
  }
}
