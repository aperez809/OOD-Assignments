package model;

import java.util.Objects;

/**
 * Represents a position of a Shape in Cartesian coordinates.
 *
 */
public class Location {
  private int x;
  private int y;

  /**
   * Constructs a Location object.
   *
   * @param x x position of object
   * @param y y position of object
   */
  public Location(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Gets the x coordinate of the Location object.
   *
   *
   * @return int of x position
   */
  public int getX() {
    return x;
  }

  /**
   * Gets the y coordinate of the Location object.
   *
   * @return int of y position
   */
  public int getY() {
    return y;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Location)) {
      return false;
    }

    Location other = (Location) o;
    return other.x == this.x && other.y == this.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.x, this.y);
  }
}
