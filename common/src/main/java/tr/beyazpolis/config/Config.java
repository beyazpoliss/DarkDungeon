package tr.beyazpolis.config;

import java.util.List;

public abstract class Config {

  private final ConfigAdapter configAdapter;

  public Config(final ConfigAdapter configAdapter) {
    this.configAdapter = configAdapter;
  }

  public abstract void start();

  public abstract List<String> getKeys(final String path, final boolean deep);

  public abstract void save();

  public abstract boolean isSet(final String path);

  public abstract Object get(final String path);

  public abstract Config set(final String path,final Object o);

  public abstract Object getConfiguration();

  public abstract void setIfNotExists(final String path,final Object o);

  public ConfigAdapter getConfigAdapter() {
    return configAdapter;
  }
}
