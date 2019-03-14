package model;

/**
 * Represents the action of changing the dimensions of a Shape.
 *
 */
public class ChangeDims extends AbstractAction {
  private String type;
  private int height;
  private int width;

  /**
   * Constructs a ChangeDims object with all necessary data to change the dimensions of a Shape.
   *
   * @param startTick when Action starts
   * @param endTick when Action ends
   * @param height new height of Shape
   * @param width new width of Shape
   *
   * @throws IllegalArgumentException if height or width are less than or equal to zero
   */
  public ChangeDims(int startTick, int endTick, int height, int width) {
    super(startTick, endTick);
    if (height <= 0 || width <= 0) {
      throw new IllegalArgumentException("New height and width must be greater than 0");
    }
    this.type = "Change Dims";
    this.height = height;
    this.width = width;
  }

  @Override
  public void apply(Shape s) {
    s.setHeight(this.height);
    s.setWidth(this.width);
  }

  /**
   * Gets the type of action performed.
   *
   * @return String of action type
   */
  public String getType() {
    return this.type;
  }
}
