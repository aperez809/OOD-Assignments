import org.junit.Before;
import org.junit.Test;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;
import cs3500.animator.model.Action;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.model.Ellipse;
import cs3500.animator.model.IAction;
import cs3500.animator.model.Location;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.Shape;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


/**
 * Holds only those tests that apply to the SVGRepresentation class.
 */
public class AnimationModelImplTest {

  private AnimationModel testModel;
  private ArrayList<IAction> a1;
  private ArrayList<IAction> a2;
  private ArrayList<IAction> a3;


  @Before
  public void setUp() {
    //example IAction objects
    IAction move = new Action(
            0, 10,
            300, 350,
            300, 350,
            200, 200,
            200, 200,
            100, 100,
            100, 100,
            100, 100);
    IAction changeColor = new Action(
            0, 10,
            300, 300,
            300, 300,
            200, 200,
            200, 200,
            100, 50,
            100, 50,
            100, 50);
    IAction changeDims = new Action(
            0, 10,
            300, 300,
            300, 300,
            200, 100,
            200, 100,
            100, 100,
            100, 100,
            100, 100);



    //example list of actions
    a1 = new ArrayList<>();
    a2 = new ArrayList<>();
    a3 = new ArrayList<>();

    a1.add(move);
    a2.add(changeColor);
    a3.add(changeDims);

    //example Shape objects
    Shape rect = new Rectangle(
            200,
            200,
            new Location(300,300),
            new Color(100,100,100),
            new ArrayList<>(),
            "rectangle");
    Shape ellipse = new Ellipse(200,
            200,
            new Location(300,300),
            new Color(100,100,100),
            new ArrayList<>(),
            "ellipse");


    testModel = new AnimationModelImpl(new StringBuilder(),
            new TreeMap<>(),
            500, 500,
            500, 500);
    testModel.addShape(rect);
    testModel.addShape(ellipse);

    testModel.addAction("rectangle", a1.get(0));
    testModel.addAction("ellipse", a2.get(0));

  }

  @Test
  public void testNoActionsPerformed() {
    Shape rectAnim = new Rectangle(
            100,
            100,
            new Location(100,100),
            new Color(100,100,100),
            new ArrayList<>(),
            "Rectangle");
    Shape ellipseAnim = new Ellipse(100,
            100,
            new Location(100,100),
            new Color(100,100,100),
            new ArrayList<>(),
            "Ellipse");
    AnimationModel animModel = new AnimationModelImpl();
    animModel.addShape(rectAnim);
    animModel.addShape(ellipseAnim);
    assertEquals("", animModel.getAnimState());
  }

  @Test
  public void testSuccessfullyMakeRect() {
    Shape r = new Rectangle(100,100,
            new Location(100,100), Color.BLUE, a1, "rect1");
    assertEquals(100, r.getHeight());
    assertEquals(100, r.getWidth());
    assertEquals(100, r.getCoords().getX());
    assertEquals(100, r.getCoords().getY());
    assertEquals(a1, r.getActions());
    assertEquals("rect1", r.getShapeName());
    assertEquals(Color.BLUE, r.getColor());
  }

  @Test
  public void testSuccessfullyMakeOval() {
    Shape o = new Ellipse(100,100,
            new Location(100,100), Color.BLUE, a2, "ellipse1");
    assertEquals(100, o.getHeight());
    assertEquals(100, o.getWidth());
    assertEquals(100, o.getCoords().getX());
    assertEquals(100, o.getCoords().getY());
    assertEquals(a2, o.getActions());
    assertEquals("ellipse1", o.getShapeName());
    assertEquals(Color.BLUE, o.getColor());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadRectZeroHeight() {
    Shape r = new Rectangle(0,100,
            new Location(100,100), Color.RED, new ArrayList<>(), "test");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadOvalZeroWidth() {
    Shape o = new Ellipse(100,0,
            new Location(100,100), Color.RED, new ArrayList<>(), "test");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadOvalZeroHeight() {
    Shape o = new Ellipse(0,100,
            new Location(100,100), Color.RED, new ArrayList<>(), "test");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadRectZeroWidth() {
    Shape r = new Rectangle(100,0,
            new Location(100,100), Color.RED, new ArrayList<>(), "test");
  }



  @Test
  public void testMoveRect() {
    Shape curr = testModel.getShapes().get(0);
    curr.execute(a1);

    assertEquals(new Location(350,350), curr.getCoords());
  }

  @Test
  public void testChangeColorRect() {
    Shape curr = testModel.getShapes().get(0);
    curr.execute(a2);

    assertEquals(new Color(50,50,50), curr.getColor());
  }

  @Test
  public void testChangeDimsRect() {
    Shape curr = testModel.getShapes().get(0);
    curr.execute(a3);

    assertEquals(100, curr.getHeight());
    assertEquals(100, curr.getWidth());
  }

  @Test
  public void testAddShapeAndAction() {
    AnimationModelImpl shapeActModel = new AnimationModelImpl();
    Shape rect1 = new Rectangle(200,
            200,
            new Location(300,300),
            new Color(100,100,100),
            new ArrayList<>(),
            "rect1");
    Shape rect2 = new Rectangle(100,
            100,
            new Location(100,100),
            new Color(100,100,100),
            new ArrayList<>(),
            "rect2");
    IAction move1 = new Action(0, 10,
            300, 350,
            300, 350,
            200, 200,
            200, 200,
            100, 100,
            100, 100,
            100, 100);
    IAction move2 = new Action(0, 10,
            350, 400,
            350, 400,
            200, 200,
            200, 200,
            100, 100,
            100, 100,
            100, 100);
    ArrayList<IAction> moveList2 = new ArrayList<IAction>();
    ArrayList<IAction> moveList1 = new ArrayList<IAction>();
    moveList1.add(move1);
    moveList2.add(move2);
    shapeActModel.addShape(rect1);
    shapeActModel.addAction("rect1", move1);
    assertEquals(rect1, shapeActModel.getShapes().get(0));
    assertEquals(moveList1.get(0), shapeActModel.getShapeActions(rect1).get(0));
    assertNotEquals(rect2, shapeActModel.getShapes().get(0));
    assertNotEquals(moveList2.get(0), shapeActModel.getShapeActions(rect1).get(0));
  }

  @Test
  public void testBlankModel() {
    AnimationModel blankModel = new AnimationModelImpl();
    assertEquals(new ArrayList<>(), blankModel.getShapes());
  }

  @Test
  public void testAddShapes() {
    AnimationModel shapeActModel = new AnimationModelImpl();
    Shape rect1 = new Rectangle(200,
            200,
            new Location(300,300),
            new Color(100,100,100),
            new ArrayList<>(),
            "rectang1");
    Shape rect2 = new Rectangle(10,
            10,
            new Location(300,300),
            new Color(100,100,100),
            new ArrayList<>(),
            "rectang2");
    Shape oval1 = new Ellipse(200,
            200,
            new Location(200,200),
            new Color(100,100,100),
            new ArrayList<>(),
            "oval1");
    Collection<Shape> shapeList = new ArrayList<>();
    shapeList.add(rect1);
    shapeList.add(oval1);
    shapeList.add(rect2);
    shapeActModel.addShape(oval1);
    shapeActModel.addShape(rect1);
    shapeActModel.addShape(rect2);
    assertEquals(true, shapeActModel.getShapes().containsAll(shapeList));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddOverlappingActions() {
    IAction move1 = new Action(0, 10,
            300, 350,
            300, 350,
            200, 200,
            200, 200,
            100, 100,
            100, 100,
            100, 100);
    IAction color1 = new Action(5, 15,
            350, 350,
            350, 350,
            200, 200,
            200, 200,
            100, 200,
            100, 200,
            100, 200);
    testModel.addAction("rectangle", move1);
    testModel.addAction("rectangle", color1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddActionsWithGaps() {
    IAction move1 = new Action(0, 10,
            300, 350,
            300, 350,
            200, 200,
            200, 200,
            100, 100,
            100, 100,
            100, 100);
    IAction color1 = new Action(20, 30,
            350, 350,
            350, 350,
            200, 200,
            200, 200,
            100, 200,
            100, 200,
            100, 200);
    testModel.addAction("rectangle", move1);
    testModel.addAction("rectangle", color1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddActionsWithUnmatchingStates() {
    IAction move1 = new Action(0, 10,
            300, 350,
            300, 350,
            200, 200,
            200, 200,
            100, 100,
            100, 100,
            100, 100);
    IAction color1 = new Action(10, 20,
            400, 350,
            400, 350,
            200, 200,
            200, 200,
            100, 200,
            100, 200,
            100, 200);
    testModel.addAction("rectangle", move1);
    testModel.addAction("rectangle", color1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNegativeTimeActions() {
    IAction move1 = new Action(0, 10,
            300, 350,
            300, 350,
            200, 200,
            200, 200,
            100, 100,
            100, 100,
            100, 100);
    IAction move2 = new Action(10, 9,
            350, 400,
            350, 400,
            200, 200,
            200, 200,
            100, 100,
            100, 100,
            100, 100);
    testModel.addAction("rectangle", move1);
    testModel.addAction("rectangle", move2);
  }

  @Test
  public void testGetStartTick() {
    assertEquals(a1.get(0).getStartTick(), 0);
  }

  @Test
  public void testGetEndTick() {
    assertEquals(a1.get(0).getEndTick(), 10);
  }

  @Test
  public void testGetAnimState() {
    assertEquals("Motion ellipse 0 300 300 200 200 100 100 100 "
                    + "     10 300 300 200 200 50 50 50    \n"
                    + "Motion rectangle 0 300 300 200 200 100 100 100"
                    + "      10 350 350 200 200 100 100 100    \n",
            testModel.getAnimState());
  }


  @Test
  public void testGetShapes() {
    ArrayList<Shape> temp = new ArrayList<>();
    temp.add(new Rectangle(
            200,
            200,
            new Location(300,300),
            new Color(100,100,100),
            new ArrayList<>(),
            "rectangle"));
    temp.add(new Ellipse(200,
            200,
            new Location(300,300),
            new Color(100,100,100),
            new ArrayList<>(),
            "ellipse"));
    assertEquals(true, temp.containsAll(testModel.getShapes()));
  }
}
