package cs3500.animator.view;

import cs3500.animator.model.*;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.Shape;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Consumer;


public class SVGRepresentation extends JPanel implements IView, ActionListener {

  private ArrayList<Shape> shapes;
  private Appendable output;
  private int speed;

  public SVGRepresentation(ArrayList<Shape> shapes, int speed) {
    super();
    this.shapes = shapes;
    this.speed = 1000 / speed;
    this.output = new StringBuilder();
  }

  @Override
  public void createAnimOutput() throws IOException {
    output.append(String.format("<svg width=\"%s\" height=\"%s\" version=\"1.1\"\n" +
            "     xmlns=\"http://www.w3.org/2000/svg\">\n", this.getWidth(), this.getHeight()));
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
                      "<%s id=\"%s\" x=\"%s\" y=\"%s\" width=\"%s\" height=\"%s\" fill=\"rgb(%s,%s,%s)\" visibility=\"visible\" >\n",
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

    //"<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" attributeName=\"x\" from=\"200\" to=\"300\" fill=\"freeze\" />"
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
                            "attributeName=\"y\" from=\"rgb(%s,%s,%s)\" " +
                            "to=\"rgb(%s,%s,%s)\" fill=\"freeze\"/>\n",
                    start[0] * this.speed,
                    timeToComplete,
                    start[5], start[6], start[7],
                    end[5], end[6], end[7]));
  }

  @Override
  public Appendable getOutput() {
    return this.output;
  }

  @Override
  public void makeVisible() {
    throw new UnsupportedOperationException("Only for Visual View");
  }

  @Override
  public void setCommandCallback(Consumer<String> callback) {

  }

  @Override
  public void showErrorMessage(String error) {

  }

  @Override
  public void refresh() {
    throw new UnsupportedOperationException("Only for Visual View");
  }

  @Override
  public void add(AnimationPanelView panel) {
    throw new UnsupportedOperationException("Only for Visual View");
  }

  @Override
  public void actionPerformed(ActionEvent e) {

  }

}
