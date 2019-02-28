package model;

import java.awt.*;

public class ChangeColor implements Action {
  private String type;
  private int r;
  private int g;
  private int b;

  public ChangeColor(int r, int g, int b) {
    if (r <= 0 || g <= 0 || b <= 0) {
      throw new IllegalArgumentException("New color values must all be greater than or equal to 0");
    }
    this.type = "Change Color";
    this.r = r;
    this.g = g;
    this.b = b;
  }

  @Override
  public void apply(Shape s) {
    s.setColor(new Color(this.r, this.g, this.b));
  }
}
