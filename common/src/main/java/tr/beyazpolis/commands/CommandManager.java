package tr.beyazpolis.commands;

import java.util.Collection;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

public interface CommandManager {

  /**
   * Returns Command by name.
   *
   * @param name command name.
   * @return Command.
   */
  @NotNull
  Command get(@NotNull final String name);

  /**
   * Returns all active command objects.
   *
   * @return all commands.
   */
  @NotNull
  Collection<Command> all();

  /**
   * Returns all command names.
   *
   * @return all command names.
   */
  @NotNull
  Collection<String> names();

  /**
   * Registers the command object.
   *
   * @param command object to be added.
   */
  void add(@NotNull final Command command);

  /**
   * Registers the commands object.
   *
   * @param command object to be added.
   */
  void add(@NotNull final Command... command);

  /**
   * Deletes the command object.
   *
   * @param command object to be removed.
   */
  void remove(@NotNull final Command command);
}
