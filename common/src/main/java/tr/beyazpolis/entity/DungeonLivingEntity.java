package tr.beyazpolis.entity;

import org.jetbrains.annotations.NotNull;
import tr.beyazpolis.common.CommonPlugin;
import tr.beyazpolis.dungeon.teams.DungeonTeam;
import tr.beyazpolis.world.LocationWrapper;
import tr.beyazpolis.world.PlayerWrapper;

public interface DungeonLivingEntity {

  void save(final String worldName);

  void sendPacketTeam(final LocationWrapper spawnLocation, @NotNull final DungeonTeam dungeonTeam);

  DungeonEntity getDungeonEntity();

  Object getEntityType();

  Object getWorld(final String name);

  Object getPlayerConnection(final PlayerWrapper playerWrapper);

}
