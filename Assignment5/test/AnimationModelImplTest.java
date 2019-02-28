import model.*;
import model.Rectangle;
import model.Shape;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;



public class AnimationModelImplTest {

  private AnimationModel testModel;
  private ArrayList<Action> a1;
  private ArrayList<Action> a2;
  private ArrayList<Action> a3;


  @Before
  public void setUp() {
    //example Action objects
    Action move = new Move(300, 300);
    Action changeColor = new ChangeColor(100,100,100);
    Action changeDims = new ChangeDims(100, 200);

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


    testModel = new AnimationModelImpl(new ArrayList<>());
    testModel.placeImage(rect);
    testModel.placeImage(oval);
  }

  @Test
  public void testNoActionsPerformed() {
    assertEquals("", testModel.getAnimState());
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



}
