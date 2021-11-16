package tr.beyazpolis.commands;

import org.jetbrains.annotations.NotNull;

public abstract class DungeonCommand implements Command{

  private final String[] args;

  public DungeonCommand(final String[] args) {
    this.args = args;
  }

  @NotNull
  @Override
  public String[] args() {
    return args;
  }

  public abstract void invoke(@NotNull final CommandAdapter commandAdapter, @NotNull final CommandWrapper commandWrapper);

}
