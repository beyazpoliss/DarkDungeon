package tr.beyazpolis.commands.impl;

import org.jetbrains.annotations.NotNull;
import tr.beyazpolis.commands.CommandAdapter;
import tr.beyazpolis.commands.CommandWrapper;
import tr.beyazpolis.commands.DungeonCommand;

public class SpawnEntityCommand extends DungeonCommand {

  public SpawnEntityCommand(final String[] args) {
    super(args);
  }

  @Override
  public void invoke(@NotNull final CommandAdapter commandAdapter, @NotNull final CommandWrapper commandWrapper) {
    if (commandAdapter.senderIsPlayer()) return;
    if (!commandAdapter.senderIsOp()) return;


  }
}
