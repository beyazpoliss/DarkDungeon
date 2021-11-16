package tr.beyazpolis.dungeon;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import tr.beyazpolis.common.CommonPlugin;
import tr.beyazpolis.dungeon.rooms.DungeonRoom;
import tr.beyazpolis.dungeon.teams.DTeam;
import tr.beyazpolis.dungeon.teams.DungeonTeam;
import tr.beyazpolis.entity.DungeonEntity;
import tr.beyazpolis.profile.player.DungeonPlayer;
import tr.beyazpolis.world.WorldWrapper;

public interface DungeonManager {

  Set<DungeonTeam> TEAMS = new HashSet<>();

  static boolean isSpawn(final DungeonPlayer dungeonPlayer,final UUID id){
    final DungeonTeam dungeonTeam = DungeonManager.of(dungeonPlayer);
    if (dungeonTeam == null) return false;
    if (dungeonTeam.currentRoomId() == 0 && dungeonTeam.currentRoomId() <= 7) return false;
    for (final DungeonEntity entity : dungeonTeam.currentRoom().entities().values()) {
      if (id == entity.entityId()){
        return true;
      }
    }
    return false;
  }

  static DungeonTeam getEntityLivingByTeam(final UUID id){
    for (final DungeonTeam team : TEAMS) {
      if (team.currentRoomId() == 8){
        final DungeonRoom dungeonRoom = team.getBossRoom();
        if (dungeonRoom.isEntityIsDungeonEntity(id)){
          return team;
        }
      }
      if (team.currentRoomId() > 1 && team.currentRoomId() < 8){
        final DungeonRoom dungeonRoom = team.currentRoom();
        if (dungeonRoom.isEntityIsDungeonEntity(id)){
          return team;
        }
      }
    }
    return null;
  }

  static DungeonTeam of(final DungeonPlayer dungeonPlayer){
    for (final DungeonTeam team : TEAMS) {
      for (final DungeonPlayer player : team.players()) {
        if (player.getUUID().equals(dungeonPlayer.getUUID())){
          return team;
        }
      }
    }
    return null;
  }

  static void removeTeam(final DungeonPlayer dungeonPlayer){
    final DungeonTeam dungeonTeam = of(dungeonPlayer);
    if (dungeonTeam == null) return;
    TEAMS.remove(dungeonTeam);
  }

  static DungeonTeam createDungeonTeam(@NotNull final DungeonPlayer... dungeonPlayers) {
    final DungeonTeam dungeonTeam = new DTeam();
    for (final DungeonPlayer dungeonPlayer : dungeonPlayers) {
      dungeonTeam.addPlayer(dungeonPlayer);
    }
    RoomManager.addRoomsOfTeam(dungeonTeam);
    DungeonManager.TEAMS.add(dungeonTeam);
    return dungeonTeam;
  }

  static void spawnRoom(@NotNull final CommonPlugin commonPlugin,@NotNull final DungeonTeam dungeonTeam) {
    dungeonTeam.spawnCurrentRoom((WorldWrapper) commonPlugin.getWorldWrapper());
  }

  static void createDungeon(@NotNull final CommonPlugin commonPlugin,
                            @NotNull final DungeonTeam dungeonTeam) {
    dungeonTeam.setCurrentRoomId(1);
    commonPlugin.getMethodAdapter().startRoomTimer(dungeonTeam);
  }
}
