package cs3500.animator.provider.model;

import java.util.Objects;


/**
 * Adapter class so that our Size implementation would integrate with our
 * provider's Size implementation.
 */
public class SizeAdapter {

  private int height;
  private int width;

  public SizeAdapter(int height, int width) {
    this.height = height;
    this.width = width;
  }

  public int getH() {
    return this.height;
  }

  public int getW() {
    return this.width;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof SizeAdapter)) {
      return false;
    }

    SizeAdapter other = (SizeAdapter) o;

    return this.height == other.height
            && this.width == other.width;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.height, this.width);
  }
}
