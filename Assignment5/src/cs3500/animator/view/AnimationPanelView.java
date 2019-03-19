package cs3500.animator.view;

import cs3500.animator.model.*;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.function.Consumer;


public class AnimationPanelView extends JPanel implements IView, ActionListener {

  private ArrayList<Shape> shapes;
  private Timer t;
  private int currTick;
  private int speed;

  public AnimationPanelView(ArrayList<Shape> shapes, int speed) {
    super();
    this.shapes = shapes;
    this.setBackground(Color.WHITE);
    this.speed = (int) (1000 / speed);
    this.t = new Timer(this.speed, this);
    this.currTick = 0;
    t.start();
  }

  @Override
  public void paintComponent(Graphics g) {
    Graphics2D twoDimG = (Graphics2D) g;
    super.paintComponent(g);

    for (Shape s : this.shapes) {
      twoDimG.setColor(s.getColor());

      if (s instanceof Rectangle) {
        twoDimG.fillRect(s.getCoords().getX(), s.getCoords().getY(), s.getWidth(), s.getHeight());
      }
      else if (s instanceof Ellipse) {
        twoDimG.fillOval(s.getCoords().getX(), s.getCoords().getY(), s.getWidth(), s.getHeight());
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
    currTick++;
    System.out.println(currTick);
  }

  private void executeMove(Shape s, IAction a) {

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


  public void setTimerSpeed(int speed) {
    t = new Timer(1000 / speed, this);
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
  public void createAnimOutput() {
    throw new UnsupportedOperationException("Only for TextRepresentations");
  }

  @Override
  public Appendable getOutput() {
    throw new UnsupportedOperationException("Only for TextRepresentations");
  }
}
