package tr.beyazpolis.world;

public abstract class WorldWrapper implements DungeonWorld {

  private final MethodAdapter methodAdapter;

  public WorldWrapper(final MethodAdapter methodAdapter) {
    this.methodAdapter = methodAdapter;
  }

  public MethodAdapter getMethodAdapter() {
    return methodAdapter;
  }



}
