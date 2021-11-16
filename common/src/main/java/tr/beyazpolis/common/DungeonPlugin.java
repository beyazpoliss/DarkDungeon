package tr.beyazpolis.common;

import org.jetbrains.annotations.NotNull;
import tr.beyazpolis.commands.CommandManager;
import tr.beyazpolis.commands.impl.ComandManager;
import tr.beyazpolis.config.Config;
import tr.beyazpolis.config.ConfigManager;
import tr.beyazpolis.config.impl.CManager;
import tr.beyazpolis.dungeon.RoomManager;
import tr.beyazpolis.dungeon.rooms.RLoader;
import tr.beyazpolis.dungeon.rooms.RoomLoader;
import tr.beyazpolis.entity.EntityManager;
import tr.beyazpolis.entity.impl.EManager;

public abstract class DungeonPlugin implements CommonPlugin {

  private final CommandManager commandManager;
  private final EntityManager entityManager;
  private final ConfigManager configManager;
  private final RoomLoader roomLoader;

  public DungeonPlugin(){
    this.commandManager = new ComandManager();
    this.entityManager = new EManager();
    this.configManager = new CManager();
    this.roomLoader = new RLoader();
  }

  @Override
  public void onEnable() {
    registerCommand();
    this.getConfigManager().addConfig(this.createConfig("settings.yml", () -> {
      for (int i = 1; i <= 7; i++) {
        final Config config = this.getConfigManager().get("settings.yml");
        roomLoader.setConfigBossRoom(config);
        roomLoader.setConfigRoom(i,config);
        RoomManager.ROOMS.put(i,roomLoader.getRoom(this,config,i));
      }
    }));
    this.getConfigManager().runAsyncL_S_S(this);
    this.addPacketListener();
    this.registerEvents();

  }

  @Override
  public void onDisable() {
    this.getConfigManager().saves();
  }

  @NotNull
  @Override
  public CommandManager getCommandManager() {
    return commandManager;
  }

  @Override
  @NotNull
  public EntityManager getEntityManager() {
    return entityManager;
  }

  @NotNull
  @Override
  public ConfigManager getConfigManager() {
    return configManager;
  }

  @Override
  public RoomLoader getRoomLoader() {
    return roomLoader;
  }
}
