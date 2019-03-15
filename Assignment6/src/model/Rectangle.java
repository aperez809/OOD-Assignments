package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a Rectangle shape on the canvas.
 *
 */
public class Rectangle extends AbstractShape {
  private String shapeName;

  /**
   * Constructs a Rectangle object.
   *
   * @param height height of Rectangle
   * @param width width of Rectangle
   * @param coords position of Rectangle on canvas
   * @param color color of Rectangle
   */
  public Rectangle(int height, int width, Location coords, Color color) {
    super(height, width, coords, color);
    this.shapeName = "Rectangle";
  }

  @Override
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
