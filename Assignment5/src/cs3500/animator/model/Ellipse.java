package cs3500.animator.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents an Ellipse Shape on the canvas.
 *
 */
public class Ellipse extends AbstractShape {

  /**
   * Constructs an Ellipse object.
   *
   * @param height height of OVal
   * @param width width of Ellipse
   * @param coords position of Ellipse on canvas
   * @param color color of Ellipse
   */
  public Ellipse(int height, int width, Location coords, Color color,
                 ArrayList<IAction> actions, String shapeName) {
    super(height, width, coords, color, actions, shapeName);
  }

  @Override
  public Shape cloneShape() {
    return new Ellipse(
            this.getHeight(),
            this.getWidth(),
            new Location(this.getCoords().getX(), this.getCoords().getY()),
            new Color(this.getColor().getRed(), this.getColor().getBlue(),
                    this.getColor().getGreen()),
            cloneActions(this.getActions()),
            this.getShapeName());
  }


  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Ellipse)) {
      return false;
    }

    Ellipse other = (Ellipse) o;
    return this.getHeight() == other.getHeight()
            && this.getWidth() == other.getWidth()
            && this.getCoords().equals(other.getCoords())
            && this.getColor().equals(other.getColor())
            && this.getShapeName().equals(other.getShapeName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(
            this.getHeight(), this.getWidth(), this.getCoords(),
            this.getColor(), this.getShapeName());
  }
}
