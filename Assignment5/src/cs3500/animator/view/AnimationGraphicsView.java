package cs3500.animator.view;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class AnimationGraphicsView extends JFrame implements IView {

  private JPanel shapeAnimPanel;
  private JScrollPane scrollPane;
  Consumer<String> commandCallback;
  Timer t;
  int maxX;
  int maxY;
  int height;
  int width;

  public AnimationGraphicsView(JPanel shapeAnimPanel, int maxX, int maxY, int width, int height) {
    super();
    this.setSize(width,height);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());
    this.setBounds(maxX, maxY, width, height);
    //this.setPreferredSize(new Dimension(width,height));
    this.shapeAnimPanel = shapeAnimPanel;
    scrollPane = new JScrollPane(shapeAnimPanel);
    scrollPane.setPreferredSize(new Dimension(width, height));
    this.add(scrollPane, BorderLayout.CENTER);


    this.pack();
  }

  public void makeVisible() {
    this.setVisible(true);
  }

  public void setCommandCallback(Consumer<String> callback) {
    this.commandCallback = callback;
  }

  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
  }

  public void refresh() {
    this.repaint();
  }

  public void add(AnimationPanelView p) {
  }

  @Override
  public void createAnimOutput() {
    throw new UnsupportedOperationException("Only for TextRepresentations");
  }

  @Override
  public Appendable getOutput() {
    throw new UnsupportedOperationException("Only for TextRepresentations");  }
}
