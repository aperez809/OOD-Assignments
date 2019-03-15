package model;

/**
 * Represents the possible actions that can be performed on a Shape that is on the
 * Animation canvas.
 */
public interface Action {

  /**
   * Apply a certain Action to a given Shape.
   *
   * @param s a shape to perform the given Action on
   */
  void apply(Shape s);

  /**
   * Get the starting tick of this action.
   *
   * @return startTick representing tick at which Action began
   */
  int getStartTick();

  /**
   * Get the ending tick of this action.
   *
   * @return endTick representing tick at which Action ended
   */
  int getEndTick();

  int[] getStartState();

  int[] getEndState();
}
