package tr.beyazpolis.dungeon.rooms;

import org.jetbrains.annotations.NotNull;
import tr.beyazpolis.common.CommonPlugin;
import tr.beyazpolis.config.Config;
import tr.beyazpolis.entity.DungeonEntity;
import tr.beyazpolis.entity.Entities;
import tr.beyazpolis.world.LocationWrapper;

public interface RoomLoader {

  int getRoomStartTime(@NotNull final Config config, final int roomId);

  int getRoomMobAmount(@NotNull final Config config, @NotNull final String mob, final int roomId);

  DungeonRoom getRoom(@NotNull final CommonPlugin commonPlugin, final Config config, final int roomId);

  void setConfigRoom(final int roomValue, final Config config);

  void setConfigBossRoom(final Config configBossRoom);

}
