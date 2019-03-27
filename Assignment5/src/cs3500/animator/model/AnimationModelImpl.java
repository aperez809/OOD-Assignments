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
     * addKeyFrame method is not actually used for anything in the model/view and is unnecessary.
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
     * @throws UnsupportedOperationException addKeyFrame method is not actually used to
     *          for anything in the view and is unnecessary
     */
    @Override
    public AnimationBuilder<AnimationModel> addKeyframe(String name, int t, int x, int y,
                                                        int w, int h, int r, int g, int b) {
      throw new UnsupportedOperationException("Operation not used for building frames");
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
