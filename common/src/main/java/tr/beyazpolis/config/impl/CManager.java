package tr.beyazpolis.config.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.NotNull;
import tr.beyazpolis.common.CommonPlugin;
import tr.beyazpolis.config.Config;
import tr.beyazpolis.config.ConfigManager;

public class CManager implements ConfigManager {

  private final Map<String, Config> configs;

  public CManager() {
    this.configs = new HashMap<>();
  }

  @Override
  public void addConfig(@NotNull final Config config){
    Optional.ofNullable(configs.get(config.getConfigAdapter().getYmlName()))
      .ifPresentOrElse(c -> System.out.println("This config could not be initialized because this config already exists! = " + c.getConfigAdapter().getYmlName()), () -> {
        this.add(config);
      });
  }

  @NotNull
  @Override
  public Config get(@NotNull final String name){
    return Optional.ofNullable(configs.get(name)).orElseThrow(() -> new NullPointerException("Config name is null!"));
  }

  @Override
  public void loads(){
    configs.forEach((s, config) -> config.getConfigAdapter().load());
  }

  @Override
  public void starts(){
    configs.forEach((s, config) -> config.start());
  }

  @Override
  public void saves(){
    configs.forEach((s, config) -> config.save());
  }

  @Override
  public void runAsyncL_S_S(@NotNull final CommonPlugin commonPlugin) {
    CompletableFuture.runAsync(() -> {
      loads();
      starts();
      saves();
      final String name = (String) this.get("settings.yml").get("Dungeon.settings.worldName");
      commonPlugin.getWorldWrapper().setDungeonWorldName(name);
    });
  }

  @NotNull
  public Collection<Config> getConfigs() {
    return configs.values();
  }

  @NotNull
  @Override
  public Map<String, Config> cloneConfigs(){
    return configs;
  }

  private void add(@NotNull final Config config){
    configs.put(config.getConfigAdapter().getYmlName(),config);
  }
}
