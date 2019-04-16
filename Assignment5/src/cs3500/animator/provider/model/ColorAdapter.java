package cs3500.animator.provider.model;

import java.awt.Color;

public class ColorAdapter {

  private Color c;

  public ColorAdapter(Color c) {
    this.c = c;
  }

  public int getR() {
    return c.getRed();
  }

  public int getG() {
    return c.getGreen();
  }

  public int getB() {
    return c.getBlue();
  }

  public boolean equals(Object o) {
    if (!(o instanceof ColorAdapter)) {
      return false;
    }

    ColorAdapter other = (ColorAdapter) o;

    return this.c.equals(other.c);
  }
}
