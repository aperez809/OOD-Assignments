
package cs3500.animator.view;

import cs3500.animator.model.Rectangle;
import cs3500.animator.model.Shape;
import java.util.ArrayList;
import java.util.function.Consumer;

public class TextRepresentation implements IView {
  private ArrayList<Shape> shapes;
  private StringBuilder output;
  private int maxX;
  private int maxY;
  private int width;
  private int height;

  public TextRepresentation(ArrayList<Shape> shapes, int maxX, int maxY, int width, int height) {
    this.shapes = shapes;
    this.output = new StringBuilder();
    this.maxX = maxX;
    this.maxY = maxY;
    this.width = width;
    this.height = height;
  }

  @Override
  public void makeVisible() {
    throw new UnsupportedOperationException("Only for Visual Representations");
  }

  @Override
  public void setCommandCallback(Consumer<String> callback) {
    throw new UnsupportedOperationException("Only for Visual Representations");
  }

  @Override
  public void showErrorMessage(String error) {
    throw new UnsupportedOperationException("Only for Visual Representations");
  }

  @Override
  public void refresh() {
    throw new UnsupportedOperationException("Only for Visual Representations");
  }

  @Override
  public void add(AnimationPanelView panel) {
    throw new UnsupportedOperationException("Only for Visual Representations");
  }

  @Override
  public Appendable getOutput() {
    this.output.deleteCharAt(this.output.length() - 1);
    return this.output;
  }

  public void createAnimOutput() {
    String type;
    output.append(String.format("canvas %s %s %s %s\n",
            this.maxX, this.maxY, this.width, this.height));
    for (Shape s : this.shapes) {
      if (s instanceof Rectangle) {
        type = "rectangle";
      }
      else {
        type = "ellipse";
      }

      output.append(String.format("Shape %s %s\n", s.getShapeName(), type));
      s.execute(s.getActions());
      output.append(s.getTrackedState());

      }
    }
}

