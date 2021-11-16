package tr.beyazpolis.commands;


import org.jetbrains.annotations.NotNull;
import tr.beyazpolis.common.CommonPlugin;
import tr.beyazpolis.world.PlayerWrapper;

public interface CommandAdapter {

  boolean senderIsPlayer();

  boolean senderIsConsole();

  boolean senderIsOp();

  void senderSendMessage(@NotNull final String message);

  PlayerWrapper getPlayer();

  CommonPlugin getPlugin();
}
