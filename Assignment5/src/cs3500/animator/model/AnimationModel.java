package cs3500.animator.model;

import java.util.ArrayList;
import java.util.TreeMap;


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
   * @return TreeMap of Shapes and list of IAction
   */
  TreeMap<Shape, ArrayList<IAction>> getScript();


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

  int getWidth();

  int getHeight();

  int getMaxX();

  int getMaxY();
}

