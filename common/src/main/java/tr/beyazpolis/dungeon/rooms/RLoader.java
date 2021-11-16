package tr.beyazpolis.dungeon.rooms;

import org.jetbrains.annotations.NotNull;
import tr.beyazpolis.common.CommonPlugin;
import tr.beyazpolis.config.Config;
import tr.beyazpolis.entity.DungeonEntity;
import tr.beyazpolis.entity.Entities;
import tr.beyazpolis.world.LocationWrapper;

public class RLoader implements RoomLoader {

  @Override
  public int getRoomStartTime(@NotNull final Config config, final int roomId){
    return ((int)config.get("Rooms.room" + roomId + ".settings.startTime"));
  }

  @Override
  public int getRoomMobAmount(@NotNull final Config config, @NotNull final String mob, final int roomId){
    return ((int)config.get("Rooms.room" + roomId + ".Entities."+ mob +".amount"));
  }

  @Override
  public DungeonRoom getRoom(@NotNull final CommonPlugin commonPlugin, final Config config, final int roomId) {
    final LocationWrapper spawnLocation = LocationWrapper.getRoomSpawnLocationById(config, roomId);
    final LocationWrapper mobSpawnLocation = LocationWrapper.getRoomMobSpawnLocationById(config, roomId);
    final DungeonRoom dungeonRoom = new DRoom(commonPlugin,roomId);
    dungeonRoom.setSpawnLocation(spawnLocation);
    dungeonRoom.setMobSpawnLocation(mobSpawnLocation);
    dungeonRoom.setStartTime(this.getRoomStartTime(config, roomId));
    for (final String mob : Entities.names()) {
      for (int i = 0; i < this.getRoomMobAmount(config, mob, roomId); i++) {
        final DungeonEntity dungeonEntity = DungeonEntity.createEntity(commonPlugin, config, roomId, mob);
        dungeonRoom.addEntity(dungeonEntity);
      }
    }
    dungeonRoom.setRoomMessages(new RoomMessages() {
      @Override
      public String joinMessage() {
        return ((String)config.get("Rooms.room" + roomId + ".settings.messages.joinMessage"));
      }

      @Override
      public String messageToNextRoom() {
        return ((String)config.get("Rooms.room" + roomId + ".settings.messages.messageToNextRoom"));
      }

      @Override
      public String startMessage() {
        return ((String)config.get("Rooms.room" + roomId + ".settings.messages.startMessage"));
      }

      @Override
      public String startTitleMessage() {
        return ((String)config.get("Rooms.room" + roomId + ".settings.messages.startTitleMessage"));
      }

      @Override
      public String startTitleMiniMessage() {
        return ((String)config.get("Rooms.room" + roomId + ".settings.messages.startTitleMiniMessage"));
      }

      @Override
      public String deathMessage() {
        return ((String)config.get("Rooms.room" + roomId + ".settings.messages.deathMessage"));
      }
    });

    dungeonRoom.setRoomSounds(new RoomSounds() {
      @Override
      public String joinSoundKey() {
        return ((String)config.get("Rooms.room" + roomId + ".settings.sounds.joinSound"));
      }

      @Override
      public String startSoundKey() {
        return ((String)config.get("Rooms.room" + roomId + ".settings.sounds.startSound"));
      }

      @Override
      public String soundToNextRoomKey() {
        return ((String)config.get("Rooms.room" + roomId + ".settings.sounds.soundToNextRoom"));
      }
    });

    return dungeonRoom;
  }



  @Override
  public void setConfigRoom(final int roomValue, final Config config) {
    //
    config.setIfNotExists("Rooms.room" + roomValue+".settings.startTime", 5);
    config.setIfNotExists("Rooms.room" + roomValue+".settings.messages.joinMessage", "&aAfter 10 seconds, you will enter the rooms with your dungeon team, be careful! harmful creatures will be there to fight you");
    config.setIfNotExists("Rooms.room" + roomValue+".settings.messages.messageToNextRoom", "&aAfter 10 seconds, you will enter the rooms with your dungeon team, be careful! harmful creatures will be there to fight you");
    config.setIfNotExists("Rooms.room" + roomValue+".settings.messages.startMessage", "&aYou are walking for a light in a dark dungeon and you accidentally enter one of the rooms...");
    config.setIfNotExists("Rooms.room" + roomValue+".settings.messages.startTitleMessage", "&cYou accidentally entered the first room in the dark");
    config.setIfNotExists("Rooms.room" + roomValue+".settings.messages.startTitleMiniMessage", "&c be careful creatures around");
    config.setIfNotExists("Rooms.room" + roomValue+".settings.messages.deathMessage", "&c are you death!");
    //
    config.setIfNotExists("Rooms.room" + roomValue+".settings.sounds.joinSound", "AMBIENT.CAVE");
    config.setIfNotExists("Rooms.room" + roomValue+".settings.sounds.startSound", "AMBIENT.CAVE");
    config.setIfNotExists("Rooms.room" + roomValue+".settings.sounds.soundToNextRoom", "AMBIENT.CAVE");
    //
    config.setIfNotExists("Rooms.room" + roomValue+".Locations.spawnLocation" +".x", -27);
    config.setIfNotExists("Rooms.room" + roomValue+".Locations.spawnLocation" +".y", 136);
    config.setIfNotExists("Rooms.room" + roomValue+".Locations.spawnLocation" +".z", 188);
    config.setIfNotExists("Rooms.room" + roomValue+".Locations.spawnLocation" +".world", "world");
    //
    config.setIfNotExists("Rooms.room" + roomValue+".Locations.mobSpawnLocation" +".x", -44);
    config.setIfNotExists("Rooms.room" + roomValue+".Locations.mobSpawnLocation" +".y", 136);
    config.setIfNotExists("Rooms.room" + roomValue+".Locations.mobSpawnLocation" +".z", 176);
    config.setIfNotExists("Rooms.room" + roomValue+".Locations.mobSpawnLocation" +".world", "world");
    //
    final String[] mobs  = new String[]{"skeleton","zombie","wither"};
    for (final String mob : mobs) {
      config.setIfNotExists("Rooms.room" + roomValue+".Entities."+ mob +".amount", 5);
      config.setIfNotExists("Rooms.room" + roomValue+".Entities."+ mob +".settings.health", 5);
      config.setIfNotExists("Rooms.room" + roomValue+".Entities."+ mob +".settings.damage", 5);
      config.setIfNotExists("Rooms.room" + roomValue+".Entities."+ mob +".settings.name", mob + " [%health%]");
    }
  }

  @Override
  public void setConfigBossRoom(final Config config) {
    config.setIfNotExists("Dungeon.settings.worldName","world");


    config.setIfNotExists("BossRoom.settings.startTime", 5);
    config.setIfNotExists("BossRoom.settings.messages.joinMessage", "&aAfter 10 seconds, you will enter the rooms with your dungeon team, be careful! harmful creatures will be there to fight you");
    config.setIfNotExists("BossRoom.settings.messages.messageToNextRoom", "&aAfter 10 seconds, you will enter the rooms with your dungeon team, be careful! harmful creatures will be there to fight you");
    config.setIfNotExists("BossRoom.settings.messages.startMessage", "&aYou are walking for a light in a dark dungeon and you accidentally enter one of the rooms...");
    config.setIfNotExists("BossRoom.settings.messages.startTitleMessage", "&cYou accidentally entered the first room in the dark");
    config.setIfNotExists("BossRoom.settings.messages.startTitleMiniMessage", "&c be careful creatures around");
    config.setIfNotExists("BossRoom.settings.messages.deathMessage", "&c are you death!");
    //
    config.setIfNotExists("BossRoom.settings.sounds.joinSound", "AMBIENT.CAVE");
    config.setIfNotExists("BossRoom.settings.sounds.startSound", "AMBIENT.CAVE");
    config.setIfNotExists("BossRoom.settings.sounds.soundToNextRoom", "AMBIENT.CAVE");
    //
    config.setIfNotExists("BossRoom.Locations.spawnLocation" +".x", -27);
    config.setIfNotExists("BossRoom.Locations.spawnLocation" +".y", 136);
    config.setIfNotExists("BossRoom.Locations.spawnLocation" +".z", 188);
    config.setIfNotExists("BossRoom.Locations.spawnLocation" +".world", "world");
    //
    config.setIfNotExists("BossRoom.Locations.bossSpawnLocation" +".x", -44);
    config.setIfNotExists("BossRoom.Locations.bossSpawnLocation" +".y", 136);
    config.setIfNotExists("BossRoom.Locations.bossSpawnLocation" +".z", 176);
    config.setIfNotExists("BossRoom.Locations.bossSpawnLocation" +".world", "world");

    config.setIfNotExists("BossRoom.boss.settings.health", 300);
    config.setIfNotExists("BossRoom.boss.settings.damage", 300);
    config.setIfNotExists("BossRoom.boss.settings.skills.randomSkillTime", 10);
    config.setIfNotExists("BossRoom.boss.settings.skills.anvil.damage", 10);
    config.setIfNotExists("BossRoom.boss.settings.skills.particleShoot.damage", 10);
    config.setIfNotExists("BossRoom.boss.settings.skills.createMinion.count", 10);
    config.setIfNotExists("BossRoom.boss.settings.skills.createMinion.removeTime", 20);
  }
}
