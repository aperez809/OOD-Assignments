package model;

public class Move extends AbstractAction {
  private String type;
  private int startTick;
  private int endTick;
  private int x;
  private int y;

  public Move(int startTick, int endTick, int x, int y) {
    super(startTick,endTick);
    this.type = "Motion";
    this.x = x;
    this.y = y;
  }

  @Override
  public void apply(Shape s) {
    s.setCoords(new Location(x, y));
  }

  public String getType() {
    return this.type;
  }
}
