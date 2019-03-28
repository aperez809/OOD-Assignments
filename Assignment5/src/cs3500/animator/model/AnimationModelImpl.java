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
      try {
        this.model.editKeyFrame(name, t, x, y, w, h, r, g, b);
      } catch (Exception e) {
        throw new UnsupportedOperationException(e.getMessage());
      }
      return this;
    }

    public AnimationBuilder<AnimationModel> insertKeyFrame(String name, int t) {
      try {
        this.model.insertKeyFrame(name, t);
      } catch (Exception e) {
        throw new UnsupportedOperationException(e.getMessage());
      }
      return this;
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
        this.model.removeKeyFrame(name, t);
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
    Shape givenShape = this.getShapeByName(name);
    ArrayList<IAction> actionsAtTick = givenShape.getActionsAtTick(t);
    if (actionsAtTick.size() == 0) {
      throw new IllegalArgumentException("Given Tick Not a Keyframe");
    }
    else if (actionsAtTick.size() == 1 && (t == actionsAtTick.get(0).getStartTick()
            || t == actionsAtTick.get(0).getEndTick())) {
      this.removeAction(name,actionsAtTick.get(0));
    }
    else if (actionsAtTick.size() == 2) {
      //merge two bordering actions into one action by making new action with start state of first
      //and end state of second, removing the first action and replacing the second action with
      //the new merged action
      int[] combinedStartState = actionsAtTick.get(0).getStartState();
      int[] combinedEndState = actionsAtTick.get(1).getEndState();
      this.removeAction(name,actionsAtTick.get(0));
      int combinedActIndex = givenShape.getActions().indexOf(actionsAtTick.get(1));
      this.addShapeAction(givenShape, combinedActIndex, true, combinedStartState, combinedEndState);
    }
  }

  @Override
  public void editKeyFrame(String name, int t, int x, int y, int w, int h, int r, int g, int b) {
    Shape givenShape = this.getShapeByName(name);
    ArrayList<IAction> actionsAtTick = givenShape.getActionsAtTick(t);
    int[] givenState = new int[] {t,x,y,h,w,r,g,b};
    if (actionsAtTick.size() == 1 && t == actionsAtTick.get(0).getStartTick()) {
      //edit action so that start state is equal to given state values
      int[] actEndState = actionsAtTick.get(0).getEndState();
      int newActIndex = givenShape.getActions().indexOf(actionsAtTick.get(0));
      this.addShapeAction(givenShape, newActIndex, true, givenState, actEndState);
    }
    else if (actionsAtTick.size() == 1 && t == actionsAtTick.get(0).getEndTick()) {
      //edit action so that end state is equal to given state values
      int[] actStartState = actionsAtTick.get(0).getStartState();
      int newActIndex = givenShape.getActions().indexOf(actionsAtTick.get(0));
      this.addShapeAction(givenShape, newActIndex, true, actStartState, givenState);
    }
    else if (actionsAtTick.size() == 2) {
      //edit both actions so that state at given tick is equal to given state values
      int[] firstStartState = actionsAtTick.get(0).getStartState();
      int[] secondEndState = actionsAtTick.get(1).getEndState();
      int newActIndex = givenShape.getActions().indexOf(actionsAtTick.get(0));
      this.addShapeAction(givenShape, newActIndex, true, firstStartState, givenState);
      this.addShapeAction(givenShape, newActIndex, true, givenState, secondEndState);
    }
    else {
      throw new IllegalArgumentException("No KeyFrame Exists at Given Tick Value");
    }
  }

  @Override
  public void insertKeyFrame(String name, int t) {
    Shape givenShape = this.getShapeByName(name);
    ArrayList<IAction> actionsAtTick = givenShape.getActionsAtTick(t);
    ArrayList<Integer> firstLastActions = givenShape.getExtemeActionIndices();
    if (actionsAtTick.size() == 1
            && t != actionsAtTick.get(0).getStartTick()
            && t != actionsAtTick.get(0).getEndTick()) {
      //split action into two actions
      int[] newState = new int[8];
      newState[0] = t;
      int[] startState = actionsAtTick.get(0).getStartState();
      int[] endState = actionsAtTick.get(0).getEndState();
      for (int i = 1; i < startState.length; i++) {
        newState[i] = (newState[0] - startState[0])
                * ((endState[i] - startState[i]) / (endState[0] - startState[0]));
      }
      int oldActIndex = givenShape.getActions().indexOf(actionsAtTick.get(0));
      this.removeAction(name,actionsAtTick.get(0));
      this.addShapeAction(givenShape, oldActIndex, false, startState, newState);
      this.addShapeAction(givenShape, oldActIndex, false, newState, endState);
    }
    else if (actionsAtTick.size() == 0 && givenShape.getActions().size() == 0) {
      //add new action from tick to tick as no existing actions
      IAction emptyAct = new Action(t,t);
      this.addAction(name, emptyAct);
    }
    else if (actionsAtTick.size() == 0
            && t < givenShape.getActions().get(firstLastActions.get(0)).getStartTick()) {
      //add new keyframe and create new motion with first keyframe copying state of
      //existing keyframe
      int[] startingState = givenShape.getActions().get(firstLastActions.get(0)).getStartState();
      int[] keyFrameState = startingState.clone();
      keyFrameState[0] = t;
      this.addShapeAction(givenShape,0, false, keyFrameState, startingState);
    }
    else if (actionsAtTick.size() == 0
            && t > givenShape.getActions().get(firstLastActions.get(1)).getEndTick()) {
      //add new keyframe and create new motion with last keyframe copying state of
      //existing keyframe
      int[] endingState = givenShape.getActions().get(firstLastActions.get(1)).getEndState();
      int[] keyFrameState = endingState.clone();
      keyFrameState[0] = t;
      this.addShapeAction(givenShape, firstLastActions.get(1),
              false, endingState, keyFrameState);
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

  private ArrayList<IAction> getShapeActionsAtTick(String name, int tick) {
    Shape givenShape = shapes.get(name);
    if (givenShape == null) {
      throw new IllegalArgumentException("Invalid Shape Name");
    }
    return givenShape.getActionsAtTick(tick);
  }

  private void addShapeAction(Shape shape, int actionIndex, boolean replace,
                                 int[] startState, int[] endState) {
    IAction newAction = new Action(startState[0],endState[0],
            startState[1], endState[1],
            startState[2], endState[2],
            startState[3], endState[3],
            startState[4], endState[4],
            startState[5], endState[5],
            startState[6], endState[6],
            startState[7], endState[7]);
    if (replace) {
      shape.getActions().set(actionIndex, newAction);
    }
    else {
      shape.getActions().add(actionIndex, newAction);
    }

  }

  private Shape getShapeByName(String name) {
    Shape namedShape = shapes.get(name);
    if (namedShape == null) {
      throw new IllegalArgumentException("Invalid Shape Name");
    }
    return namedShape;
  }
}
