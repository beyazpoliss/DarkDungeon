package tr.beyazpolis.entity;

import java.util.Collection;
import org.jetbrains.annotations.NotNull;

public interface EntityManager {

  DungeonEntity getEntity(@NotNull final String name);

  Collection<DungeonEntity> all();

  void add(@NotNull final String name, @NotNull final DungeonEntity dungeonEntity);

}
