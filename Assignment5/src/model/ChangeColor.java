package model;

import java.awt.*;

public class ChangeColor extends AbstractAction {
  private String type;
  private int startTick;
  private int endTick;
  private int r;
  private int g;
  private int b;

  public ChangeColor(int startTick, int endTick, int r, int g, int b) {
    super(startTick, endTick);
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

  public String getType() {
    return this.type;
  }
}
