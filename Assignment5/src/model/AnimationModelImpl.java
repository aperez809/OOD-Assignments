package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AnimationModelImpl implements AnimationModel {
  private ArrayList<Shape> shapes;
  private StringBuilder trackedState;
  private HashMap<Shape, List<Action>> actionMap;

  public AnimationModelImpl(ArrayList<Shape> shapes) {
    this.shapes = shapes;
    this.trackedState = new StringBuilder();
    this.actionMap = new HashMap<>();
  }

  @Override
  public void placeImage(Shape s) {
    this.shapes.add(s);
  }

  @Override
  public void addAction(Shape s, Action a) {
    if (!actionMap.containsKey(s)) {
      throw new IllegalArgumentException("Shape does not exist in model");
    }
    else if (this.checkOverlap(s,a)) {
      throw new IllegalArgumentException("Action overlaps with existing action");
    }
    else {
      this.actionMap.get(s).add(a);
    }
  }

  @Override
  public String getAnimState() {
    for (Shape s : this.actionMap.keySet()) {
      trackedState.append(s.getTrackedState().toString());
    }
    return this.trackedState.toString();
  }

  @Override
  public List<Action> getShapeActions(Shape s) {
    return actionMap.get(s);
  }

  @Override
  public HashMap<Shape, List<Action>> getScript() {
    return this.actionMap;
  }

  @Override
  public ArrayList<Shape> getShapes() {
    return this.shapes;
  }

  private boolean checkOverlap(Shape s, Action a) {
    int actionStart = a.getStartTick();
    int actionEnd = a.getEndTick();
    for (Action act : getShapeActions(s)) {
      if ((actionStart > act.getStartTick() && actionStart < act.getEndTick())
      || (actionEnd > act.getStartTick() && actionEnd < act.getEndTick())) {
        return true;
      }
    }
    return false;
  }
}
