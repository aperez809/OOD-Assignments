package cs3500.animator.view;

import cs3500.animator.model.Shape;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class AnimationPanelView extends JPanel {

  private ArrayList<Shape> shapes;

  public AnimationPanelView(ArrayList<Shape> shapes) {
    this.shapes = shapes;
  }

  public void paintComponent(Graphics g) {
    Graphics2D twoDimG = (Graphics2D) g;
    super.paintComponent(g);

    for (Shape s : this.shapes) {
      
    }


  }

}
