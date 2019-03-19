import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.model.util.AnimationReader;
import cs3500.animator.view.IView;
import cs3500.animator.view.TextRepresentation;
import org.junit.Before;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

public class TextRepresentationTest {
  AnimationModel testModel;
  IView testView;

  @Before
  public void setUp() {
    try {
      testModel = AnimationReader.parseFile(new FileReader("buildingsSimple.txt"),
              new AnimationModelImpl.Builder(
                      new AnimationModelImpl()));
    } catch (IOException e) {
      e.printStackTrace();
    }
    testView = new TextRepresentation(
            testModel.getShapes(),
            testModel.getMaxX(),
            testModel.getMaxY(),
            testModel.getWidth(),
            testModel.getHeight());
  }
  @Test
  public void testExpectedOutput() {
    try {
      testView.createAnimOutput();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals(
            "canvas 0 0 800 800\n" +
                    "Shape window000 rectangle\n" +
                    "Motion window000 1 0 0 1 1 0 0 0      " +
                    "1 100 600 20 20 255 255 255    \n" +
                    "Motion window000 1 100 600 20 20 255 255 255     " +
                    " 20 100 200 20 20 255 255 255    \n" +
                    "Motion window000 20 100 200 20 20 255 255 255      " +
                    "60 400 400 20 20 255 255 255    \n" +
                    "Motion window000 60 400 400 20 20 255 255 255     " +
                    " 200 400 710 20 20 255 255 255    ", testView.getOutput().toString());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testMakeVisibleUnsupported() {
    testView.makeVisible();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testRefreshUnsupported() {
    testView.refresh();
  }
  @Test(expected = UnsupportedOperationException.class)
  public void testAddUnsupported() {
    testView.add(null);
  }
  @Test(expected = UnsupportedOperationException.class)
  public void testShowErrorMessageUnsupported() {
    testView.showErrorMessage(null);
  }
  @Test(expected = UnsupportedOperationException.class)
  public void testCommandCallbackUnsupported() {
    testView.setCommandCallback(null);
  }

}
