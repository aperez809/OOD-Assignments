package cs3500.animator.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.TreeMap;

import cs3500.animator.model.util.AnimationBuilder;

/**
 * Represents the cs3500.animator.model for an Animation program.
 */
public class AnimationModelImpl implements AnimationModel {
  private StringBuilder trackedState;
  private TreeMap<String, Shape> shapes;
  private int height;
  private int width;
  private int maxX;
  private int maxY;


  /**
   * Constructs an AnimationModelImpl object that has an empty log of actions performed
   * and an apply map of actions to shapes. Sets default (empty) values for each field.
   */
  public AnimationModelImpl(StringBuilder trackedState,
                            TreeMap<String, Shape> shapes,
                            int height,
                            int width,
                            int maxX,
                            int maxY) {
    this.trackedState = trackedState;
    this.shapes = shapes;
    this.height = height;
    this.width = width;
    this.maxX = maxX;
    this.maxY = maxY;
  }

  public AnimationModelImpl() {
    this.trackedState = new StringBuilder();
    this.shapes = new TreeMap<>();
  }

  /**
   * Builder class that is used by AnimationBuilder to create a custom model.
   * Uses the methods below to set the model's attributes.
   */
  public static final class Builder implements AnimationBuilder<AnimationModel> {
    private AnimationModel model;

    /**
     * Constructs a Builder object using an instance of a model that is passed in (usually with
     * default values for all fields).
     * @param model AnimationModel object that will be edited by the Builder's methods
     */
    public Builder(AnimationModel model) {
      this.model = model;
    }


    @Override
    public AnimationModel build() {
      return this.model;
    }

    @Override
    public AnimationBuilder<AnimationModel> setBounds(int x, int y, int width, int height) {
      this.model.setMaxX(x);
      this.model.setMaxY(y);
      this.model.setWidth(width);
      this.model.setHeight(height);

      return this;
    }

    @Override
    public AnimationBuilder<AnimationModel> declareShape(String name, String type) {
      switch (type.toLowerCase()) {
        case "ellipse":
          this.model.addShape(
                  new Ellipse(1,
                          1,
                          new Location(0,0),
                          new Color(0,0,0),
                          new ArrayList<>(),
                          name));
          break;

        case "rectangle":
          this.model.addShape(
                  new Rectangle(1,
                          1,
                          new Location(0,0),
                          new Color(0,0,0),
                          new ArrayList<>(),
                          name));
          break;

        default:
          throw new IllegalArgumentException("Shape type not recognized");
      }

      return this;
    }

    @Override
    public AnimationBuilder<AnimationModel> addMotion(String name, int t1, int x1, int y1, int w1,
                                                      int h1, int r1, int g1, int b1, int t2,
                                                      int x2, int y2, int w2, int h2, int r2,
                                                      int g2, int b2) {
      this.model.addAction(name, new Action(t1,t2,x1,x2,y1,y2,h1,h2,w1,w2,r1,r2,g1,g2,b1,b2));
      return this;
    }

    /**
     * Adds a new keyframe to motions of given shape. If keyframe is in the middle of an existing
     * motion it splits the motion into two motions with the respective end and start time and
     * state being the given keyframe values. If keyframe tick value is already an existing
     * keyframe, replaces the start and end states of the surrounding motions determined by the
     * existing keyframe with the given state values.
     *
     * @param name The name of the shape (added with {@link AnimationBuilder#declareShape})
     * @param t    The time for this keyframe
     * @param x    The x-position of the shape
     * @param y    The y-position of the shape
     * @param w    The width of the shape
     * @param h    The height of the shape
     * @param r    The red color-value of the shape
     * @param g    The green color-value of the shape
     * @param b    The blue color-value of the shape
     * @throws UnsupportedOperationException if attempting to add keyframe at invalid tick value
     *            or invalid shape name
     */
    @Override
    public AnimationBuilder<AnimationModel> addKeyframe(String name, int t, int x, int y,
                                                        int w, int h, int r, int g, int b) {
      throw new UnsupportedOperationException("Operation not used for building frames");
    }

    /**
     * Removes the keyframe from the given shape at the given tick. If keyframe is bordered by a
     * single motion, that motions is removed from the list of motions. If keyframe is bordered by
     * two motions, the start state of the first motion and the end state of the second motion are
     * merged to become a single motion.
     *
     * @param name The name of the shape (added with {@link AnimationBuilder#declareShape})
     * @param t    The time for this keyframe
     * @throws UnsupportedOperationException if the keyframe tick value is not a keyframe or invalid
     *          shape name
     */
    public AnimationBuilder<AnimationModel> removeKeyframe(String name, int t) {
      try {
        model.removeKeyFrame(name, t);
      } catch (Exception e) {
        throw new UnsupportedOperationException(e.getMessage());
      }
      return this;
    }
  }


  @Override
  public void addShape(Shape s) {
    if (shapes.containsKey(s.getShapeName())) {
      throw new IllegalArgumentException("Shape already exists in cs3500.animator.model");
    }
    this.shapes.put(s.getShapeName(), s);
  }

  @Override
  public void removeShape(String name) {
    if (!this.shapes.containsKey(name)) {
      throw new IllegalArgumentException("Shape is not in list of shapes");
    }
    this.shapes.remove(name);
  }

  /**
   * Adds an IAction to this cs3500.animator.model's action map for the given Shape.
   *
   * @param a IAction to add to the Shape's list of Actions
   *
   * @throws IllegalArgumentException if the given IAction overlaps with an existing one
   */
  @Override
  public void addAction(String name, IAction a) {
    if (!shapes.containsKey(name)) {
      throw new IllegalArgumentException("Shape does not exist in cs3500.animator.model");
    }
    else {
      shapes.get(name).addAction(a);
    }
  }

  @Override
  public void removeAction(String name, IAction a) {
    if (!shapes.containsKey(name)) {
      throw new IllegalArgumentException("Shape does not exist in cs3500.animator.model");
    }
    else {
      shapes.get(name).removeAction(a);
    }
  }

  /**
   * Get the state of the animation (which Actions have been performed).
   *
   * @return String log of the Actions
   */
  @Override
  public String getAnimState() {
    for (Shape s : this.shapes.values()) {
      s.execute(s.getActions());
      this.trackedState.append(s.getTrackedState().toString());
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
  public ArrayList<IAction> getShapeActions(Shape s) {
    return s.getActions();
  }

  /**
   * Get list of Shapes in the cs3500.animator.model.
   *
   * @return list of Shapes
   */
  @Override
  public ArrayList<Shape> getShapes() {
    ArrayList<Shape> temp = new ArrayList<>();
    for (Shape s : this.shapes.values()) {
      temp.add(s.cloneShape());
    }
    return temp;
  }


  /**
   * Get the set of Shapes in the cs3500.animator.model along with their corresponding
   * IAction lists.
   *
   * @return TreeMap of Shapes and list of IAction
   */
  @Override
  public TreeMap<Shape, ArrayList<IAction>> getScript() {
    TreeMap<Shape, ArrayList<IAction>> temp = new TreeMap<>();
    for (Shape s : this.shapes.values()) {
      temp.put(s, s.getActions());
    }
    return temp;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  @Override
  public void removeKeyFrame(String name, int t) {
    Shape givenShape = shapes.get(name);
    int firstActIndex;
    int lastActIndex;
    if (givenShape == null) {
      throw new IllegalArgumentException("Invalid Shape Name");
    }
    ArrayList<IAction> actionsAtTick = givenShape.getActionsAtTick(t);
    if (actionsAtTick.size() == 0) {
      throw new IllegalArgumentException("Given Tick Not a Keyframe");
    }
    if (actionsAtTick.size() == 1) {
      givenShape.removeAction(actionsAtTick.get(0));
    }
    else if (actionsAtTick.size() == 2) {
      //check which action of list comes first and assign indices accordingly
      if (actionsAtTick.get(0).getEndTick() == t) {
        firstActIndex = 0;
        lastActIndex = 1;
      }
      else {
        firstActIndex = 1;
        lastActIndex = 0;
      }
      //merge two bordering actions into one action by making new action with start state of first
      //and end state of second, removing the first action and replacing the second action with
      //the new merged action
      int[] combinedStartState = actionsAtTick.get(firstActIndex).getStartState();
      int[] combinedEndState = actionsAtTick.get(lastActIndex).getEndState();
      givenShape.removeAction(actionsAtTick.get(firstActIndex));
      int combinedActIndex = givenShape.getActions().indexOf(actionsAtTick.get(lastActIndex));
      IAction combinedAct = new Action(combinedStartState[0],combinedEndState[0],
              combinedStartState[1], combinedEndState[1],
              combinedStartState[2], combinedEndState[2],
              combinedStartState[3], combinedEndState[3],
              combinedStartState[4], combinedEndState[4],
              combinedStartState[5], combinedEndState[5],
              combinedStartState[6], combinedEndState[6],
              combinedStartState[7], combinedEndState[7]);
      givenShape.getActions().set(combinedActIndex, combinedAct);
    }
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getMaxX() {
    return maxX;
  }

  public void setMaxX(int maxX) {
    this.maxX = maxX;
  }

  public int getMaxY() {
    return maxY;
  }

  public void setMaxY(int maxY) {
    this.maxY = maxY;
  }
}
