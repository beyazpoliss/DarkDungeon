package tr.beyazpolis.entity.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import tr.beyazpolis.entity.DungeonEntity;
import tr.beyazpolis.entity.EntityManager;

public class EManager implements EntityManager {

  private final Map<String, DungeonEntity> entityMap;

  public EManager() {
    this.entityMap = new HashMap<>();
  }

  @Override
  public DungeonEntity getEntity(@NotNull final String name) {
    return entityMap.get(name);
  }

  @Override
  public Collection<DungeonEntity> all() {
    return entityMap.values();
  }

  @Override
  public void add(@NotNull final String name, @NotNull final DungeonEntity dungeonEntity) {
    this.entityMap.put(name,dungeonEntity);
  }
}
