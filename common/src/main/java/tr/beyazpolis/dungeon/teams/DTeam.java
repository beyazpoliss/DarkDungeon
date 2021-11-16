package tr.beyazpolis.dungeon.teams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import tr.beyazpolis.dungeon.DungeonManager;
import tr.beyazpolis.dungeon.rooms.DungeonRoom;
import tr.beyazpolis.dungeon.rooms.boss.BossRoom;
import tr.beyazpolis.profile.player.DungeonPlayer;
import tr.beyazpolis.world.WorldWrapper;

public class DTeam implements DungeonTeam{

  private final List<DungeonPlayer> dungeonPlayers;
  private final HashMap<Integer, DungeonRoom> rooms;
  private int currentRoomId;
  private int currentKilledMob;
  private BossRoom bossRoom;

  public DTeam() {
    this.dungeonPlayers = new ArrayList<>();
    this.rooms = new HashMap<>();
    this.currentRoomId = 0;
    this.currentKilledMob = 0;
    this.bossRoom = null;
  }

  @Override
  public List<DungeonPlayer> players() {
    return dungeonPlayers;
  }

  @Override
  public HashMap<Integer, DungeonRoom> rooms() {
    return rooms;
  }

  @Override
  public int currentRoomId() {
    return currentRoomId;
  }

  @Override
  public DungeonRoom currentRoom() {
    return this.rooms().get(currentRoomId);
  }

  @Override
  public void addCurrentKilledMob() {
    currentKilledMob++;
  }

  @Override
  public boolean isNextRoom() {
    if (currentKilledMob >= currentRoom().mobAmount()){
      currentRoomId++;
      currentKilledMob = 0;
      return true;
    }
    return false;
  }

  @Override
  public void setBossRoom(@NotNull final BossRoom bossRoom) {
    this.bossRoom = bossRoom;
  }

  @Override
  public BossRoom getBossRoom() {
    return bossRoom;
  }

  @Override
  public int currentKilledMob() {
    return currentKilledMob;
  }

  @Override
  public void setCurrentRoomId(final int roomId) {
    this.currentRoomId = roomId;
  }

  @Override
  public void spawnCurrentRoom(final WorldWrapper worldWrapper) {
    this.currentRoom().entities().forEach((integer, dungeonEntity) -> {
      worldWrapper.spawnEntitiesTeam(dungeonEntity,this,this.currentRoom().mobSpawnLocations());
    });
  }

  @Override
  public void addPlayer(@NotNull final DungeonPlayer dungeonPlayer) {
    this.dungeonPlayers.add(dungeonPlayer);
  }

  @Override
  public void addRoom(@NotNull final DungeonRoom dungeonRoom) {
    this.rooms.put(dungeonRoom.id(),dungeonRoom);
  }

  @Override
  public boolean hasPlayerIsTeam(final String name) {
    for (final DungeonPlayer player : players()) {
      if (player.name().equalsIgnoreCase(name))
        return true;
    }
    return false;
  }

  @Override
  public void removeTeam(final DungeonPlayer dungeonPlayer) {
    DungeonManager.removeTeam(dungeonPlayer);
  }

  @Override
  public void removePlayer(final DungeonPlayer dungeonPlayer) {
    dungeonPlayers.remove(dungeonPlayer);
  }

}
