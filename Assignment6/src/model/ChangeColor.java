package model;

import java.awt.Color;

/**
 * Represents the action of change the color of a Shape.
 *
 */
public class ChangeColor extends AbstractAction {
  private String type;
  private int r;
  private int g;
  private int b;


  /**
   * Constructs a ChangeColor function object with all the data needed to change the Shape.
   *
   * @param startTick when change starts
   * @param endTick when change ends
   * @param r red value of new color
   * @param g green value of new color
   * @param b blue value of new color
   *
   * @throws IllegalArgumentException if r, g, or b values are less than 0
   */
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

  /**
   * Gets the type of action performed.
   *
   * @return String of action
   */
  public String getType() {
    return this.type;
  }
}
