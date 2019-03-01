package model;

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
   * @param startTick tick at which to start Actions
   * @param endTick tick at which to end Actions
   */
  void execute(ArrayList<Action> actions, int startTick, int endTick);

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
  Location getCoords();

  /**
   * Gets the color of this Shape object.
   *
   * @return Color object representing this Shape's color
   */
  Color getColor();

  /**
   * Get the width of this Shape object.
   *
   * @return int representing width
   */
  int getWidth();

  /**
   * Get the height of this Shape.
   *
   * @return height as an int
   */
  int getHeight();
}
