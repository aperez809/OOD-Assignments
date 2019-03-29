package cs3500.animator.view;

import cs3500.animator.controller.ExcellenceController;
import cs3500.animator.model.Ellipse;
import cs3500.animator.model.IAction;
import cs3500.animator.model.Location;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.Shape;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * Represents the pieces of an animation that actually contain images. Handles all the lower-level
 * logic for drawing and placing shapes on the canvas.
 */
public class AnimationPanelView extends JPanel implements IView, ActionListener {

  protected ArrayList<Shape> shapes;
  protected int currTick;
  protected int maxTick;
  protected Timer t;




  /**
   * Creates an object that will hold all the shapes that need to be drawing and represent the
   * frames of the animation. Also instantiates a timer that will be used to keep track of ticks
   * and the speed at which they are incremented.
   *
   * @param shapes list of shapes that need to be drawn.
   * @param speed tick speed which is calculated as the number of times per second that
   *              a tick moves
   */
  public AnimationPanelView(ArrayList<Shape> shapes, int speed) {
    super();
    int tickSpeed = (int) (1000 / speed);
    this.shapes = shapes;
    this.setBackground(Color.WHITE);
    this.t = new Timer(tickSpeed, this);
    this.currTick = 0;
    this.maxTick = getMaxTick();
    t.start();
  }

  @Override
  public void paintComponent(Graphics g) {
    Graphics2D twoDimG = (Graphics2D) g;
    super.paintComponent(twoDimG);

    for (Shape s : this.shapes) {
      twoDimG.setColor(s.getColor());
      Integer currMin = null;
      Integer currMax = null;
      for (IAction a : s.getActions()) {

        if (currMax == null || a.getEndTick() > currMax) {
          currMax = a.getEndTick();
        }
        if (currMin == null || a.getStartTick() < currMin) {
          currMin = a.getStartTick();
        }
        if (currMin <= currTick && currMax >= currTick) {
          if (s instanceof Rectangle) {
            twoDimG.fillRect(s.getCoords().getX(), s.getCoords().getY(), s.getWidth(), s.getHeight());
          } else if (s instanceof Ellipse) {
            twoDimG.fillOval(s.getCoords().getX(), s.getCoords().getY(), s.getWidth(), s.getHeight());
          }
        }
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    for (Shape s : this.shapes) {
      for (IAction a : s.getActions()) {
        if (currTick >= a.getStartTick() && currTick <= a.getEndTick()) {
          executeMove(s, a);
        }
      }
    }
    this.repaint();
    if (currTick >= getMaxTick()) {
      currTick = getMaxTick();
    }
    else {
      currTick++;
    }

    System.out.println(currTick);
  }


  public void executeMove(Shape s, IAction a) {

    int[] start = a.getStartState();
    int[] end = a.getEndState();
    int divisor = end[0] - start[0];
    if (divisor == 0) {
      divisor = 1;
    }

    s.setCoords(new Location(
            jumpOrAnimate(s.getCoords().getX(), start[1], end[1], divisor),
            jumpOrAnimate(s.getCoords().getY(), start[2], end[2], divisor)));

    s.setHeight(jumpOrAnimate(s.getHeight(), start[3], end[3], divisor));

    s.setWidth(jumpOrAnimate(s.getWidth(), start[4], end[4], divisor));

    s.setColor(
            new Color(
                    jumpOrAnimateRGB(s.getColor().getRed(), start[5], end[5], divisor),
                    jumpOrAnimateRGB(s.getColor().getGreen(), start[6], end[6], divisor),
                    jumpOrAnimateRGB(s.getColor().getBlue(), start[7], end[7], divisor)));
  }

  //determines if a Shape on the canvas needs to be instantly relocated or smoothly animated
  private int jumpOrAnimate(int curr, int start, int end, int divisor) {
    if (divisor == 1) {
      return end;
    }
    else {
      int diff = end - start;
      return curr + diff / divisor;
    }
  }

  private int jumpOrAnimateRGB(int curr, int start, int end, int divisor) {
    if (divisor == 1) {
      return end;
    }
    else {
      int diff = end - start;
      int endVal = curr + diff / divisor;

      if (endVal > 255) {
        return 255;
      }
      else if (endVal < 0) {
        return 0;
      }
      else {
        return endVal;
      }
    }
  }

  /**
   * Make the view visible. This is only called
   * after the view is constructed in the Visual representation.
   */
  @Override
  public void makeVisible() {
    throw new UnsupportedOperationException("For use by AnimationGraphics View class");
  }


  /**
   * Provide the view with a callback option to
   * process a command. Not yet used for this iteration of the Animator.
   *
   * @param callback object
   */
  @Override
  public void setCommandCallback(Consumer<String> callback) {
    throw new UnsupportedOperationException("For use by AnimationGraphics View class");
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
    new JOptionPane();
  }

  /**
   * Signal the view to draw itself again. Only used in the visual representation.
   */
  @Override
  public void refresh() {
    throw new UnsupportedOperationException("For use by AnimationGraphics View class");
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
    throw new UnsupportedOperationException("Only for TextRepresentations");
  }

  /**
   * Gets the output of the program for the SVG and Text representations.
   *
   * @return Appendable that changes based on both the command line arguments and the
   *          type of representation
   */
  @Override
  public Appendable getOutput() {
    throw new UnsupportedOperationException("Only for TextRepresentations");
  }

  /**
   * adds a panel of type AnimationPanelView (extended from JPanel) to the JFrame. Only used in
   * by the visual representations.
   *
   * @param panel AnimationPanelView to add to the JFrame
   */
  @Override
  public void add(AnimationPanelView panel) {
    throw new UnsupportedOperationException("Only for AnimationGraphicsView class");
  }

  @Override
  public void setCurrTick(int i) {
    currTick = i;
  }

  @Override
  public int getCurrTick() {
    return currTick;
  }

  @Override
  public void flipPause() {
    return;
  }

  @Override
  public void flipReverse() {
    return;

  }

  @Override
  public void flipLooping() {
    return;

  }

  @Override
  public void speedUp() {
    return;

  }

  @Override
  public void slowDown() {
    return;

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
  public void setActionListener(ExcellenceController listener) {
    throw new UnsupportedOperationException("Only for EditorView class");
  }

  @Override
  public void setMaxTick(int newMax) {
    return;
  }

  public void startTimer() {
    this.t.start();
  }

  @Override
  public IView getPanel() {
    return this;
  }

  @Override
  public int getMaxTick() {
    int currMax = 0;
    for (Shape s: this.shapes) {
      for (IAction a : s.getActions()) {
        if (a.getEndTick() > currMax) {
          currMax = a.getEndTick();
        }
      }
    }
    return currMax;
  }

  @Override
  public void addActionListener(ExcellenceController excellenceController) {
    this.t.addActionListener(excellenceController);
  }
}