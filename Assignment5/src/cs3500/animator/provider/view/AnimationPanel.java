package cs3500.animator.provider.view;


import cs3500.animator.model.Shape;


import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.util.List;

/**
 * This class represents the implementation of the visual animation view panel.
 * <p>
 *   The shape will be painted to the panel using JavaSwing. The supported types of shapes are
 *   retangle and ellipse.
 * </p>
 */
public class AnimationPanel extends JPanel {
  private List<Shape> shapes;

  public AnimationPanel() {
    super();
    this.shapes = new ArrayList<>();
  }

  /**
   * Setting the panel shapes.
   * @param shapes the shapes to draw
   */
  public void setShapes(List<Shape> shapes) {
    this.shapes = shapes;
  }

  /**
   * Paint the component.
   * @param gr the graphic component to draw
   */
  public void paintComponent(Graphics gr) {
    for (Shape s : shapes) {
      int x = (int) Math.round(s.getPosition().getX());
      int y = (int) Math.round(s.getPosition().getY());
      int w = (int) Math.round(s.getSize().getW());
      int h = (int) Math.round(s.getSize().getH());
      int r = (int) Math.round(s.getColor().getR());
      int g = (int) Math.round(s.getColor().getG());
      int b = (int) Math.round(s.getColor().getB());

      gr.setColor(new Color(r, g, b));

      if (s.getShapeType().equals("ellipse")) {
        gr.fillOval(x, y, w, h);
      } else if (s.getShapeType().equals("rectangle")) {
        gr.fillRect(x, y, w, h);
      }
    }
  }
}
