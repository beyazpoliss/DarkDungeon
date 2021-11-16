package tr.beyazpolis.dungeon.rooms.boss;

import java.util.HashMap;
import java.util.List;
import tr.beyazpolis.dungeon.rooms.DungeonRoom;
import tr.beyazpolis.dungeon.skills.BossSkill;

public interface BossRoom extends DungeonRoom {

  List<BossSkill> getSkills();

  BossSkill getRandomSkill();


}
