package cs3500.animator.model;

import cs3500.animator.provider.model.ColorAdapter;
import cs3500.animator.provider.model.SizeAdapter;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a Rectangle shape on the canvas.
 *
 */
public class Rectangle extends AbstractShape {


  /**
   * Constructs a Rectangle object.
   *
   * @param height height of Rectangle
   * @param width width of Rectangle
   * @param coords position of Rectangle on canvas
   * @param color color of Rectangle
   */
  public Rectangle(int height, int width, Location coords, ColorAdapter color, ArrayList<IAction> actions,
                   String shapeName) {
    super(height, width, coords, color, actions, shapeName);
  }

  @Override
  public Shape cloneShape() {
    return new Rectangle(
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
    if (!(o instanceof Rectangle)) {
      return false;
    }

    Rectangle other = (Rectangle) o;
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
    return "rectangle";
  }

  @Override
  public SizeAdapter getSize() {
    return this.getSize();
  }

  @Override
  public void setColor(Color color) {
    this.setColor(color);
  }
}
