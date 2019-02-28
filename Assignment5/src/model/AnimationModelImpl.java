package model;

import java.util.ArrayList;

public class AnimationModelImpl implements AnimationModel {
  private ArrayList<Shape> shapes;
  private StringBuilder trackedState;

  public AnimationModelImpl(ArrayList<Shape> shapes) {
    this.shapes = shapes;
    this.trackedState = new StringBuilder();
  }

  @Override
  public void placeImage(Shape s) {
    this.shapes.add(s);
  }

  @Override
  public String getAnimState() {
    for (Shape s : this.shapes) {
      trackedState.append(s.getTrackedState().toString());
    }
    return this.trackedState.toString();
  }
}
