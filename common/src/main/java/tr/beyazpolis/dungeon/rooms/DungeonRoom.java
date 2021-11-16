package tr.beyazpolis.dungeon.rooms;

import java.util.HashMap;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import tr.beyazpolis.common.CommonPlugin;
import tr.beyazpolis.entity.DungeonEntity;
import tr.beyazpolis.world.LocationWrapper;

public interface DungeonRoom {

  CommonPlugin getCommonPlugin();

  HashMap<Integer, DungeonEntity> entities();

  LocationWrapper spawnLocation();

  LocationWrapper mobSpawnLocations();

  int mobAmount();

  int startTime();

  RoomMessages getRoomMessages();

  RoomSounds getRoomSounds();

  int id();

  void setMobAmount(final int mobAmount);

  void setMobSpawnLocation(final LocationWrapper mobSpawnLocation);

  void setRoomMessages(final RoomMessages roomMessages);

  void setSpawnLocation(final LocationWrapper spawnLocation);

  void setStartTime(final int startTime);

  void addEntity(@NotNull final DungeonEntity entity);

  void setRoomSounds(@NotNull final RoomSounds roomSounds);

  boolean isEntityIsDungeonEntity(final UUID livingId);
}
