package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents the model for an Animation program.
 */
public class AnimationModelImpl implements AnimationModel {
  private StringBuilder trackedState;
  private HashMap<Shape, ArrayList<Action>> actionMap;

  /**
   * Constructs an AnimationModelImpl object that has an empty log of actions performed
   * and an apply map of actions to shapes. Sets default (empty) values for each field.
   */
  public AnimationModelImpl() {
    this.trackedState = new StringBuilder();
    this.actionMap = new HashMap<>();
  }


  /**
   * Adds a Shape to this model's list of Shape (without any Actions to go along with it).
   *
   * @param s Shape to add to the action map
   */
  @Override
  public void addShape(Shape s) {
    if (actionMap.containsKey(s)) {
      throw new IllegalArgumentException("Shape already exists in model");
    }
    this.actionMap.put(s, new ArrayList<>());
  }

  /**
   * Adds an Action to this model's action map for the given Shape.
   *
   * @param s Shape to map the Action to
   * @param a Action to add to the Shape's list of Actions
   *
   * @throws IllegalArgumentException if the given Action overlaps with an existing one
   */
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

  /**
   * Get the state of the animation (which Actions have been performed).
   *
   * @return String log of the Actions
   */
  @Override
  public String getAnimState() {
    for (Shape s : this.actionMap.keySet()) {
      trackedState.append(s.getTrackedState().toString());
    }
    return this.trackedState.toString();
  }

  /**
   * Get list of Actions that a Shape has been set to perform.
   *
   * @param s the given Shape
   * @return list of Actions
   */
  @Override
  public ArrayList<Action> getShapeActions(Shape s) {
    return actionMap.get(s);
  }

  /**
   * Get list of Shapes in the model.
   *
   * @return list of Shapes
   */
  @Override
  public ArrayList<Shape> getShapes() {
    ArrayList<Shape> allShapes = new ArrayList<>();
    allShapes.addAll(this.actionMap.keySet());
    return allShapes;
  }

  /**
   * Get the set of Shapes in the model along with their corresponding Action lists.
   *
   * @return HashMap of Shapes and list of Action
   */
  @Override
  public HashMap<Shape, ArrayList<Action>> getScript() {
    return new HashMap<>(this.actionMap);
  }

  /**
   * Checks if there is any overlap between the given Action and the Actions already assigned
   * to the given Shape.
   *
   * @param s the given Shape
   * @param a the given Action
   * @return boolean of whether or not there is overlap
   *
   * @throws IllegalArgumentException if the Shape does not yet exist
   */
  private boolean checkOverlap(Shape s, Action a) {
    if (!this.actionMap.keySet().contains(s)) {
      throw new IllegalArgumentException("Shape does not yet exist in the list");
    }

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

  /**
   * Starts the Anim and adds ticks to a running counter.
   *
   */
  @Override
  public void startAnim() {
    //will keep track of currTick and perform actions at designated times
  }
}
