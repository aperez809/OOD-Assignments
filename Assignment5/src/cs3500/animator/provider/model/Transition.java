package cs3500.animator.provider.model;

/**
 * This class represents the implentation of a transition.
 * <p>
 *   A transition is characterized by a beginning time, an end time, and the changes within such
 *   time duration, including the starting states of x-position, y-position, width, height, and
 *   RGB-color code, with the end states of all the above properties.
 * </p>
 */
public class Transition {
  public int beginTime;
  public int endTime;
  public int duration;
  public int x1;
  public int y1;
  public int w1;
  public int h1;
  public int r1;
  public int g1;
  public int b1;
  public int x2;
  public int y2;
  public int w2;
  public int h2;
  public int r2;
  public int g2;
  public int b2;

  /**
   * Initialize the transition to the specified time and start/end state.
   * @param beginTime the tick to start the transition
   * @param endTime the tick to end the transition
   * @param x1 the initial x-position
   * @param y1 the initial y-position
   * @param w1 the initial width
   * @param h1 the initial height
   * @param r1 the initial R-color code
   * @param g1 the initial G-color code
   * @param b1 the initial B-color code
   * @param x2 the result x-position
   * @param y2 the result y-position
   * @param w2 the result width
   * @param h2 the result height
   * @param r2 the result R-color code
   * @param g2 the result G-color code
   * @param b2 the result B-color code
   */
  public Transition(
      int beginTime, int endTime,
      int x1, int y1, int w1, int h1, int r1, int g1, int b1,
      int x2, int y2, int w2, int h2, int r2, int g2, int b2
  ) {
    if (beginTime < 0 || beginTime > endTime) {
      throw new IllegalArgumentException("Invalid transition begin-end time");
    }

    this.beginTime = beginTime;
    this.endTime = endTime;
    this.duration = endTime - beginTime;
    this.x1 = x1;
    this.y1 = y1;
    this.w1 = w1;
    this.h1 = h1;
    this.r1 = r1;
    this.g1 = g1;
    this.b1 = b1;
    this.x2 = x2;
    this.y2 = y2;
    this.w2 = w2;
    this.h2 = h2;
    this.r2 = r2;
    this.g2 = g2;
    this.b2 = b2;
  }
}
