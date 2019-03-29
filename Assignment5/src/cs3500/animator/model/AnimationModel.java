package cs3500.animator.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;


/**
 * Represents that cs3500.animator.model for creating images and moving them
 * around a canvas.
 */
public interface AnimationModel {

  /**
   * Adds a Shape to the model's list of Shape (without any Actions to go along with it).
   *
   * @param s Shape to add to the shape map
   *
   * @throws IllegalArgumentException if the given Shape already exists in model
   */
  void addShape(Shape s);

  /**
   * Removes a Shape (with the given name) from the model's list of Shape.
   *
   * @param name name of Shape to remove from the shape map
   *
   * @throws IllegalArgumentException if the given Shape does not exist in the shape map
   */
  void removeShape(String name);

  /**
   * Adds an IAction to the given shape's action map while shape is in the model's shape map.
   *
   * @param a IAction to add to the Shape's list of Actions
   * @param name name of Shape to add the Action to
   *
   * @throws IllegalArgumentException if the given IAction overlaps with an existing one
   */
  void addAction(String name, IAction a);

  /**
   * Removes an IAction from the given shape's action map while shape is in the model's shape map.
   *
   * @param a IAction to remove from the Shape's list of Actions
   * @param name name of Shape to remove the Action from
   *
   * @throws IllegalArgumentException if the given shape does not exist in the shape map or the
   *         IAction does not exist in the given Shape's action map
   */
  void removeAction(String name, IAction a);

  /**
   * Get the state of the animation (which Actions have been performed).
   *
   * @return String log of the Actions
   */
  String getAnimState();

  /**
   * Get list of Actions that a Shape has been set to perform.
   *
   * @param s the given Shape
   * @return list of Actions
   */
  ArrayList<IAction> getShapeActions(Shape s);

  /**
   * Get list of Shapes in the cs3500.animator.model.
   *
   * @return list of Shapes
   */
  ArrayList<Shape> getShapes();

  /**
   * Get the set of Shapes in the cs3500.animator.model along with their corresponding
   * IAction lists.
   *
   * @return LinkedHashMap of Shapes and list of IAction
   */
  LinkedHashMap<Shape, ArrayList<IAction>> getScript();


  /**
   * Setter method to set the leftmost x value on the screen for the canvas.
   */
  void setMaxX(int x);

  /**
   * Setter method to set the uppermost y value on the screen for the canvas.
   */
  void setMaxY(int y);

  /**
   * Setter method to set the width of the bounding box for the canvas.
   */
  void setWidth(int width);

  /**
   * Setter method to set the height of the bounding box for the canvas.
   */
  void setHeight(int height);

  /**
   * Removes the keyframe from the given shape at the given tick by modifying the motion list of
   * the shape with the given name. If keyframe is bordered by a single motion, that motions is
   * removed from the list of motions. If keyframe is bordered by
   * two motions, the start state of the first motion and the end state of the second motion are
   * merged to become a single motion.
   *
   * @param name The name of the shape
   * @param t    The time for this keyframe
   * @throws IllegalArgumentException if the keyframe tick value is not a keyframe or if the shape
   *          with given name does not exist
   */
  void removeKeyFrame(String name, int t);

  /**
   * Edits the keyframe of the given shape at the given tick with the given state by modifying the
   * shape's state at the existing keyframe. This method does not add new keyframes, but rather
   * alters the state values of keyframes that already exist.
   *
   * @param name The name of the shape
   * @param t    The time for this keyframe
   * @param x    The x-position of the shape
   * @param y    The y-position of the shape
   * @param w    The width of the shape
   * @param h    The height of the shape
   * @param r    The red color-value of the shape
   * @param g    The green color-value of the shape
   * @param b    The blue color-value of the shape
   * @throws UnsupportedOperationException if keyframe does not exist at given tick for given shape
   */
  void editKeyFrame(String name, int t, int x, int y,
                   int w, int h, int r, int g, int b);

  /**
   * Adds the keyframe to the given shape at the given tick by adding a new motion to the shape's
   * list of motions, splitting the motion that the tick tweens. The state of the keyframe is
   * determined by calculating the intermediate state between the two nearest tick frames. This
   * method does not edit the state of the shape, but places a keyframe that can later be modified
   * to modify the actions of the shape. Adding a keyframe at a tick where a keyframe already exists
   * does nothing, adding a keyframe before the first keyframe creates a new motion between it and
   * the previous first keyframe, adding a keyframe after the last keyframe creates a new motion
   * between the previous last keyframe and it, adding a keyframe to a shape with no motions creates
   * a new action with both the start and end states being 0. Once a second keyframe is added,
   * these two keyframes will create a full motion.
   *
   * @param name The name of the shape
   * @param t    The time for this keyframe
   * @throws UnsupportedOperationException if attempting to add keyframe at invalid tick value
   *           or invalid shape name
   */
  void insertKeyFrame(String name, int t);

  int getWidth();

  int getHeight();

  int getMaxX();

  int getMaxY();
}

