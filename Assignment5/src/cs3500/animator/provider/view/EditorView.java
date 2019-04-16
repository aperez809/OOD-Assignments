package cs3500.animator.provider.view;

import cs3500.animator.model.Shape;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * This class represents the implementation of the editor view.
 * <p>
 *   This class reuses the JPanel animation panel of the visual view, and display additional
 *   control around it. The supported runtime controls are: start, pause, restart, loop, and set
 *   speed.
 *   The view also displays controls to add/modify/delete shapes in the animation. The current
 *   supported types are rectangle and ellipse.
 * </p>
 */
public class EditorView implements AnimationView {
  private final static String START = "▶";
  private final static String PAUSE = "❙❙";
  private final static String LOOP_ON = "↺ ✓";
  private final static String LOOP_OFF = "↺";
  private final static String RESTART = "◼";
  private final static String SPEED = "Set Speed";
  private final static String RECTANGLE = "▢";
  private final static String ELLIPSE = "◯";
  private final static String REMOVE_SHAPE = "Remove";
  private final static String ADD_KEYFRAME = "Add";
  private final static String REMOVE_KEYFRAME = "Remove";

  private JFrame viewFrame;
  private AnimationPanel animationPanel;
  private int speed;

  private JPanel buttonPanel;
  private JButton startPauseButton;
  private JButton restartButton;
  private JButton loopButton;
  private JButton speedButton;
  private JTextField speedInput;

  private JPanel shapePanel;
  private JButton rectangleButton;
  private JButton ellipseButton;
  private JButton removeShapeButton;

  private JPanel keyFramePanel;
  private JButton addKeyFrameButton;
  private JButton removeKeyFrameButton;

  /**
   * Initialize the view to a default JPanel, ready for rendering.
   */
  public EditorView() {
    JScrollPane scrollPane = new JScrollPane();
    viewFrame = new JFrame();
    viewFrame.setTitle("Animation Editor View");
    viewFrame.setSize(400, 400);
    viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.viewFrame.setLayout(new BorderLayout());
    animationPanel = new AnimationPanel();
    animationPanel.setPreferredSize(new Dimension(500, 500));
    animationPanel.setLayout(null);
    scrollPane = new JScrollPane(animationPanel);
    this.viewFrame.add(scrollPane, BorderLayout.CENTER);

    //button panel
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    viewFrame.add(buttonPanel, BorderLayout.SOUTH);

    startPauseButton = new JButton(PAUSE);
    restartButton = new JButton(RESTART);
    loopButton = new JButton(LOOP_ON);
    speedButton = new JButton(SPEED);
    speedInput = new JTextField(10);

    startPauseButton.setActionCommand("start/pause");
    restartButton.setActionCommand("restart");
    loopButton.setActionCommand("loop");
    speedButton.setActionCommand("speed");

    buttonPanel.add(startPauseButton);
    buttonPanel.add(restartButton);
    buttonPanel.add(loopButton);
    buttonPanel.add(speedInput);
    buttonPanel.add(speedButton);

    //shape panel
    shapePanel = new JPanel();
    shapePanel.setBorder(new TitledBorder("Shape"));
    shapePanel.setLayout(new BoxLayout(shapePanel, BoxLayout.Y_AXIS));
    viewFrame.add(shapePanel, BorderLayout.WEST);

    rectangleButton = new JButton(RECTANGLE);
    ellipseButton = new JButton(ELLIPSE);
    removeShapeButton = new JButton(REMOVE_SHAPE);

    shapePanel.add(rectangleButton);
    shapePanel.add(ellipseButton);
    shapePanel.add(removeShapeButton);

    //keyframe panel
    keyFramePanel = new JPanel();
    keyFramePanel.setBorder(new TitledBorder("KeyFrame"));
    keyFramePanel.setLayout(new BoxLayout(keyFramePanel, BoxLayout.Y_AXIS));
    viewFrame.add(keyFramePanel, BorderLayout.EAST);

    addKeyFrameButton = new JButton(ADD_KEYFRAME);
    removeKeyFrameButton = new JButton(REMOVE_KEYFRAME);

    keyFramePanel.add(addKeyFrameButton);
    keyFramePanel.add(removeKeyFrameButton);

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
    viewFrame.setBounds(x,y,
        w + shapePanel.getWidth() + keyFramePanel.getWidth(),
        h + buttonPanel.getHeight());
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
    startPauseButton.addActionListener(l);
    restartButton.addActionListener(l);
    loopButton.addActionListener(l);
    speedButton.addActionListener(l);

    startPauseButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (startPauseButton.getText().equals(START)) {
          startPauseButton.setText(PAUSE);
        } else {
          startPauseButton.setText(START);
        }
      }
    });

    restartButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        startPauseButton.setText(START);
      }
    });

    loopButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (loopButton.getText().equals(LOOP_ON)) {
          loopButton.setText(LOOP_OFF);
        } else {
          loopButton.setText(LOOP_ON);
        }
      }
    });

    PopUpOptionPanel createRectPopup = new PopUpOptionPanel("Add Rectangle");
    rectangleButton.addActionListener(createRectPopup);
    createRectPopup.addActionListener(l);

    PopUpOptionPanel createEllipsePopup = new PopUpOptionPanel("Add Ellipse");
    ellipseButton.addActionListener(createEllipsePopup);
    createEllipsePopup.addActionListener(l);

    PopUpOptionPanel removeShapePopup = new PopUpOptionPanel("Remove Shape");
    removeShapeButton.addActionListener(removeShapePopup);
    removeShapePopup.addActionListener(l);

    PopUpOptionPanel addKeyFramePopup = new PopUpOptionPanel("Add Keyframe");
    addKeyFrameButton.addActionListener(addKeyFramePopup);
    addKeyFramePopup.addActionListener(l);

    PopUpOptionPanel removeKeyFramePopup = new PopUpOptionPanel("Remove Keyframe");
    removeKeyFrameButton.addActionListener(removeKeyFramePopup);
    removeKeyFramePopup.addActionListener(l);
  }

  /**
   * Get the speed input by the user.
   * @return the speed in the input box
   */
  public int getSpeed() {
    speed = Integer.parseInt(speedInput.getText());
    return speed;
  }

  /**
   * Set the speed input box to a value.
   * @param speed the speed to set
   */
  public void setSpeed(int speed) {
    this.speed = speed;
    speedInput.setText(Integer.toString(speed));
  }


  /**
   * Set the output destination file. Print to system console if not specified.
   * @param outFile the name of the output file
   */
  public void setOutputFile(String outFile) {
    throw new UnsupportedOperationException("Editor view does not support file output");
  }
}
