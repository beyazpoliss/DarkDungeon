package tr.beyazpolis.commands.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import tr.beyazpolis.commands.Command;
import tr.beyazpolis.commands.CommandManager;

public class ComandManager implements CommandManager {

  private final Map<String, Command> commands;

  public ComandManager(){
    this.commands = new HashMap<>();
  }

  @NotNull
  @Override
  public Command get(@NotNull final String name) {
    return commands.get(name);
  }

  @NotNull
  @Override
  public Collection<Command> all() {
    return commands.values();
  }

  @Override
  public void add(@NotNull final Command command){
    this.commands.put(command.args()[0], command);
  }

  @Override
  public void add(@NotNull final Command... command) {
    for (final Command c : command)
      this.commands.put(c.args()[0],c);
  }

  @Override
  public void remove(@NotNull final Command command){
    this.commands.remove(command.args()[0]);
  }

  @NotNull
  @Override
  public Collection<String> names() {
    return commands.keySet();
  }
}
