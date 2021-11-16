package tr.beyazpolis.entity.impl;

import java.util.UUID;
import tr.beyazpolis.entity.DungeonEntity;

public abstract class EntityDungeon implements DungeonEntity {

  private final String entityCustomName;
  private final int maxHealth;
  private final int attackDamage;
  private final int entityType;

  private UUID entityId;

  public EntityDungeon(final int entityType,final String entityCustomName,
                       final int maxHealth, final int attackDamage) {
    this.entityCustomName = entityCustomName;
    this.maxHealth = maxHealth;
    this.attackDamage = attackDamage;
    this.entityType = entityType;
    this.entityId = null;
  }

  @Override
  public int entityType() {
    return entityType;
  }

  @Override
  public UUID entityId() {
    return entityId;
  }

  @Override
  public void setEntityId(final UUID a) {
    this.entityId = a;
  }

  @Override
  public int attackDamage() {
    return attackDamage;
  }

  @Override
  public int getMaxHealth() {
    return maxHealth;
  }

  @Override
  public String getCustomName() {
    return entityCustomName;
  }
}
