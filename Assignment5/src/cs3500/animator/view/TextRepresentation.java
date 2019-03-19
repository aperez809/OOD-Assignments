
package cs3500.animator.view;

import cs3500.animator.model.Shape;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Consumer;

public class TextRepresentation implements IView {
  private ArrayList<Shape> shapes;
  private Appendable output;

  public TextRepresentation(ArrayList<Shape> shapes) {
    this.shapes = shapes;
    this.output = System.out;
  }

  @Override
  public void makeVisible() {

  }

  @Override
  public void setCommandCallback(Consumer<String> callback) {

  }

  @Override
  public void showErrorMessage(String error) {

  }

  @Override
  public void refresh() {

  }

  @Override
  public void add(AnimationPanelView panel) {

  }

  @Override
  public Appendable getOutput() {
    return this.output;
  }

  public void createAnimOutput() {
    for (Shape s : this.shapes) {
      s.execute(s.getActions());
      try {
        output.append(s.getTrackedState());
      } catch (IOException e1) {
        e1.printStackTrace();
      }
    }
  }
}
