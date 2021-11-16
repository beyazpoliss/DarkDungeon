package tr.beyazpolis.commands;

import org.jetbrains.annotations.NotNull;
import tr.beyazpolis.common.CommonPlugin;

public interface CommandRunner {

  /**
   * The method that runs the command.
   */
  void invoke(@NotNull final CommandAdapter commandAdapter, @NotNull final CommandWrapper commandWrapper);
}
