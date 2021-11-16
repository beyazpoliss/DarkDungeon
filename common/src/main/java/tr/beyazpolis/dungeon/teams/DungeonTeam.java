package tr.beyazpolis.dungeon.teams;

import java.util.HashMap;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import tr.beyazpolis.dungeon.DungeonManager;
import tr.beyazpolis.dungeon.rooms.DungeonRoom;
import tr.beyazpolis.dungeon.rooms.boss.BossRoom;
import tr.beyazpolis.profile.player.DungeonPlayer;
import tr.beyazpolis.world.LocationWrapper;
import tr.beyazpolis.world.WorldWrapper;

public interface DungeonTeam {

  List<DungeonPlayer> players();

  HashMap<Integer, DungeonRoom> rooms();

  int currentRoomId();

  DungeonRoom currentRoom();

  void addCurrentKilledMob();

  boolean isNextRoom();

  void setBossRoom(@NotNull BossRoom bossRoom);

  BossRoom getBossRoom();

  int currentKilledMob();

  void setCurrentRoomId(final int roomId);

  void spawnCurrentRoom(final WorldWrapper worldWrapper);

  void addPlayer(@NotNull final DungeonPlayer dungeonPlayer);

  void addRoom(@NotNull final DungeonRoom dungeonRoom);

  boolean hasPlayerIsTeam(final String name);

  void removeTeam(final DungeonPlayer dungeonPlayer);

  void removePlayer(final DungeonPlayer dungeonPlayer);

}
