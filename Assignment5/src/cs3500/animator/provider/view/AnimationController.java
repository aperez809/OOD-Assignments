package cs3500.animator.provider.view;

/**
 * This interface represents the operations of the controller of the Model-View-Controller system
 * of animation.
 *
 * <p>
 *   The controller allows to render the model to the view once with renderView(), advance the mode
 *   itself to the next tick with nextTick() or as a whole with animate().
 *
 *   The controller also allows to set the bounds of the view, and add new shape/animation to the
 *   model.
 * </p>
 *
 */
public interface AnimationController {
  /**
   * Render the model the the view once.
   */
  void renderView();

  /**
   * Advance the model to the next tick.
   */
  void nextTick();

  /**
   * Animate the model till the end.
   */
  void start();

  /**
   * Set the bounds of the view.
   * @param x the x-coordinate of the top left point of the view
   * @param y the y-coordinate of the top left point of the view
   * @param w the width of the view
   * @param h the height of the view
   */
  void setBounds(int x, int y, int w, int h);

  /**
   * Add a new shape to the model.
   * @param name the name of the shape
   * @param type the type of shape
   */
  void addShape(String name, String type);

  /**
   * Add a new transition to the model.
   * @param name the name of the shape to add the transition to
   * @param t1 the beginning time of the transition
   * @param x1 the beginning x-coordinate of the transition
   * @param y1 the beginning y-coordinate of the transition
   * @param w1 the beginning width of the transition
   * @param h1 the beginning height of the transition
   * @param r1 the beginning R-color code of the transition
   * @param g1 the beginning G-color code of the transition
   * @param b1 the beginning B-color code of the transition
   * @param t2 the ending time of the transition
   * @param x2 the ending x-coordinate of the transition
   * @param y2 the ending y-coordinate of the transition
   * @param w2 the ending width of the transition
   * @param h2 the ending height of the transition
   * @param r2 the ending R-color code of the transition
   * @param g2 the ending G-color code of the transition
   * @param b2 the ending B-color code of the transition
   */
  void addTransition(String name,
                     int t1, int x1, int y1, int w1, int h1, int r1, int g1, int b1,
                     int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2);

  /**
   * Adds a keyframe to the animation.
   * @param name The name of the shape
   * @param t    The time for this keyframe
   * @param x    The x-position of the shape
   * @param y    The y-position of the shape
   * @param w    The width of the shape
   * @param h    The height of the shape
   * @param r    The red color-value of the shape
   * @param g    The green color-value of the shape
   * @param b    The blue color-value of the shape
   * @return
   */
  void addKeyFrame(
      String name, int t, int x, int y, int w, int h, int r, int g, int b);

  /**
   * Delete a keyframe from the animation.
   * @param name The name of the shape
   * @param t    The time for this keyframe
   * @return
   */
  void deleteKeyFrame(String name, int t);

  /**
   * Play/pause the animation.
   */
  void togglePause();

  /**
   * Restart the animation.
   */
  void restart();

  /**
   * Toggle the looping option.
   */
  void toggleLoop();

  /**
   * Get the speed of the animation
   * @return the number of ticks per second
   */
  int getSpeed();

  /**
   * Set the speed of the animation.
   * @param speed the number of ticks per second
   */
  void setSpeed(int speed);

  /**
   * Get the loop option.
   * @return true if loop is on
   */
  boolean getLoop();

  /**
   * Return whether the animation is playing
   * @return true if the animation is playing
   */
  boolean getPlaying();
}
