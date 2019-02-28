package model;

import java.util.ArrayList;

public class AnimationModelImpl implements AnimationModel {
  private final int canvasHeight;
  private final int canvasWidth;
  private ArrayList<AbstractShape> shapes;
  private StringBuilder trackedState;

  public AnimationModelImpl(int canvasHeight,
                            int canvasWidth,
                            ArrayList<AbstractShape> shapes) {

    if (canvasHeight < 1 || canvasWidth < 1) {
      throw new IllegalArgumentException("Canvas height and width must be greater than 0");
    }

    this.canvasHeight = canvasHeight;
    this.canvasWidth = canvasWidth;
    this.shapes = shapes;
  }

  @Override
  public AnimationModel getAnimState(int tick) {
    return this;
  }

  @Override
  public void placeImage(AbstractShape s, int x, int y) {

  }

  @Override
  public String getAnimState() {
    for (Shape s : this.shapes) {
      trackedState.append(s.getTrackedState().toString());
    }

    return this.trackedState.toString();
  }
}
