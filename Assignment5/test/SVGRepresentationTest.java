import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.model.util.AnimationReader;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGRepresentation;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;
import java.io.FileReader;
import java.io.IOException;

/**
 * Holds only those tests that apply to the SVGRepresentation class.
 */
public class SVGRepresentationTest {
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
    testView = new SVGRepresentation(
            testModel.getShapes(),
            testModel.getWidth(),
            testModel.getHeight(),
            5);
  }

  @Test
  public void testExpectedOutput() {
    try {
      testView.createAnimOutput();
    }
    catch (IOException e) {
      e.printStackTrace();
    }

    assertEquals("<svg width=\"800\" height=\"800\" version=\"1.1\"\n" +
            "     xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "<rect id=\"window000\" x=\"0\" y=\"0\" width=\"1\" height=\"1\" fill=\"rgb(0,0,0)\"" +
            " visibility=\"visible\" >\n" +
            "<animate attributeType=\"xml\" begin=\"200ms\" dur=\"0ms\" attributeName=\"x\" " +
            "from=\"100\" to=\"100\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"200ms\" dur=\"0ms\" attributeName=\"y\" " +
            "from=\"600\" to=\"600\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"200ms\" dur=\"0ms\" attributeName=\"height\"" +
            " from=\"20\" to=\"20\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"200ms\" dur=\"0ms\" attributeName=\"width\" " +
            "from=\"20\" to=\"20\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"200ms\" dur=\"0ms\" attributeName=\"fill\"" +
            " from=\"rgb(255,255,255)\" to=\"rgb(255,255,255)\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"200ms\" dur=\"3800ms\" attributeName=\"x\"" +
            " from=\"100\" to=\"100\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"200ms\" dur=\"3800ms\" attributeName=\"y\"" +
            " from=\"600\" to=\"200\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"200ms\" dur=\"3800ms\"" +
            " attributeName=\"height\" from=\"20\" to=\"20\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"200ms\" dur=\"3800ms\" " +
            "attributeName=\"width\" from=\"20\" to=\"20\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"200ms\" dur=\"3800ms\" " +
            "attributeName=\"fill\" from=\"rgb(255,255,255)\" to=\"rgb(255,255,255)\" " +
            "fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"4000ms\" dur=\"8000ms\" " +
            "attributeName=\"x\" from=\"100\" to=\"400\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"4000ms\" dur=\"8000ms\" " +
            "attributeName=\"y\" from=\"200\" to=\"400\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"4000ms\" dur=\"8000ms\" " +
            "attributeName=\"height\" from=\"20\" to=\"20\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"4000ms\" dur=\"8000ms\" " +
            "attributeName=\"width\" from=\"20\" to=\"20\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"4000ms\" dur=\"8000ms\" " +
            "attributeName=\"fill\" from=\"rgb(255,255,255)\" to=\"rgb(255,255,255)\" " +
            "fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"12000ms\" dur=\"28000ms\"" +
            " attributeName=\"x\" from=\"400\" to=\"400\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"12000ms\" dur=\"28000ms\" " +
            "attributeName=\"y\" from=\"400\" to=\"710\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"12000ms\" dur=\"28000ms\"" +
            " attributeName=\"height\" from=\"20\" to=\"20\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"12000ms\" dur=\"28000ms\"" +
            " attributeName=\"width\" from=\"20\" to=\"20\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"12000ms\" dur=\"28000ms\"" +
            " attributeName=\"fill\" from=\"rgb(255,255,255)\" to=\"rgb(255,255,255)\" " +
            "fill=\"freeze\"/>\n" +
            "</rect>\n" +
            "</svg>", testView.getOutput().toString());
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
