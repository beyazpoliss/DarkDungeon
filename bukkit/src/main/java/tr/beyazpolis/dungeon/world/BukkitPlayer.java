package tr.beyazpolis.dungeon.world;

import java.util.UUID;
import net.minecraft.server.v1_12_R1.SoundEffects;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import tr.beyazpolis.profile.player.DungeonPlayer;
import tr.beyazpolis.world.LocationWrapper;
import tr.beyazpolis.world.PlayerWrapper;

public class BukkitPlayer extends PlayerWrapper {

  public BukkitPlayer(final String name,
                      final UUID uuid,
                      final LocationWrapper locationWrapper) {
    super(name, uuid, locationWrapper);
  }

  @Override
  public Object getPlayerConnection() {
    final Player p = Bukkit.getPlayer(getName());
    return ((CraftPlayer) p).getHandle().playerConnection;
  }

  @Override
  public void teleportPlayer() {
    final BukkitLocation location = (BukkitLocation) getLocationWrapper();
    Bukkit.getPlayer(getName()).teleport(location.getLocation());
  }

  @Override
  public DungeonPlayer dungeonPlayer() {
    return null;
  }

  @Override
  public void sendMessage(final String text) {

  }

}
