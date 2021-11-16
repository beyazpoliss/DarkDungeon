package tr.beyazpolis.dungeon.config;

import java.io.IOException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;
import tr.beyazpolis.config.ConfigAdapter;

public class ConfigAdapterBukkit extends ConfigAdapter {

  private FileConfiguration configuration;

  public ConfigAdapterBukkit(@NotNull final String path,
                             @NotNull final String ymlName) {
    super(path, ymlName);
  }

  @Override
  public void load() {
    createFile();
    if (!getYml().exists()){
      try {
        getYml().createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    this.configuration = YamlConfiguration.loadConfiguration(getYml());
  }

  public Object getConfiguration() {
    return configuration;
  }
}
