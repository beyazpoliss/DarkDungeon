package tr.beyazpolis.entity.impl;

import tr.beyazpolis.entity.DungeonEntity;
import tr.beyazpolis.entity.DungeonLivingEntity;

public abstract class EntityLiving implements DungeonLivingEntity {

  private final DungeonEntity entity;
  public Object entityType;

  public EntityLiving(final DungeonEntity entity,
                      final Object entityType) {
    this.entity = entity;
    this.entityType = entityType;
  }

  @Override
  public DungeonEntity getDungeonEntity() {
    return entity;
  }

  @Override
  public Object getEntityType() {
    return entityType;
  }
}
