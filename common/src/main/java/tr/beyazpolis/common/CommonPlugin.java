package tr.beyazpolis.common;

import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import tr.beyazpolis.commands.CommandManager;
import tr.beyazpolis.config.Config;
import tr.beyazpolis.config.ConfigManager;
import tr.beyazpolis.config.ConfigRunner;
import tr.beyazpolis.dungeon.RoomManager;
import tr.beyazpolis.dungeon.rooms.RoomLoader;
import tr.beyazpolis.entity.DungeonEntity;
import tr.beyazpolis.entity.EntityManager;
import tr.beyazpolis.world.DungeonWorld;
import tr.beyazpolis.world.LocationWrapper;
import tr.beyazpolis.world.MethodAdapter;
import tr.beyazpolis.world.PlayerWrapper;

public interface CommonPlugin {

  /**
   * In the place where the plugin is opened, create the object and call the onEnable method.
   */
  void onEnable();

  /**
   * In the place where the plugin is disable, create the object and call the onDisable method.
   */
  void onDisable();

  /**
   * Add a command named /dungeon to the game.
   */
  void registerCommand();

  /**
   * ConfigManager object makes it easy to manage config jobs
   * @return ConfigManager.
   */
  @NotNull
  ConfigManager getConfigManager();

  /**
   * Carrier class to manage commands.
   *
   * @return  CommandManager
   */
  @NotNull
  CommandManager getCommandManager();

  /**
   * Object that adapts some functions.
   */
  @NotNull
  MethodAdapter getMethodAdapter();

  DungeonWorld getWorldWrapper();

  EntityManager getEntityManager();

  Object getPluginObject();

  void addPacketListener();

  Object getProtocolManager();

  Config createConfig(final String name, final ConfigRunner configRunner);

  DungeonEntity createEntity(final int entityType, final String entityCustomName, final int maxHealth, final int attackDamage);

  RoomLoader getRoomLoader();

  PlayerWrapper createPlayer(final String name, final UUID uuid, final LocationWrapper locationWrapper);

  void registerEvents();


}
