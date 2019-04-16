package cs3500.animator.model;

import cs3500.animator.provider.model.ColorAdapter;
import cs3500.animator.provider.model.SizeAdapter;

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
  public Ellipse(int height, int width, Location coords, ColorAdapter color,
                 ArrayList<IAction> actions,
                 String shapeName) {
    super(height, width, coords, color, actions, shapeName);
  }

  @Override
  public Shape cloneShape() {
    return new Ellipse(
            this.getH(),
            this.getW(),
            new Location(this.getPosition().getX(), this.getPosition().getY()),
            new ColorAdapter(new Color(this.getColor().getR(), this.getColor().getB(),
                    this.getColor().getG())),
            cloneActions(this.getActions()),
            this.getShapeName());
  }


  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Ellipse)) {
      return false;
    }

    Ellipse other = (Ellipse) o;
    return this.getH() == other.getH()
            && this.getW() == other.getW()
            && this.getPosition().equals(other.getPosition())
            && this.getColor().equals(other.getColor())
            && this.getShapeName().equals(other.getShapeName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(
            this.getH(), this.getW(), this.getPosition(), this.getColor(),
            this.getShapeName());
  }

  @Override
  public String getShapeType() {
    return "ellipse";
  }

  @Override
  public void setColor(Color color) {
    this.color = new ColorAdapter(color);
  }

  @Override
  public SizeAdapter getSize() {
    return new SizeAdapter(this.getH(), this.getW());
  }
}
