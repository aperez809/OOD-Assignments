package model;

public class ChangeDims extends AbstractAction {
  private int startTick;
  private int endTick;
  private String type;
  private int height;
  private int width;

  public ChangeDims(int startTick, int endTick, int height, int width) {
    super(startTick, endTick);
    if (height <= 0 || width <= 0) {
      throw new IllegalArgumentException("New height and width must be greater than 0");
    }
    this.type = "Change Dims";
    this.height = height;
    this.width = width;
  }

  @Override
  public void apply(Shape s) {
    s.setHeight(this.height);
    s.setWidth(this.width);
  }

  @Override
  public int getStartTick() {
    return 0;
  }

  @Override
  public int getEndTick() {
    return 0;
  }

  public String getType() {
    return this.type;
  }
}
