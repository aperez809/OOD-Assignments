package view;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class AnimationGraphicsView extends JFrame implements IView {

  private JPanel shapeAnimPanel;
  private JScrollPane scrollPane;
  Consumer<String> commandCallback;

  public AnimationGraphicsView() {
    super();
    this.setSize(500,500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());
    this.shapeAnimPanel = new JPanel();
    this.setPreferredSize(new Dimension(500,500));
    scrollPane = new JScrollPane(shapeAnimPanel);
    this.add(scrollPane, BorderLayout.CENTER);
    this.scrollPane = scrollPane;
    this.commandCallback = commandCallback;
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void setCommandCallback(Consumer<String> callback) {
    this.commandCallback = callback;
  }

  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void refresh() {
    this.repaint();
  }
}
