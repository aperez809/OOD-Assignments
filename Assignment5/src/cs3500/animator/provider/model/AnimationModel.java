package cs3500.animator.provider.model;

import cs3500.animator.model.Shape;
import java.util.List;

/**
 * This interface specifies the operation of a 2D animation model.
 *
 * <p>
 *   A 2D animation is characterized by a shape of size(a,b), at a position(x,y), of color(r,g,b)
 *   and a number of transitions of such shape over a period of time.
 *   The model allows user to add a shape to the model addShape(). The shape should have the
 *   desired list of animations, if any is needed, loaded within it.
 *   The model stores these shapes by the order they are put into the model, which can also be
 *   retrieved, modified, removed by the unique name of each shape.
 *   The transitions added to the model are added on top of a list through the shape, thus
 *   overlapping transitions are not a concern. The animations of a particular shape could be
 *   retrieved, added, and popped accordingly.
 * </p>
 */
public interface AnimationModel {
  /**
   * Get all the shapes in the model.
   * @return
   */
  List<Shape> getShapes();

  /**
   * Get the shape matching a specified name in the model.
   * @param name the name of the shape
   * @return the shape
   */
  Shape getShape(String name);

  /**
   * Add a new shape to the model.
   * @param sh the shapes
   */
  void addShape(Shape... sh);

  /**
   * Add a new animation to the end of the shape's transition list.
   * @param name the name of the shape to add the transition to
   * @param t the new transition
   */
  void addTransition(String name, Transition t);

  /**
   * Advance the model to the next tick.
   */
  void tick();

  /**
   * Return whether the model could still animate.
   * @return
   */
  boolean canTick();

  /**
   * Return the current tick.
   * @return
   */
  int getCurrentTick();

  /**
   * Reset the model.
   */
  void reset();

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
   * Remove the shape with such name from the model.
   * @param name the name of the shape
   */
  void removeShape(String name);

}
