package model;

/**
 * Represents the action of moving a Shape.
 *
 */
public class Move extends AbstractAction {
  private String type;
  private int x;
  private int y;

  /**
   * Constructs a Move function object with all necessary data to move the Shape.
   *
   * @param startTick when Action begins
   * @param endTick when Action ends
   * @param x new x position of Shape
   * @param y new y position of Shape
   */
  public Move(int startTick, int endTick, int x, int y) {
    super(startTick,endTick);
    this.type = "Motion";
    this.x = x;
    this.y = y;
  }

  @Override
  public void apply(Shape s) {
    s.setCoords(new Location(x, y));
  }

  /**
   * Gets the type of Action performed.
   *
   * @return String of action type
   */
  public String getType() {
    return this.type;
  }
}
