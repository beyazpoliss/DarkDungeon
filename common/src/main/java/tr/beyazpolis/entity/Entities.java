package tr.beyazpolis.entity;

import java.util.HashSet;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

public enum Entities {

  SKELETON(1,"skeleton"),
  ZOMBIE(2, "zombie"),
  WITHER(3,"wither");

  private final int id;
  private final String name;

  private Entities(final  int id,final String name){
    this.id = id;
    this.name = name;
  }

  public static int getEntityTypeByName(final String name){
    switch (name){
      case "skeleton":
        return 1;
      case "zombie":
        return 2;
      case "wither":
        return 3;
    }
    return 0;
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  @NotNull
  public static Set<String> names(){
    final HashSet<String> h = new HashSet<String>();
    for (final Entities value : values()) {
      h.add(value.getName());
    }
    return h;
  }
}
