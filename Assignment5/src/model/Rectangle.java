package model;

import java.awt.*;
import java.util.ArrayList;

public class Rectangle extends AbstractShape {
  private String shapeName;

  public Rectangle(int height, int width, Location coords, Color color) {
    super(height, width, coords, color);
    this.shapeName = "Rectangle";
  }

  public void execute(ArrayList<Action> actions, int startTick, int endTick) {
    super.execute(actions, startTick, endTick, this.shapeName);
  }
}
