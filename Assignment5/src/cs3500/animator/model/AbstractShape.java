package cs3500.animator.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Abstract class that generally represents any type of Shape that can be placed
 * on the Animation's canvas.
 */
public abstract class AbstractShape implements Shape {
  private int height;
  private int width;
  private Location coords;
  private Color color;
  protected StringBuilder trackedState;
  private ArrayList<IAction> actions;
  private String shapeName;


  /**
   * Constructs an instance of an AbstractShape object.
   *
   * @param height height of Shape
   * @param width width of Shape
   * @param coords location of Shape on canvas
   * @param color color of Shape
   *
   * @throws IllegalArgumentException if height or width of Shape are < 0
   */
  public AbstractShape(int height,
                       int width,
                       Location coords,
                       Color color,
                       ArrayList<IAction> actions,
                       String shapeName) {
    if (height < 1 || width < 1) {
      throw new IllegalArgumentException("Height and width must be greater than 0");
    }

    this.height = height;
    this.width = width;
    this.coords = coords;
    this.color = color;

    this.trackedState = new StringBuilder();
    this.actions = actions;
    this.shapeName = shapeName;
  }

  /**
   * Copy constructor that will return a deep copy of the AbstractShape.
   * @param other AbstractShape to be copied
   */
  public AbstractShape(AbstractShape other) {
    this.height = other.height;
    this.width = other.width;
    this.coords = new Location(other.getPosition().getX(), other.getPosition().getY());
    this.color = new Color(
            other.getColor().getRed(),
            other.getColor().getGreen(),
            other.getColor().getBlue());

    this.trackedState = new StringBuilder();
    this.actions = cloneActions(this.getActions());
    this.shapeName = other.shapeName;
  }

  protected ArrayList<IAction> cloneActions(ArrayList<IAction> actions) {
    ArrayList<IAction> temp = new ArrayList<>();
    for (IAction a : actions) {
      temp.add(a.cloneAction());
    }
    return temp;
  }

  @Override
  public abstract Shape cloneShape();

  /**
   * Performs action(s) on this Shape, beginning and ending at the given ticks.
   *
   * @param actions list of Actions to be performed by the Shape
   */
  @Override
  public void execute(ArrayList<IAction> actions) {

    for (IAction a : actions) {
      this.documentChange("Motion",
              this.shapeName,
              String.valueOf(a.getStartTick()));

      this.performAction(a);

      this.documentChange("", "", String.valueOf(a.getEndTick()));
      this.trackedState.append("\n");
    }
  }


  protected void performAction(IAction a) {
    int[] temp = a.getEndState();
    this.setCoords(new Location(temp[1], temp[2]));
    this.setHeight(temp[3]);
    this.setWidth(temp[4]);
    this.setColor(new Color(temp[5], temp[6], temp[7]));

  }

  @Override
  public void removeAction(IAction a) {
    if (!this.actions.contains(a)) {
      throw new IllegalArgumentException("Action does not exist in the given shapes list");
    }
    else {
      actions.remove(a);
    }
  }

  /**
   * Records information about the Actions performed on a Shape and stores them in a log.
   *
   * @param actionType the action performed on the Shape
   * @param shape type of Shape
   * @param tick tick at which it happened
   */
  protected void documentChange(String actionType, String shape, String tick) {
    trackedState.append(String.format(
            "%s %s %s %s %s %s %s %s %s %s    ",
            actionType,
            shape,
            tick,
            this.coords.getX(),
            this.coords.getY(),
            this.width,
            this.height,
            this.color.getRed(),
            this.color.getGreen(),
            this.color.getBlue()));
  }

  /**
   * Get the height of this Shape.
   *
   * @return height as an int
   */
  public int getH() {
    return height;
  }

  /**
   * Set height of this Shape Object.
   * @param height int representing height
   */
  public void setHeight(int height) {
    this.height = height;
  }

  /**
   * Get the width of this Shape object.
   *
   * @return int representing width
   */
  public int getW() {
    return width;
  }

  /**
   * Set width of this Shape object.
   *
   * @param width int representing width
   */
  public void setWidth(int width) {
    this.width = width;
  }

  /**
   * Get coordinates of this Shape.
   *
   * @return coordinates as a Location object
   */
  public Location getPosition() {
    return new Location(this.coords.getX(), this.coords.getY());
  }

  /**
   * Set Location of this Shape object.
   *
   * @param coords Location object representing width
   */
  public void setCoords(Location coords) {
    this.coords = coords;
  }


  /**
   * Gets the color of this Shape object.
   *
   * @return Color object representing this Shape's color
   */
  public Color getColor() {
    return color;
  }

  /**
   * Set the color of this Shape object.
   *
   * @param color Color object
   */
  public void setColor(Color color) {
    this.color = color;
  }

  /**
   * Get log of Actions performed on this Shape.
   *
   * @return StringBuilder of performed Actions
   */
  public StringBuilder getTrackedState() {
    return trackedState;
  }

  /**
   * Checks if there is any overlap between the given IAction and the Actions already assigned
   * to the given Shape.
   *
   * @param a the given IAction
   * @return boolean of whether or not there is overlap
   *
   * @throws IllegalArgumentException if the Shape does not yet exist
   */
  public boolean checkOverlap(IAction a) {

    int actionStart = a.getStartTick();
    int actionEnd = a.getEndTick();
    for (IAction act : this.actions) {
      if ((actionStart > act.getStartTick() && actionStart < act.getEndTick())
              || (actionEnd > act.getStartTick() && actionEnd < act.getEndTick())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if there is any overlap between the given IAction and the Actions already assigned
   * to the given Shape.
   *
   * @param a the given IAction
   * @return boolean of whether or not there is overlap
   */
  private boolean checkConsecutive(IAction a) {
    Integer currMax = null;
    IAction curr = null;

    for (IAction act : this.actions) {
      if (currMax == null || act.getEndTick() > currMax) {
        currMax = act.getEndTick();
        curr = act;
      }
    }

    return currMax == null || (currMax == a.getStartTick()
            && Arrays.equals(curr.getEndState(), a.getStartState()));
  }

  @Override
  public void addAction(IAction a) {
    if (this.checkOverlap(a)) {
      throw new IllegalArgumentException("IAction overlaps with existing action");
    }
    else if (!this.checkConsecutive(a)) {
      throw new IllegalArgumentException("Actions must have consecutive states");
    }

    this.actions.add(a);
  }

  @Override
  public ArrayList<IAction> getActions() {
    return this.actions;
  }

  @Override
  public String getShapeName() {
    return this.shapeName;
  }

  @Override
  public ArrayList<IAction> getActionsAtTick(int t) {
    ArrayList<IAction> associatedActions = new ArrayList<>();
    for (IAction action : actions) {
      if (t == action.getStartTick() || t == action.getEndTick()
              || (t > action.getStartTick() && t < action.getEndTick())) {
        associatedActions.add(action);
      }
    }
    //sorts the associated actions list based on start tick
    if (associatedActions.size() == 2
            && associatedActions.get(0).getStartTick() > associatedActions.get(1).getStartTick()) {
      Collections.swap(associatedActions,0,1);
    }
    return associatedActions;
  }

  @Override
  public ArrayList<Integer> getExtemeActionIndices() {
    int numActions = this.getActions().size();
    ArrayList<Integer> extremeIndices = new ArrayList<>();
    if (numActions == 0) {
      return extremeIndices;
    }
    int minIndex = 0;
    int maxIndex = 0;
    int minTick = 0;
    int maxTick = 0;
    for (int i = 0; i < numActions; i++) {
      if (this.getActions().get(i).getStartTick() <= minTick) {
        minIndex = i;
      }
      if (this.getActions().get(i).getStartTick() >= maxTick) {
        maxIndex = i;
      }
    }
    extremeIndices.add(0,minIndex);
    extremeIndices.add(1,maxIndex);
    return extremeIndices;
  }

  public abstract String getShapeType();


  public int getR() {
    return this.color.getRed();
  }

  public int getG() {
    return this.color.getGreen();
  }

  public int getB() {
    return this.color.getBlue();
  }

  @Override
  public int[] getSize() {
    return new int[] {this.height, this.width};
  }
}




