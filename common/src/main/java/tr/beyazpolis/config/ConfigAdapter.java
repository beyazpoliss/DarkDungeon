package tr.beyazpolis.config;

import java.io.File;
import java.io.IOException;
import org.jetbrains.annotations.NotNull;

public abstract class ConfigAdapter {

  private final File pluginFolder;

  private final File yml;
  private final String path;

  private final String ymlName;

  public ConfigAdapter(@NotNull final String path,@NotNull final String ymlName) {
    this.path = path;
    this.ymlName = ymlName;
    this.pluginFolder = new File(path);
    this.yml = new File(path,ymlName);
  }

  protected void createFile(){
    if (!pluginFolder.exists()){
      try {
        pluginFolder.mkdirs();
        pluginFolder.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public abstract Object getConfiguration();

  public abstract void load();

  public String getYmlName() {
    return ymlName;
  }

  public File getYml() {
    return yml;
  }

  public String getPath() {
    return path;
  }

  public File getPluginFolder() {
    return pluginFolder;
  }
}
