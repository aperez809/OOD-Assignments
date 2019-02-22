import model.*;
import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;
import static org.junit.Assert.assertEquals;



public class AnimationModelImplTest {

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCanvasSizeHeight() {
    new AnimationModelImpl(0, 500, new HashMap<>());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCanvasSizeWidth() {
    new AnimationModelImpl(500, 0, new HashMap<>());
  }

  @Test
  public void testValidCanvasCreation() {
    assertEquals("", testModel.getAnimState());
  }
}
