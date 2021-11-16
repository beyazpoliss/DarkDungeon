package tr.beyazpolis.profile.player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;

public interface DungeonPlayer {

  Map<UUID,DungeonPlayer> PLAYER_MAP = new HashMap<>();

  UUID getUUID();

  String name();

  static DungeonPlayer of(@NotNull final String name, @NotNull final UUID uuid){
    if (PLAYER_MAP.get(uuid) == null)
      return new DPlayer(uuid,name);
    return PLAYER_MAP.get(uuid);
  }
}

class DPlayer implements DungeonPlayer{

  private final UUID uuid;
  private final String name;

  public DPlayer(final UUID uuid, final String name) {
    this.uuid = uuid;
    this.name = name;
  }

  @Override
  public UUID getUUID() {
    return uuid;
  }

  @Override
  public String name() {
    return name;
  }
}
