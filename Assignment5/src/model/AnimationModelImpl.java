package model;

import java.util.ArrayList;
import java.util.HashMap;

public class AnimationModelImpl implements AnimationModel {
  private StringBuilder trackedState;
  private HashMap<Shape, ArrayList<Action>> actionMap;

  public AnimationModelImpl() {
    this.trackedState = new StringBuilder();
    this.actionMap = new HashMap<>();
  }

  @Override
  public void addShape(Shape s) {
    if (actionMap.containsKey(s)) {
      throw new IllegalArgumentException("Shape already exists in model");
    }
    this.actionMap.put(s, new ArrayList<>());
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
  public ArrayList<Action> getShapeActions(Shape s) {
    return actionMap.get(s);
  }

  @Override
  public ArrayList<Shape> getShapes() {
    ArrayList<Shape> allShapes = new ArrayList<Shape>();
    for (Shape s : this.actionMap.keySet()) {
      allShapes.add(s);
    }
    return allShapes;
  }

  @Override
  public HashMap<Shape, ArrayList<Action>> getScript() {
    return this.actionMap;
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
