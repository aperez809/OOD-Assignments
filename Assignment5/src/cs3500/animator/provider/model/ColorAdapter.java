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
}
