package cs3500.animator.view;

import cs3500.animator.controller.ExcellenceController;
import cs3500.animator.model.IAction;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Consumer;


/**
 * Represents a method for developing an SVG version of the animation. Takes in shapes to draw,
 * speed to calculate how fast to draw, and attributes about the canvas, then spits it out into
 * a file (name given by command line args).
 */
public class SVGRepresentation implements IView, ActionListener {

  private ArrayList<Shape> shapes;
  private Appendable output;
  private int speed;
  private int height;
  private int width;

  /**
   * Creates an object that will render all the text needed for SVG-formatted file to be made (list
   * of shapes, canvas size, speed of animation, etc).
   *
   * @param shapes list of shapes to draw
   * @param width width of the canvas
   * @param height height of the canvas
   * @param speed number of ticks that are incremented per second
   */
  public SVGRepresentation(ArrayList<Shape> shapes,
                           int width,
                           int height,
                           int speed) {
    super();
    this.shapes = shapes;
    this.speed = 1000 / speed;
    this.output = new StringBuilder();
    this.width = width;
    this.height = height;
  }

  /**
   * Writes the output of the program to a file. File name, location, etc. are handled externally
   * and collected by the method.
   *
   * @throws IOException in case there is an issue with writing to the file
   *          (not found, corrupt, etc)
   */
  @Override
  public void createAnimOutput() throws IOException {
    output.append(String.format("<svg width=\"%s\" height=\"%s\" version=\"1.1\"\n" +
            "     xmlns=\"http://www.w3.org/2000/svg\">\n", this.width, this.height));
    for (Shape s : this.shapes) {
      String type;
      if (s instanceof Rectangle) {
        type = "rect";
      }
      else {
        type = "ellipse";
      }
      output.append(
              String.format(
                      "<%s id=\"%s\" x=\"%s\" y=\"%s\" width=\"%s\" height=\"%s\" " +
                              "fill=\"rgb(%s,%s,%s)\" visibility=\"visible\" >\n",
              type,
              s.getShapeName(),
              s.getCoords().getX(),
              s.getCoords().getY(),
                      s.getWidth(),
                      s.getHeight(),
                      s.getColor().getRed(),
                      s.getColor().getGreen(),
                      s.getColor().getBlue()));

      for (IAction a : s.getActions()) {
        createMoveLine(a);
      }
      output.append(String.format("</%s>\n", type));
    }
    output.append("</svg>");
  }

  private void createMoveLine(IAction a) throws IOException {
    int[] start = a.getStartState();
    int[] end = a.getEndState();
    int timeToComplete = (end[0] - start[0]) * this.speed;

    output.append(
            String.format(
                    "<animate attributeType=\"xml\" begin=\"%sms\" dur=\"%sms\" " +
                            "attributeName=\"x\" from=\"%s\" to=\"%s\" fill=\"freeze\"/>\n",
                    start[0] * this.speed,
                    timeToComplete,
                    start[1],
                    end[1]));

    output.append(
            String.format(
                    "<animate attributeType=\"xml\" begin=\"%sms\" dur=\"%sms\" " +
                            "attributeName=\"y\" from=\"%s\" to=\"%s\" fill=\"freeze\"/>\n",
                    start[0] * this.speed,
                    timeToComplete,
                    start[2],
                    end[2]));
    output.append(
            String.format(
                    "<animate attributeType=\"xml\" begin=\"%sms\" dur=\"%sms\" " +
                            "attributeName=\"height\" from=\"%s\" to=\"%s\" fill=\"freeze\"/>\n",
                    start[0] * this.speed,
                    timeToComplete,
                    start[3],
                    end[3]));
    output.append(
            String.format(
                    "<animate attributeType=\"xml\" begin=\"%sms\" dur=\"%sms\" " +
                            "attributeName=\"width\" from=\"%s\" to=\"%s\" fill=\"freeze\"/>\n",
                    start[0] * this.speed,
                    timeToComplete,
                    start[4],
                    end[4]));
    output.append(
            String.format(
                    "<animate attributeType=\"xml\" begin=\"%sms\" dur=\"%sms\" " +
                            "attributeName=\"fill\" from=\"rgb(%s,%s,%s)\" " +
                            "to=\"rgb(%s,%s,%s)\" fill=\"freeze\"/>\n",
                    start[0] * this.speed,
                    timeToComplete,
                    start[5], start[6], start[7],
                    end[5], end[6], end[7]));
  }

  /**
   * Gets the output of the program for the SVG and Text representations.
   *
   * @return Appendable that changes based on both the command line arguments and the
   *          type of representation
   */
  @Override
  public Appendable getOutput() {
    return this.output;
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


  @Override
  public void actionPerformed(ActionEvent e) {
    throw new UnsupportedOperationException("Only for Visual Representations");
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
  public void setActionListener(ExcellenceController listener) {
    return;
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
  public IView getPanel() {
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
  public void addActionListener(ExcellenceController excellenceController) {
    return;
  }
}
