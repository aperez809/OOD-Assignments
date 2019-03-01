package model;

import com.sun.org.apache.regexp.internal.RE;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

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

  @Override
  public Shape makeCopy() {
    return new Rectangle(this.getHeight(), this.getWidth(), this.getCoords(), this.getColor());
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Rectangle)) {
      return false;
    }

    Rectangle other = (Rectangle) o;
    return this.getHeight() == other.getHeight()
            && this.getWidth() == other.getWidth()
            && this.getCoords().equals(other.getCoords())
            && this.getColor().equals(other.getColor())
            && this.shapeName.equals(other.shapeName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
            this.getHeight(), this.getWidth(), this.getCoords(), this.getColor(), this.shapeName);
  }
}
