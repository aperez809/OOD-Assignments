import model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import static org.junit.Assert.assertEquals;



public class AnimationModelImplTest {

  @Before
  public void setUp() {
    //example Action objects
    Action move = new Move("Move", 300, 300);
    Action changeColor = new ChangeColor("ColorChange", 100,100,100);
    Action changeDims = new ChangeDims("Dim Change", 100, 200);

    //example Shape objects
    Shape rect = new AbstractShape() //need to make Rectangle class
    Shape


    AnimationModel model = new AnimationModelImpl(500,500,)
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCanvasSizeHeight() {
    new AnimationModelImpl(0, 500, new ArrayList<>());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCanvasSizeWidth() {
    new AnimationModelImpl(500, 0, new ArrayList<>());
  }


}
