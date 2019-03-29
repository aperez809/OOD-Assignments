
package cs3500.animator.view;

import cs3500.animator.controller.ExcellenceController;
import cs3500.animator.model.IAction;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.Shape;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * Represents the view that creates a System.out based output that is printed to the screen.
 * Takes in many of the same arguments as the other representations, but produces a purely textual
 * representation of the animation.
 */
public class TextRepresentation implements IView {
  private ArrayList<Shape> shapes;
  private StringBuilder output;
  private int maxX;
  private int maxY;
  private int width;
  private int height;

  @Override
  public void setActionListener(ExcellenceController listener) {
    return;
  }

  /**
   * Constructs the TextRepresentation object that will handle all shapes and their actions in
   * order to produce the final output.
   *
   * @param shapes list of shapes to interact with/perform their actions
   * @param maxX max X coordinate of canvas
   * @param maxY max Y coordinate of canvas
   * @param width width of the canvas
   * @param height height of the canvas
   */
  public TextRepresentation(ArrayList<Shape> shapes, int maxX, int maxY, int width, int height) {
    this.shapes = shapes;
    this.output = new StringBuilder();
    this.maxX = maxX;
    this.maxY = maxY;
    this.width = width;
    this.height = height;
  }

  /**
   * Make the view visible. This is usually called
   * after the view is constructed.
   */
  @Override
  public void makeVisible() {
    throw new UnsupportedOperationException("Only for Visual Representations");
  }


  /**
   * Provide the view with a callback option to
   * process a command. Not yet implemented.
   *
   * @param callback object
   */
  @Override
  public void setCommandCallback(Consumer<String> callback) {
    throw new UnsupportedOperationException("Only for Visual Representations");
  }

  /**
   * Transmit an error message to view, in case
   * the command could not be processed correctly. Comes in the form of either a JOptionPane
   * or simple text.
   *
   * @param error The error message to be transmitted
   */
  @Override
  public void showErrorMessage(String error) {
    throw new UnsupportedOperationException("Only for Visual Representations");
  }

  /**
   * Signal the view to draw itself again.
   */
  @Override
  public void refresh() {
    throw new UnsupportedOperationException("Only for Visual Representations");
  }

  /**
   * Gets the output of the program for the SVG and Text representations.
   *
   * @return Appendable that changes based on both the command line arguments and the
   *          type of representation
   */
  @Override
  public Appendable getOutput() {
    this.output.deleteCharAt(this.output.length() - 1);
    return this.output;
  }

  /**
   * Writes the output of the program to a file. File name, location, etc. are handled externally
   * and collected by the method.
   *
   * @throws IOException in case there is an issue with writing to the file
   *          (not found, corrupt, etc)
   */
  @Override
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

  /**
   * adds a panel of type AnimationPanelView (extended from JPanel) to the JFrame. Only used in
   * by the visual representations.
   *
   * @param panel AnimationPanelView to add to the JFrame
   */
  @Override
  public void add(AnimationPanelView panel) {
    throw new UnsupportedOperationException("Only for Visual Representations");
  }

  @Override
  public void flipPause() {
    throw new UnsupportedOperationException("Only for EditorView class");

  }

  @Override
  public void flipReverse() {
    throw new UnsupportedOperationException("Only for EditorView class");

  }

  @Override
  public void flipLooping() {
    throw new UnsupportedOperationException("Only for EditorView class");

  }

  @Override
  public void speedUp() {
    throw new UnsupportedOperationException("Only for EditorView class");

  }

  @Override
  public void slowDown() {
    throw new UnsupportedOperationException("Only for EditorView class");

  }

  @Override
  public boolean isPaused() {
    throw new UnsupportedOperationException("Only for EditorView class");
  }

  @Override
  public boolean isReversed() {
    throw new UnsupportedOperationException("Only for EditorView class");
  }

  @Override
  public boolean isWillLoop() {
    throw new UnsupportedOperationException("Only for EditorView class");
  }

  @Override
  public int getCurrTick() {
    throw new UnsupportedOperationException("Only for EditorView class");
  }

  @Override
  public void executeMove(Shape s, IAction a) {
    throw new UnsupportedOperationException("Only for EditorView class");

  }

  @Override
  public void repaint() {
    throw new UnsupportedOperationException("Only for EditorView class");

  }

  @Override
  public void setCurrTick(int i) {
    throw new UnsupportedOperationException("Only for EditorView class");

  }

  @Override
  public int getMaxTick() {
    throw new UnsupportedOperationException("Only for EditorView class");
  }

  @Override
  public void startTimer() {
    return;
  }

  @Override
  public void setMaxTick(int newMax) {
    throw new UnsupportedOperationException("Only for EditorView class");
  }

  @Override
  public IView getPanel() {
    throw new UnsupportedOperationException("Only for EditorView class");
  }

  @Override
  public void addActionListener(ExcellenceController excellenceController) {
    return;
  }

  @Override
  public void toggleAddShapeOptions() {
    throw new UnsupportedOperationException("Only for EditorView class");
  }

  @Override
  public String getSelectedItem() {
    throw new UnsupportedOperationException("Only for EditorView class");
  }

  @Override
  public void toggleDeleteShapeOptions() {
    throw new UnsupportedOperationException("Only for EditorView class");
  }
}

