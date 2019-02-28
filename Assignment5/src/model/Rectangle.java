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
    this.documentChange("Motion",
            this.shapeName,
            String.valueOf(startTick));

    for (Action a : actions) {
      a.apply(this);
    }

    this.documentChange("","",String.valueOf(endTick));
    this.trackedState.append("\n");
  }
}
