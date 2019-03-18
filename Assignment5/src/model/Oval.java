package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents an Oval Shape on the canvas.
 *
 */
public class Oval extends AbstractShape {

  /**
   * Constructs an Oval object.
   *
   * @param height height of OVal
   * @param width width of Oval
   * @param coords position of Oval on canvas
   * @param color color of Oval
   */
  public Oval(int height, int width, Location coords, Color color, ArrayList<IAction> actions, String shapeName) {
    super(height, width, coords, color, actions, shapeName);
  }



  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Oval)) {
      return false;
    }

    Oval other = (Oval) o;
    return this.getHeight() == other.getHeight()
            && this.getWidth() == other.getWidth()
            && this.getCoords().equals(other.getCoords())
            && this.getColor().equals(other.getColor())
            && this.getShapeName().equals(other.getShapeName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(
            this.getHeight(), this.getWidth(), this.getCoords(), this.getColor(), this.getShapeName());
  }
}
