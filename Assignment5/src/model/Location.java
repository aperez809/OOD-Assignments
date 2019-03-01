package model;

import java.util.Objects;

public class Location {
  private int x;
  private int y;

  public Location(int x, int y) {
    this.x = x;
    this.y = y;
  }



  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
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
