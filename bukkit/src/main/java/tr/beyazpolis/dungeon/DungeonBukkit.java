package tr.beyazpolis.dungeon;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import net.minecraft.server.v1_12_R1.EntityInsentient;
import net.minecraft.server.v1_12_R1.EntityLiving;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tr.beyazpolis.common.CommonPlugin;
import tr.beyazpolis.dungeon.teams.DungeonTeam;
import tr.beyazpolis.profile.player.DungeonPlayer;

public class DungeonBukkit extends JavaPlugin {

  @Nullable
  private CommonPlugin plugin;

  @Override
  public void onEnable() {
    this.plugin = new BukkitPlugin(this);
    plugin.onEnable();
  }

  @Override
  public void onDisable() {
    getPlugin().onDisable();
    this.plugin = null;
  }

  //vurulan oyuncunun takımında bu entity yoksa cancel

  @NotNull
  public CommonPlugin getPlugin() {
    return Optional
      .ofNullable(plugin)
      .orElseThrow(() -> new NullPointerException("CommonPlugin is null!"));
  }
}