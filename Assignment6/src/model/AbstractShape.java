package model;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Abstract class that generally represents any type of Shape that can be placed
 * on the Animation's canvas.
 */
public abstract class AbstractShape implements Shape {
  private int height;
  private int width;
  private Location coords;
  private Color color;
  protected StringBuilder trackedState;


  /**
   * Constructs an instance of an AbstractShape object.
   *
   * @param height height of Shape
   * @param width width of Shape
   * @param coords location of Shape on canvas
   * @param color color of Shape
   *
   * @throws IllegalArgumentException if height or width of Shape are < 0
   */
  public AbstractShape(int height, int width, Location coords, Color color) {
    if (height < 1 || width < 1) {
      throw new IllegalArgumentException("Height and width must be greater than 0");
    }

    this.height = height;
    this.width = width;
    this.coords = coords;
    this.color = color;
    this.trackedState = new StringBuilder();
  }

  /**
   * Performs action(s) on this Shape, beginning and ending at the given ticks.
   *
   * @param actions list of Actions to be performed by the Shape
   * @param startTick tick at which to start Actions
   * @param endTick tick at which to end Actions
   */
  public abstract void execute(ArrayList<Action> actions, int startTick, int endTick);

  /**
   * Records information about the Actions performed on a Shape and stores them in a log.
   *
   * @param actionType the action performed on the Shape
   * @param shape type of Shape
   * @param tick tick at which it happened
   */
  protected void documentChange(String actionType, String shape, String tick) {
    trackedState.append(String.format(
            "%s %s %s %s %s %s %s %s %s %s    ",
            actionType,
            shape,
            tick,
            this.coords.getX(),
            this.coords.getY(),
            this.width,
            this.height,
            this.color.getRed(),
            this.color.getGreen(),
            this.color.getBlue()));
  }

  /**
   * Get the height of this Shape.
   *
   * @return height as an int
   */
  public int getHeight() {
    return height;
  }

  /**
   * Set height of this Shape Object.
   * @param height int representing height
   */
  public void setHeight(int height) {
    this.height = height;
  }

  /**
   * Get the width of this Shape object.
   *
   * @return int representing width
   */
  public int getWidth() {
    return width;
  }

  /**
   * Set width of this Shape object.
   *
   * @param width int representing width
   */
  public void setWidth(int width) {
    this.width = width;
  }

  /**
   * Get coordinates of this Shape.
   *
   * @return coordinates as a Location object
   */
  public Location getCoords() {
    return new Location(this.coords.getX(), this.coords.getY());
  }

  /**
   * Set Location of this Shape object.
   *
   * @param coords Location object representing width
   */
  public void setCoords(Location coords) {
    this.coords = coords;
  }


  /**
   * Gets the color of this Shape object.
   *
   * @return Color object representing this Shape's color
   */
  public Color getColor() {
    return color;
  }

  /**
   * Set the color of this Shape object.
   *
   * @param color Color object
   */
  public void setColor(Color color) {
    this.color = color;
  }

  /**
   * Get log of Actions performed on this Shape.
   *
   * @return StringBuilder of performed Actions
   */
  public StringBuilder getTrackedState() {
    return trackedState;
  }
}




