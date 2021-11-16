package tr.beyazpolis.dungeon.command;

import java.util.List;
import net.minecraft.server.v1_12_R1.DataWatcher;
import net.minecraft.server.v1_12_R1.EntityZombie;
import net.minecraft.server.v1_12_R1.EnumItemSlot;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.PacketPlayOutEntityEquipment;
import net.minecraft.server.v1_12_R1.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_12_R1.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_12_R1.PlayerConnection;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tr.beyazpolis.commands.CommandAdapter;
import tr.beyazpolis.commands.CommandWrapper;
import tr.beyazpolis.common.CommonPlugin;
import tr.beyazpolis.dungeon.DungeonManager;
import tr.beyazpolis.dungeon.world.BukkitLocation;
import tr.beyazpolis.dungeon.world.BukkitPlayer;
import tr.beyazpolis.profile.player.DungeonPlayer;
import tr.beyazpolis.world.LocationWrapper;
import tr.beyazpolis.world.PlayerWrapper;

public class BukkitDungeonCommand implements TabExecutor {

  private final CommonPlugin commonPlugin;

  public BukkitDungeonCommand(final CommonPlugin commonPlugin) {
    this.commonPlugin = commonPlugin;
  }

  @Override
  public boolean onCommand(final CommandSender commandSender, org.bukkit.command.Command cmd, final String s, final String[] strings) {

    final Player player = (Player) commandSender;
    DungeonManager.createDungeon(commonPlugin,DungeonManager.createDungeonTeam(DungeonPlayer.of(player.getName(),player.getUniqueId())));

    /*this.commonPlugin.getCommandManager().all().forEach(command -> command.invoke(new CommandAdapter() {
      @Override
      public boolean senderIsPlayer() {
        return !(commandSender instanceof Player);
      }

      @Override
      public boolean senderIsConsole() {
        return !(commandSender instanceof Player);
      }

      @Override
      public boolean senderIsOp() {
        return commandSender.isOp();
      }

      @Override
      public void senderSendMessage(@NotNull final String message) {
        commandSender.sendMessage(message);
      }

      @Override
      @Nullable
      public PlayerWrapper getPlayer() {
        if (senderIsPlayer()) return null;
        final Player player = (Player) commandSender;
        final LocationWrapper locationWrapper = new BukkitLocation(player.getLocation());
        return new BukkitPlayer(player.getName(),player.getUniqueId(),locationWrapper);
      }

      @Override
      public CommonPlugin getPlugin() {
        return commonPlugin;
      }
    },new CommandWrapper(strings)));*/
    return true;
  }

  @Override
  public List<String> onTabComplete(final CommandSender commandSender, final org.bukkit.command.Command command, final String s, final String[] strings) {
    return null;
  }
}
