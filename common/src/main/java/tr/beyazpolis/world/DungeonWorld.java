package tr.beyazpolis.world;

import org.jetbrains.annotations.NotNull;
import tr.beyazpolis.common.CommonPlugin;
import tr.beyazpolis.dungeon.teams.DungeonTeam;
import tr.beyazpolis.entity.DungeonEntity;

public interface DungeonWorld {

  String dungeonWorld();

  void setDungeonWorldName(final String name);

  CommonPlugin getPlugin();

  Object getWorld(@NotNull final String worldName);

  Object getCraftWorld(@NotNull final String worldName);

  void teleportPlayer(@NotNull final String playerName, @NotNull final LocationWrapper wrapper);

  void spawnEntitiesTeam(@NotNull final DungeonEntity dungeonEntity,
                         @NotNull final DungeonTeam dungeonTeam,
                         @NotNull final LocationWrapper spawnLocation);

}
