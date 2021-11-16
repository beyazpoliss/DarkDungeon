package tr.beyazpolis.entity;


import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import tr.beyazpolis.common.CommonPlugin;
import tr.beyazpolis.config.Config;

public interface DungeonEntity {

  int getMaxHealth();

  String getCustomName();

  int attackDamage();

  int entityType();

  UUID entityId();

  void setEntityId(final UUID a);

  static DungeonEntity createEntity(@NotNull final CommonPlugin commonPlugin, @NotNull final Config config, final int roomId,final String mobName){
    return commonPlugin.createEntity(Entities.getEntityTypeByName(mobName),
      ((String)config.get("Rooms.room" + roomId +".Entities."+ mobName +".settings.name")),
      ((int)config.get("Rooms.room" + roomId + ".Entities."+ mobName +".settings.health")),
      ((int) config.get("Rooms.room" + roomId +".Entities."+ mobName +".settings.damage")));
  }
}
