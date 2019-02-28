package model;

import java.awt.*;
import java.util.ArrayList;

public abstract class AbstractShape implements Shape {
  private int height;
  private int width;
  private Location coords;
  private Color color;
  protected StringBuilder trackedState;

  public AbstractShape(int height, int width, Location coords, Color color) {
    this.height = height;
    this.width = width;
    this.coords = coords;
    this.color = color;
    this.trackedState = new StringBuilder();
  }

  public abstract void execute(ArrayList<Action> actions, int startTick, int endTick);

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

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public Location getCoords() {
    return coords;
  }

  public void setCoords(Location coords) {
    this.coords = coords;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public StringBuilder getTrackedState() {
    return trackedState;

  }

  public void setTrackedState(StringBuilder trackedState) {
    this.trackedState = trackedState;
  }
}




