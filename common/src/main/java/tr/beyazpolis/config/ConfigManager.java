package tr.beyazpolis.config;

import com.sun.jdi.Location;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import tr.beyazpolis.common.CommonPlugin;
import tr.beyazpolis.dungeon.rooms.DRoom;
import tr.beyazpolis.dungeon.rooms.DungeonRoom;
import tr.beyazpolis.dungeon.rooms.RoomMessages;
import tr.beyazpolis.entity.DungeonEntity;
import tr.beyazpolis.entity.Entities;
import tr.beyazpolis.world.LocationWrapper;

public interface ConfigManager {

  /**
   * Returns a configuration object according to the configuration name.
   *
   * @param name config name.
   * @return Config object.
   */
  @NotNull
  Config get(@NotNull final String name);

  /**
   * Calls load methods in all configuration files.
   */
  void loads();

  /**
   * Calls initialization methods in all configuration files.
   */
  void starts();

  /**
   *  Calls saves methods in all configuration files.
   */
  void saves();

  void runAsyncL_S_S(@NotNull final CommonPlugin commonPlugin);

  /**
   * Adds a new config object.
   *
   * @param config object.
   */
  void addConfig(@NotNull final Config config);

  /**
   * Returns all config files.
   *
   * @return all configs.
   */
  @NotNull
  Collection<Config> getConfigs();

  /**
   * Returns the clone of the Map where the config objects are held.
   *
   * @return clone config map.
   */
  @NotNull
  public Map<String, Config> cloneConfigs();
}
