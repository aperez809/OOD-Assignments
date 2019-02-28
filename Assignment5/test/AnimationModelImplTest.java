import model.*;
import model.Rectangle;
import model.Shape;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import static org.junit.Assert.assertEquals;



public class AnimationModelImplTest {

  @Before
  public void setUp() {
    //example Action objects
    Action move = new Move(300, 300);
    Action changeColor = new ChangeColor(100,100,100);
    Action changeDims = new ChangeDims(100, 200);

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


    AnimationModel model = new AnimationModelImpl(new ArrayList<>());
    model.placeImage(rect);
    model.placeImage(oval);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCanvasSizeHeight() {
    new AnimationModelImpl(new ArrayList<>());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCanvasSizeWidth() {
    new AnimationModelImpl(new ArrayList<>());
  }



}
