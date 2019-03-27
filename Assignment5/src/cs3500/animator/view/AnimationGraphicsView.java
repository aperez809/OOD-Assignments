package cs3500.animator.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.util.function.Consumer;


/**
 * Represents the actual image that is produced for the visual representation.
 * Aggregates and handles all of the higher-level components of a visualized animation
 * like the images, panels, scrollPanes, etc.
 */
public class AnimationGraphicsView extends JFrame implements IView {


  Consumer<String> commandCallback;
  private int maxX;
  private int maxY;
  private int height;
  private int width;

  /**
   * Constructs an object that contains all the pieces needed to create the animation.
   * Also sets some defaults for options that are going to control the way that the
   * animation is rendered (Layout, bound setting, scrollPanes, etc).
   *
   * @param shapeAnimPanel extended from JPanel which handles the lower-level logic of shape drawing
   * @param maxX maximum X value for the canvas
   * @param maxY maximum Y value for the canvas
   * @param width width of the canvas
   * @param height height of the canvas
   */
  public AnimationGraphicsView(AnimationPanelView shapeAnimPanel,
                               int maxX,
                               int maxY,
                               int width,
                               int height) {
    super();
    this.setSize(width,height);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    this.setBounds(maxX, maxY, width, height);
    AnimationPanelView panel = shapeAnimPanel;
    JScrollPane scrollPane = new JScrollPane(panel);
    scrollPane.setPreferredSize(new Dimension(width + 20, height + 20));
    this.add(scrollPane, BorderLayout.CENTER);

    this.pack();
  }

  /**
   * Make the view visible. This is only called
   * after the view is constructed in the Visual representation.
   */
  public void makeVisible() {
    this.setVisible(true);
  }

  /**
   * Provide the view with a callback option to
   * process a command. Not yet used for this iteration of the Animator.
   *
   * @param callback object
   */
  public void setCommandCallback(Consumer<String> callback) {
    this.commandCallback = callback;
  }


  /**
   * Transmit an error message to view, in case
   * the command could not be processed correctly. Comes in the form of either a JOptionPane
   * or simple text.
   *
   * @param error The error message to be transmitted
   */
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
  }


  /**
   * Signal the view to draw itself again. Only used in the visual representation.
   */
  public void refresh() {
    this.repaint();
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
    throw new UnsupportedOperationException("Not yet supported");
  }
}
