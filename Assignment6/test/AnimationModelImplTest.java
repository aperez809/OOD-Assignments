import model.AnimationModel;
import model.AnimationModelImpl;
import model.Action;
import model.Rectangle;
import model.Oval;
import model.Shape;
import model.Move;
import model.ChangeColor;
import model.ChangeDims;
import model.Location;



import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Test class with an intial set up method using @Before.
 */
public class AnimationModelImplTest {

  private AnimationModel testModel;
  private ArrayList<Action> a1;
  private ArrayList<Action> a2;
  private ArrayList<Action> a3;


  @Before
  public void setUp() {
    //example Action objects
    Action move = new Move(0,10, 300, 300);
    Action changeColor = new ChangeColor(10, 20,100,100,100);
    Action changeDims = new ChangeDims(5, 15,100, 200);

    //example list of actions
    a1 = new ArrayList<>();
    a2 = new ArrayList<>();
    a3 = new ArrayList<>();

    a1.add(move);
    a2.add(changeColor);
    a3.add(changeDims);

    //example Shape objects
    Shape rect = new Rectangle(
            100,
            100,
            new Location(100, 100),
            new Color(100, 100, 100)); //need to make Rectangle class
    Shape oval = new Oval(
            100,
            100,
            new Location(200, 200),
            new Color(200, 200, 200)); //need to make Rectangle class


    testModel = new AnimationModelImpl();
    testModel.addShape(rect);
    testModel.addShape(oval);

    testModel.addAction(rect, a1.get(0));
    testModel.addAction(oval, a2.get(0));

  }

  @Test
  public void testNoActionsPerformed() {
    assertEquals("", testModel.getAnimState());
  }

  @Test
  public void testSuccessfullyMakeRect() {
    Shape r = new Rectangle(100,100,new Location(100,100), Color.BLUE);
    assertEquals(100, r.getHeight());
    assertEquals(100, r.getWidth());
    assertEquals(100, r.getCoords().getX());
    assertEquals(100, r.getCoords().getY());
    assertEquals(Color.BLUE, r.getColor());
  }

  @Test
  public void testSuccessfullyMakeOval() {
    Shape o = new Oval(100,100,new Location(100,100), Color.BLUE);
    assertEquals(100, o.getHeight());
    assertEquals(100, o.getWidth());
    assertEquals(100, o.getCoords().getX());
    assertEquals(100, o.getCoords().getY());
    assertEquals(Color.BLUE, o.getColor());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadRectZeroHeight() {
    Shape r = new Rectangle(0,100,new Location(100,100), Color.RED);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadOvalZeroWidth() {
    Shape r = new Rectangle(100,0,new Location(100,100), Color.RED);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadOvalZeroHeight() {
    Shape r = new Oval(0,100,new Location(100,100), Color.RED);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadRectZeroWidth() {
    Shape r = new Oval(100,0,new Location(100,100), Color.RED);
  }



  @Test
  public void testMoveRect() {
    Shape curr = testModel.getShapes().get(0);
    curr.execute(a1, 10, 50);

    assertEquals(new Location(300,300), curr.getCoords());
  }

  @Test
  public void testChangeColorRect() {
    Shape curr = testModel.getShapes().get(0);
    curr.execute(a2, 10, 50);

    assertEquals(new Color(100,100,100), curr.getColor());
  }

  @Test
  public void testChangeDimsRect() {
    Shape curr = testModel.getShapes().get(0);
    curr.execute(a3, 10, 50);

    assertEquals(100, curr.getHeight());
    assertEquals(200, curr.getWidth());
  }

  @Test
  public void testAddShapeAndAction() {
    AnimationModelImpl shapeActModel = new AnimationModelImpl();
    Shape rect1 = new Rectangle(
            80,
            80,
            new Location(80, 80),
            new Color(80, 80, 80));
    Shape rect2 = new Rectangle(
            90,
            90,
            new Location(90, 90),
            new Color(90, 90, 90));
    Action move1 = new Move(0,10, 300, 300);
    Action move2 = new Move(10,20, 400, 500);
    ArrayList<Action> moveList2 = new ArrayList<Action>();
    ArrayList<Action> moveList1 = new ArrayList<Action>();
    moveList1.add(move1);
    moveList2.add(move2);
    shapeActModel.addShape(rect1);
    shapeActModel.addAction(rect1, move1);
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
    Shape rect1 = new Rectangle(
            80,
            80,
            new Location(80, 80),
            new Color(80, 80, 80));
    Shape rect2 = new Rectangle(
            90,
            90,
            new Location(90, 90),
            new Color(90, 90, 90));
    Shape oval1 = new Oval(
            85,
            85,
            new Location(85, 85),
            new Color(85, 85, 85));
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
    Action move1 = new Move(0,10, 300, 300);
    Action color1 = new ChangeColor(5, 15, 35, 35, 35);
    Shape firstShape = testModel.getShapes().get(0);
    testModel.addAction(firstShape, move1);
    testModel.addAction(firstShape, color1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNegativeTimeActions() {
    Action move1 = new Move(10,9, 300, 300);
    Shape firstShape = testModel.getShapes().get(0);
    testModel.addAction(firstShape, move1);
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
    testModel.addAction(testModel.getShapes().get(0), a1.get(0));
    for (Shape s: testModel.getShapes()) {
      s.execute(testModel.getShapeActions(s), 5, 10);
    }

    assertEquals("Motion Rectangle 5 100 100 100 100 100 100 100 "
                    + "     10 300 300 100 100 100 100 100    \n"
                    + "Motion Oval 5 200 200 100 100 200 200 200"
                    + "      10 200 200 100 100 100 100 100    \n",
            testModel.getAnimState());
  }


  @Test
  public void testGetShapes() {
    ArrayList<Shape> temp = new ArrayList<>();
    temp.add(new Rectangle(
            100,
            100,
            new Location(100, 100),
            new Color(100, 100, 100)));
    temp.add(new Oval(
            100,
            100,
            new Location(200, 200),
            new Color(200, 200, 200)));
    assertEquals(testModel.getShapes(),temp);
  }



}
