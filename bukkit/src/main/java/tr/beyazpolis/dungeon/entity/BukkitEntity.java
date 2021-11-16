package tr.beyazpolis.dungeon.entity;

import tr.beyazpolis.entity.impl.EntityDungeon;

public class BukkitEntity extends EntityDungeon {

  public BukkitEntity(final int entityType,
                      final String entityCustomName,
                      final int maxHealth,
                      final int attackDamage) {
    super(entityType, entityCustomName, maxHealth, attackDamage);
  }
}
