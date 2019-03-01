package model;


/**
 * Abstract class that represents multiple different types of Actions that can
 * be performed on a Shape.
 */
public abstract class AbstractAction implements Action {
  private int startTick;
  private int endTick;

  /**
   * Constructs an AbstractAction object.
   *
   * @param startTick tick at which the Action starts
   * @param endTick tick at which the Action ends
   *
   * @throws IllegalArgumentException if startTick or endTick < 0 or if endTick - startTick
   *         (time to complete action) is negative.
   */
  public AbstractAction(int startTick, int endTick) {
    if (startTick < 0 || endTick < 0 || endTick - startTick < 0) {
      throw new IllegalArgumentException(
              "Start tick, end tick, and elapsed time must be greater than or equal to 0");
    }

    this.startTick = startTick;
    this.endTick = endTick;
  }

  /**
   * Apply a certain Action to a given Shape.
   *
   * @param s a shape to perform the given Action on
   */
  @Override
  public abstract void apply(Shape s);


  /**
   * Get the starting tick of this action.
   *
   * @return startTick representing tick at which Action began
   */
  @Override
  public int getStartTick() {
    return this.startTick;
  }

  /**
   * Get the ending tick of this action.
   *
   * @return endTick representing tick at which Action ended
   */
  @Override
  public int getEndTick() {
    return this.endTick;
  }

}
