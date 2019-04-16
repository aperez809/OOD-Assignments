package cs3500.animator.model;

import cs3500.animator.provider.model.ColorAdapter;
import cs3500.animator.provider.model.SizeAdapter;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Represents the functionality of a Shape on the canvas.
 */
public interface Shape {

  /**
   * Performs action(s) on this Shape, beginning and ending at the given ticks.
   *
   * @param actions list of Actions to be performed by the Shape
   */
  void execute(ArrayList<IAction> actions);

  /**
   * Set Location of this Shape object.
   *
   * @param location Location object representing width
   */
  void setCoords(Location location);

  /**
   * Set width of this Shape object.
   *
   * @param width int representing width
   */
  void setWidth(int width);

  /**
   * Set height of this Shape Object.
   * @param height int representing height
   */
  void setHeight(int height);

  /**
   * Set the color of this Shape object.
   *
   * @param color Color object
   */
  void setColor(Color color);

  /**
   * Get log of Actions performed on this Shape.
   *
   * @return StringBuilder of performed Actions
   */
  StringBuilder getTrackedState();

  /**
   * Get coordinates of this Shape.
   *
   * @return coordinates as a Location object
   */
  Location getPosition();

  /**
   * Get the width of this Shape object.
   *
   * @return int representing width
   */
  int getW();

  /**
   * Get the height of this Shape.
   *
   * @return height as an int
   */
  int getH();

  boolean checkOverlap(IAction a);

  void addAction(IAction a);

  ArrayList<IAction> getActions();

  String getShapeName();

  void removeAction(IAction a);

  Shape cloneShape();

  /**
   * Get the action(s) occurring at the given tick. Will return two actions if tick is a keyframe
   * shared by multiple actions. Returns one action if tick is in middle of motion or tick is
   * start or end of list of actions.
   *
   * @param t tick value
   * @return list of actions associated with given tick
   */
  ArrayList<IAction> getActionsAtTick(int t);

  /**
   * Get the indices of the action(s) occurring at the first and last ticks of the shape's actions.
   * Will return two different indices if shape has multiple associated actions, or two of the same
   * index if only one action, or null if no actions.
   *
   * @return arraylist of action indices
   */
  ArrayList<Integer> getExtemeActionIndices();


  /**
   * Get the type of the given shape (i.e. rectangle or ellipse)
   *
   * @return String value with name of shape type
   */
  String getShapeType();

  SizeAdapter getSize();


  /**
   * Gets the color of this Shape object.
   *
   * @return Color object representing this Shape's color
   */
  ColorAdapter getColor();

}
