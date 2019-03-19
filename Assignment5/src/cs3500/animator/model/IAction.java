package cs3500.animator.model;

/**
 * Represents the possible actions that can be performed on a Shape that is on the
 * Animation canvas.
 */
public interface IAction {
  /**
   * Get the starting tick of this action.
   *
   * @return startTick representing tick at which IAction began
   */
  int getStartTick();

  /**
   * Get the ending tick of this action.
   *
   * @return endTick representing tick at which IAction ended
   */
  int getEndTick();

  int[] getStartState();

  int[] getEndState();

  IAction cloneAction();
}
