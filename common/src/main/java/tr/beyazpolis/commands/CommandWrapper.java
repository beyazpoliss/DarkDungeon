package tr.beyazpolis.commands;

public class CommandWrapper {

  private final String[] args;

  public CommandWrapper(final String[] args) {
    this.args = args;
  }

  public String[] getArgs() {
    return args;
  }
}
