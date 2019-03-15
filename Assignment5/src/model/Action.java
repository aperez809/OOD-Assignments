package model;

/**
 * Abstract class that represents multiple different types of Actions that can
 * be performed on a Shape.
 */
public abstract class AbstractAction implements Action {
  private int startTick;
  private int endTick;

  private int startX;
  private int endX;

  private int startY;
  private int endY;

  private int startHeight;
  private int endHeight;

  private int startWidth;
  private int endWidth;

  private int startR;
  private int endR;

  private int startG;
  private int endG;

  private int startB;
  private int endB;



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

    this.startTick = startTick;
    this.endTick = endTick;

  }

  public AbstractAction(int startTick, int endTick, int startX, int endX, int startY, int endY, int startHeight, int endHeight, int startWidth, int endWidth, int startR, int endR, int startG, int endG, int startB, int endB) {
    if (startTick < 0 || endTick < 0 || endTick - startTick < 0) {
      throw new IllegalArgumentException(
              "Start tick, end tick, and elapsed time must be greater than or equal to 0");
    }

    this.startTick = startTick;
    this.endTick = endTick;
    this.startX = startX;
    this.endX = endX;
    this.startY = startY;
    this.endY = endY;
    this.startHeight = startHeight;
    this.endHeight = endHeight;
    this.startWidth = startWidth;
    this.endWidth = endWidth;
    this.startR = startR;
    this.endR = endR;
    this.startG = startG;
    this.endG = endG;
    this.startB = startB;
    this.endB = endB;
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

  public int[] getStartState() {
    return new int[]{
            startTick, startX, startY, startHeight, startWidth, startR, startG, startB};
  }

  public int[] getEndState() {
    return new int[]{
            endTick, endX, endY, endHeight, endWidth, endR, endG, endB};
  }

}
