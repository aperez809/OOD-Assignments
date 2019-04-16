package cs3500.animator.provider.view;

import cs3500.animator.model.Shape;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * This class represents the implementation of the visual view.
 * <p>
 *   The visual view will render the shapes passed to it into a Javax Swing JPanel.
 *   The visual view allows to set custom bounds and initiate render of one frame.
 * </p>
 */
public class VisualView implements AnimationView {
  private int w;
  private int h;
  private int speed;
  private JFrame viewFrame;
  private AnimationPanel animationPanel;


  /**
   * Initialize the view to a default JPanel, ready for rendering.
   */
  public VisualView() {
    JScrollPane scrollPane;
    viewFrame = new JFrame();
    viewFrame.setTitle("Animation Visual View");
    viewFrame.setSize(w, h);
    viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.viewFrame.setLayout(new BorderLayout());
    animationPanel = new AnimationPanel();
    animationPanel.setPreferredSize(new Dimension(w, h));
    animationPanel.setLayout(null);
    scrollPane = new JScrollPane(animationPanel);
    this.viewFrame.add(scrollPane, BorderLayout.CENTER);
    this.viewFrame.pack();
    viewFrame.setVisible(true);
  }

  /**
   * Set the view bounds.
   * @param x x-coordinate of the top-left corner of the view
   * @param y y-coordinate of the top-left corner of the view
   * @param w width of the view
   * @param h height of the view
   */
  public void setBounds(int x, int y, int w, int h) {
    if (w < 0 || h < 0) {
      throw new IllegalArgumentException("Width or height is negative");
    }

    this.w = w;
    this.h = h;
    viewFrame.setBounds(x,y,w,h);
  }

  /**
   * Render the shapes provided at the current tick.
   * @param tick the current tick of the model
   * @param shapes the list of shape to be rendered
   */
  public void render(int tick, List<Shape> shapes) {
    animationPanel.setShapes(shapes);
    viewFrame.revalidate();
    viewFrame.repaint();

    try {
      Thread.sleep(1000 / speed);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Set the event listener to a controller.
   * @param l the listener to set to
   */
  public void setListener(ActionListener l) {
    throw new UnsupportedOperationException("Visual view does not support action listener");
  }

  /**
   * Get the speed input by the user.
   * @return the speed in the input box
   */
  public int getSpeed() {
    return speed;
  }

  /**
   * Set the speed input box to a value.
   * @param speed the speed to set
   */
  public void setSpeed(int speed) {
    if (speed <= 0) {
      throw new IllegalArgumentException("Invalid animation speed");
    }

    this.speed = speed;
  }

  /**
   * Set the output destination file. Print to system console if not specified.
   * @param outFile the name of the output file
   */
  public void setOutputFile(String outFile) {
    throw new UnsupportedOperationException("Visual view does not support file output");
  }
}