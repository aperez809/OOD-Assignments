package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents an Oval Shape on the canvas.
 *
 */
public class Oval extends AbstractShape {
  private String shapeName;

  /**
   * Constructs an Oval object.
   *
   * @param height height of OVal
   * @param width width of Oval
   * @param coords position of Oval on canvas
   * @param color color of Oval
   */
  public Oval(int height, int width, Location coords, Color color) {
    super(height, width, coords, color);
    this.shapeName = "Oval";
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
    if (!(o instanceof Oval)) {
      return false;
    }

    Oval other = (Oval) o;
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
