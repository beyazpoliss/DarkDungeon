package tr.beyazpolis.dungeon;

import com.sun.jdi.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.MinecraftKey;
import net.minecraft.server.v1_12_R1.PacketPlayOutNamedSoundEffect;
import net.minecraft.server.v1_12_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_12_R1.SoundCategory;
import net.minecraft.server.v1_12_R1.SoundEffect;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tr.beyazpolis.common.CommonPlugin;
import tr.beyazpolis.dungeon.rooms.DungeonRoom;
import tr.beyazpolis.dungeon.teams.DungeonTeam;
import tr.beyazpolis.dungeon.world.BukkitLocation;
import tr.beyazpolis.world.LocationWrapper;
import tr.beyazpolis.world.MethodAdapter;

public class BukkitMethodAdapter implements MethodAdapter {

  private final CommonPlugin commonPlugin;

  public BukkitMethodAdapter(final CommonPlugin commonPlugin) {
    this.commonPlugin = commonPlugin;
  }

  @Override
  public CommonPlugin getPlugin() {
    return commonPlugin;
  }

  @Override
  public boolean isOnline(@NotNull final String playerName) {
    if (Bukkit.getPlayer(playerName) == null) return false;
    return Bukkit.getPlayer(playerName).isOnline();
  }

  @Override
  public boolean isOp(@NotNull final String playerName) {
    return Bukkit.getPlayer(playerName).isOp();
  }

  @Override
  public boolean hasPermission(@NotNull final String playerName, @NotNull final String permission) {
    return Bukkit.getPlayer(playerName).hasPermission(permission);
  }

  @Override
  public void teleportPlayer(@NotNull final String playerName, @NotNull final LocationWrapper wrapper) {
    Bukkit.getPlayer(playerName).teleport(new Location(this.getWorld(wrapper.getWorldName()),wrapper.getX(),wrapper.getY(),wrapper.getZ()));
  }

  @Override
  public World getWorld(@NotNull final String worldName) {
    return Bukkit.getWorld(worldName);
  }

  @Override
  public void sendMessage(@NotNull final String playerName,@NotNull final String message) {
    if (!this.isOnline(playerName)) return;
    if (this.getPlayer(playerName) == null) return;
    Objects.requireNonNull(this.getPlayer(playerName)).sendMessage(ChatColor.translateAlternateColorCodes('&',message));
  }

  @Override
  public Player getPlayer(@NotNull final String playerName) {
    if (!isOnline(playerName))
      return null;
    return Bukkit.getPlayer(playerName);
  }

  @Override
  public void startRoomTimer(@NotNull final DungeonTeam dungeonTeam) {
    final String joinMessage = dungeonTeam.currentRoom().getRoomMessages().joinMessage();
    dungeonTeam.players().forEach(dungeonPlayer -> commonPlugin.getMethodAdapter().sendMessage(dungeonPlayer.name(), joinMessage));
    Bukkit.getScheduler().runTaskLaterAsynchronously((Plugin) commonPlugin.getPluginObject(), new Runnable() {
      @Override
      public void run() {
        final LocationWrapper spawnLoc = dungeonTeam.currentRoom().spawnLocation();
        dungeonTeam.players().forEach(dungeonPlayer -> {
          teleportPlayer(dungeonPlayer.name(),spawnLoc);
          sendMessage(dungeonPlayer.name(),dungeonTeam.currentRoom().getRoomMessages().startMessage());
          sendTitle(getPlayer(dungeonPlayer.name()), dungeonTeam.currentRoom().getRoomMessages().startTitleMessage(),dungeonTeam.currentRoom().getRoomMessages().startTitleMiniMessage(),7,7,7);
          sendSound(getPlayer(dungeonPlayer.name()),dungeonTeam.currentRoom().getRoomSounds().joinSoundKey(),new BukkitLocation(Objects.requireNonNull(getPlayer(dungeonPlayer.name())).getLocation()));
        });
        DungeonManager.spawnRoom(commonPlugin,dungeonTeam);
      }
    },dungeonTeam.currentRoom().startTime() * 20);
  }

  public void sendSound(final Player player,final String sKey,final LocationWrapper locationWrapper){
    final CraftPlayer cp = (CraftPlayer) player;
    final MinecraftKey mKey = new MinecraftKey(sKey);
    final SoundEffect soundEffect = SoundEffect.a.get(mKey);
    if (soundEffect == null) {
      System.out.println("Sound is null! DungeonRoom");
      return;
    }
    cp.getHandle().playerConnection.sendPacket(new PacketPlayOutNamedSoundEffect(
      soundEffect, SoundCategory.PLAYERS, locationWrapper.getX(), locationWrapper.getY(), locationWrapper.getZ(),  1, 1f));
  }

  public void sendTitle(final Player player,
                               final String title,
                               final String subtitle,
                               final int fadeIn,
                               final int time,
                               final int fadeOut) {
    final IChatBaseComponent titleComponent = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + ChatColor.translateAlternateColorCodes('&',title) + "\"}");
    final IChatBaseComponent subtitleComponent = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + ChatColor.translateAlternateColorCodes('&',subtitle) + "\"}");

    final PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleComponent);
    final PacketPlayOutTitle subtitlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, subtitleComponent);
    final PacketPlayOutTitle lengthPacket = new PacketPlayOutTitle(fadeIn, time, fadeOut);

    ((CraftPlayer)player).getHandle().playerConnection.sendPacket(lengthPacket);
    ((CraftPlayer)player).getHandle().playerConnection.sendPacket(titlePacket);
    ((CraftPlayer)player).getHandle().playerConnection.sendPacket(subtitlePacket);
  }
}
