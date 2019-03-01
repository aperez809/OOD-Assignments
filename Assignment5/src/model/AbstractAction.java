package model;

public class AbstractAction implements Action {
  private int startTick;
  private int endTick;

  public AbstractAction (int startTick, int endTick) {
    this.startTick = startTick;
    this.endTick = endTick;
  }

  @Override
  public void apply(Shape s) {

  }

  @Override
  public int getStartTick() {
    return this.startTick;
  }

  @Override
  public int getEndTick() {
    return this.endTick;
  }

}
