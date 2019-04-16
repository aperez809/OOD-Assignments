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
}
