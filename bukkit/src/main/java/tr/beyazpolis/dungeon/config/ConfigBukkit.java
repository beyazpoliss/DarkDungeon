package tr.beyazpolis.dungeon.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.configuration.file.FileConfiguration;
import tr.beyazpolis.config.Config;

public abstract class ConfigBukkit extends Config {

  public ConfigBukkit(final ConfigAdapterBukkit configAdapter) {
    super(configAdapter);
  }

  @Override
  public List<String> getKeys(final String path, final boolean deep){
    if (!isSet(path)) return new ArrayList<>();
    return new ArrayList<>(getConfiguration().getConfigurationSection(path).getKeys(deep));
  }

  @Override
  public void save() {
    try {
      this.getConfiguration().save(getConfigAdapter().getYml());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public boolean isSet(final String path){
    return this.getConfiguration().isSet(path);
  }

  @Override
  public Config set(final String path,final Object o){
    this.getConfiguration().set(path,o);
    return this;
  }

  @Override
  public void setIfNotExists(final String path, final Object o) {
    if (!isSet(path)){
      this.getConfiguration().set(path, o);
    }
  }

  @Override
  public FileConfiguration getConfiguration(){
    return (FileConfiguration) super.getConfigAdapter().getConfiguration();
  }

  @Override
  public Object get(final String path) {
    return getConfiguration().get(path);
  }
}
