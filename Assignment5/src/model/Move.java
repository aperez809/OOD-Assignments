package model;

public class Move implements Action {

  private String type;
  private int x;
  private int y;

  public Move(String type, int x, int y) {
    this.type = "Motion";
    this.x = x;
    this.y = y;
  }

  @Override
  public void apply(Shape s) {
    s.setCoords(new Location(x, y));
  }
}
