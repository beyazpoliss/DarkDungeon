package tr.beyazpolis.dungeon;

import java.util.HashMap;
import java.util.Random;
import org.jetbrains.annotations.NotNull;
import tr.beyazpolis.dungeon.rooms.DungeonRoom;
import tr.beyazpolis.dungeon.rooms.boss.BossRoom;
import tr.beyazpolis.dungeon.teams.DungeonTeam;

public interface RoomManager {

  HashMap<Integer, DungeonRoom> ROOMS = new HashMap<>();
  HashMap<Integer, BossRoom> BOSS_ROOMS = new HashMap<>();

  static void addRoomsOfTeam(final DungeonTeam dungeonTeam){
    RoomManager.ROOMS.forEach((integer, dungeonRoom) -> dungeonTeam.addRoom(dungeonRoom));
    dungeonTeam.setBossRoom(BOSS_ROOMS.get(new Random().nextInt(BOSS_ROOMS.size())));
  }

  static void addRoom(@NotNull final DungeonRoom dungeonRoom){
    ROOMS.put(dungeonRoom.id(),dungeonRoom);
  }
}
