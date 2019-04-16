package cs3500.animator.provider.model;

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

  public boolean equals(Object o) {
    if (!(o instanceof SizeAdapter)) {
      return false;
    }

    SizeAdapter other = (SizeAdapter) o;

    return this.height == other.height
            && this.width == other.width;
  }
}
