package cs3500.animator.model;

/**
 * Abstract class that represents multiple different types of Actions that can
 * be performed on a Shape.
 */
public class Action implements IAction {
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
   * Constructs an Action object.
   *
   * @param startTick tick at which the IAction starts
   * @param endTick tick at which the IAction ends
   *
   * @throws IllegalArgumentException if startTick or endTick < 0 or if endTick - startTick
   *         (time to complete action) is negative.
   */
  public Action(int startTick, int endTick) {

    this.startTick = startTick;
    this.endTick = endTick;
  }

  /**
   * Constructor that takes in the entire set of possible parameters for the Action to be made.
   *
   * @param startTick tick it starts on
   * @param endTick tick it ends on
   * @param startX beginning X position
   * @param endX ending X position
   * @param startY beginning Y position
   * @param endY ending Y position
   * @param startHeight beginning height
   * @param endHeight ending height
   * @param startWidth beginning width
   * @param endWidth ending width
   * @param startR beginning red value
   * @param endR ending red value
   * @param startG beginning green value
   * @param endG ending green value
   * @param startB beginning blue value
   * @param endB ending blue value
   */
  public Action(int startTick, int endTick, int startX, int endX, int startY, int endY,
                int startHeight, int endHeight, int startWidth, int endWidth, int startR,
                int endR, int startG, int endG, int startB, int endB) {
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
   * copy constructor that makes a deep copy of the Action object.
   * @param other Action to be copied
   */
  public Action(Action other) {
    this.startTick = other.startTick;
    this.endTick = other.endTick;

    this.startX = other.startX;
    this.endX = other.endX;
    this.startY = other.startY;
    this.endY = other.endY;
    this.startHeight = other.startHeight;
    this.endHeight = other.endHeight;
    this.startWidth = other.startWidth;
    this.endWidth = other.endWidth;
    this.startR = other.startR;
    this.endR = other.endR;
    this.startG = other.startG;
    this.endG = other.endG;
    this.startB = other.startB;
    this.endB = other.endB;
  }

  /**
   * Get the starting tick of this action.
   *
   * @return startTick representing tick at which IAction began
   */
  @Override
  public int getStartTick() {
    return this.startTick;
  }

  @Override
  public IAction cloneAction() {
    return new Action(this);
  }

  /**
   * Get the ending tick of this action.
   *
   * @return endTick representing tick at which IAction ended
   */
  @Override
  public int getEndTick() {
    return this.endTick;
  }

  public int[] getStartState() {
    return new int[]{startTick, startX, startY, startHeight, startWidth, startR, startG, startB};
  }

  public int[] getEndState() {
    return new int[]{endTick, endX, endY, endHeight, endWidth, endR, endG, endB};
  }

}
