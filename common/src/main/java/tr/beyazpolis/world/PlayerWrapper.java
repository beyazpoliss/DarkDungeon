package tr.beyazpolis.world;

import java.util.UUID;
import tr.beyazpolis.profile.player.DungeonPlayer;

public abstract class PlayerWrapper {

  private final String name;
  private final UUID uuid;

  private final LocationWrapper locationWrapper;

  public PlayerWrapper(final String name,
                       final UUID uuid,
                       final LocationWrapper locationWrapper) {
    this.name = name;
    this.uuid = uuid;
    this.locationWrapper = locationWrapper;
  }

  public String getName() {
    return name;
  }

  public LocationWrapper getLocationWrapper() {
    return locationWrapper;
  }

  public UUID getUuid() {
    return uuid;
  }

  public abstract Object getPlayerConnection();

  public abstract void teleportPlayer();

  public abstract DungeonPlayer dungeonPlayer();

  public abstract void sendMessage(final String text);
}
