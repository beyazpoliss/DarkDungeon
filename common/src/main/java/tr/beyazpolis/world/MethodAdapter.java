package tr.beyazpolis.world;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tr.beyazpolis.common.CommonPlugin;
import tr.beyazpolis.dungeon.rooms.DungeonRoom;
import tr.beyazpolis.dungeon.teams.DungeonTeam;
import tr.beyazpolis.world.LocationWrapper;

public interface MethodAdapter {

  CommonPlugin getPlugin();

  boolean isOnline(@NotNull final String playerName);

  boolean isOp(@NotNull final String playerName);

  boolean hasPermission(@NotNull final String playerName,@NotNull final String permission);

  void teleportPlayer(@NotNull final String playerName,@NotNull final LocationWrapper wrapper);

  Object getWorld(@NotNull final String worldName);

  void sendMessage(@NotNull final String playerName,@NotNull final String message);

  @Nullable
  Object getPlayer(@NotNull final String playerName);

  void startRoomTimer(@NotNull final DungeonTeam dungeonTeam);

}
