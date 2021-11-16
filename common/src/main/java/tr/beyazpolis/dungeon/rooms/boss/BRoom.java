package tr.beyazpolis.dungeon.rooms.boss;

import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import tr.beyazpolis.common.CommonPlugin;
import tr.beyazpolis.dungeon.rooms.DRoom;
import tr.beyazpolis.dungeon.skills.BossSkill;

public class BRoom extends DRoom implements BossRoom {

  private final List<BossSkill> skills;

  public BRoom(@NotNull final CommonPlugin commonPlugin, final int id) {
    super(commonPlugin, id);
    this.skills = new ArrayList<>();
  }

  @Override
  public List<BossSkill> getSkills() {
    return skills;
  }

  @Override
  public BossSkill getRandomSkill() {
    return null;
  }
}
