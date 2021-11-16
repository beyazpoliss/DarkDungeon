package tr.beyazpolis.commands;

import org.jetbrains.annotations.NotNull;
import tr.beyazpolis.common.CommonPlugin;

public interface Command extends CommandRunner{
  /**
   * @return command args.
   */
  @NotNull
  String[] args();
}
