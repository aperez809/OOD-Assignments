package cs3500.animator.view;

import cs3500.animator.model.IAction;
import cs3500.animator.model.Shape;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Implementation of Animation View allowing for the user to interact with the view and edit the
 * animation using the provided view screen options.
 */
public class EditorView extends AnimationPanelView implements ActionListener {

  //Utility booleans that are swapped every time a button is pressed
  //i.e. true to false and false to true
  private boolean isPaused;
  private boolean isReversed;
  private boolean willLoop;
  private boolean areAddShapeOptionsShown;
  private boolean areDeleteShapeOptionsShown;
  private boolean areModifyKeyFrameOptionsShown;

  //private JList shapesList;
  private JComboBox shapesList;
  private JComboBox modifyshapesList;




  //Final tick of animation
  private int maxTick;

  //Buttons for controlling how the animation plays
  private JButton playPauseButton;
  private JButton reverseButton;
  private JButton loopingButton;
  private JButton speedUpButton;
  private JButton slowDownButton;
  private JButton addShapeOptionsButton;
  private JButton deleteShapeOptionsButton;

  private JButton modifyKeyFrameButton;
  private JButton exitKeyFramesButton;
  private JButton exitNewShapeButton;

  private JTextField newShapeName;
  private JPanel newShapeInputPanel;

  private JTextField tickValueText;
  private JTextField xValueText;
  private JTextField yValueText;
  private JTextField widthValueText;
  private JTextField heightValueText;
  private JTextField redValueText;
  private JTextField greenValueText;
  private JTextField blueValueText;

  private JTextField newshapexValueText;
  private JTextField newshapeyValueText;
  private JTextField newshapewidthValueText;
  private JTextField newshapeheightValueText;
  private JTextField newshaperedValueText;
  private JTextField newshapegreenValueText;
  private JTextField newshapeblueValueText;

  private JComboBox keyFrameOpType;
  private JPanel modifyKeyFramePanel;

  //Clickable Shape types list
  //TODO: Capture selected element in a string after clicking submit button
  private JButton submitButton;
  private JButton keyFramesubmitButton;
  private JButton newshapesubmitButton;

  //Text input boxes for providing shape attributes with a submit button that will add the shape a
  //first is for dimensions (height and width)
  //second is for the start and end attributes of each motion
  //TODO: Decide if shape motions should be in 9 separate boxes (1 for name and 8 for
  //TODO: pairs of start and end vals) or a long string of space delimited vals
  //TODO: (then call str.split() on long string)
  //TODO: calls addShape in model, sets shapes in view to model.getShapes(), and restarts animation
  private JPanel shapesListPanel;

  /**
   * Constructor for Editor View, taking in a list of shapes, speed, and whether or not the
   * animation will loop. Assigns all further variables to default values.
   */
  public EditorView(ArrayList<Shape> shapes, int speed, boolean willLoop) {
    super(shapes, speed);
    this.isPaused = false;
    this.isReversed = false;
    this.willLoop = willLoop;
    this.areAddShapeOptionsShown = false;
    this.areModifyKeyFrameOptionsShown = false;
    this.areDeleteShapeOptionsShown = false;
    this.playPauseButton = new JButton(
            "Play/Pause",
            new ImageIcon("resources/playpause.png"));
    this.reverseButton = new JButton(
            "Forward/Rewind",
            new ImageIcon("resources/rewind.png"));
    this.loopingButton = new JButton(
            String.format("Looping: %s", willLoop),
            new ImageIcon("resources/looping.png"));

    //TODO: Change Picture
    this.speedUpButton = new JButton(
            "Faster",
            new ImageIcon("resources/looping.png"));

    //TODO: Change Picture
    this.slowDownButton = new JButton(
            "Slower",
            new ImageIcon("resources/looping.png"));

    this.addShapeOptionsButton = new JButton(
            "Add Shape");

    this.deleteShapeOptionsButton = new JButton(
            "Remove Shape");

    this.newShapeName = new JTextField("Shape Name", 10);
    JTextField addedShapeMotionsInput = new JTextField("t1 t2 x1 x2 y1 y2 h1 h2 w1 w2 "
            + "r1 r2 g1 g2 b1 b2",
            "t1 t2 x1 x2 y1 y2 h1 h2 w1 w2 r1 r2 g1 g2 b1 b2".length());
    JTextField addedShapeDimsInput = new JTextField("Input shape's dims as 'H W'", 16);

    this.submitButton = new JButton("Submit");
    this.keyFramesubmitButton = new JButton("Submit");
    this.newshapesubmitButton = new JButton("Submit");

    this.modifyKeyFrameButton = new JButton("Modify KeyFrames");
    this.exitKeyFramesButton = new JButton("Exit");
    this.exitNewShapeButton = new JButton("Exit");
    this.tickValueText = new JTextField("0", 2);
    this.xValueText = new JTextField("0", 2);
    this.yValueText = new JTextField("0", 2);
    this.widthValueText = new JTextField("0", 2);
    this.heightValueText = new JTextField("0", 2);
    this.redValueText = new JTextField("0", 2);
    this.greenValueText = new JTextField("0", 2);
    this.blueValueText = new JTextField("0", 2);

    this.newshapexValueText = new JTextField("0", 2);
    this.newshapeyValueText = new JTextField("0", 2);
    this.newshapewidthValueText = new JTextField("0", 2);
    this.newshapeheightValueText = new JTextField("0", 2);
    this.newshaperedValueText = new JTextField("0", 2);
    this.newshapegreenValueText = new JTextField("0", 2);
    this.newshapeblueValueText = new JTextField("0", 2);



    JLabel newShapeNameLabel = new JLabel("Shape Name:");
    JLabel newShapeTypeLabel = new JLabel("Shape Type:");

    JLabel tickValueLabel = new JLabel("Tick:");
    JLabel xValLabel = new JLabel("x-val:");
    JLabel yValLabel = new JLabel("y-val:");
    JLabel widthLabel = new JLabel("width:");
    JLabel heightLabel = new JLabel("height:");
    JLabel redLabel = new JLabel("red-val:");
    JLabel greenLabel = new JLabel("green-val:");
    JLabel blueLabel = new JLabel("blue-val:");

    JLabel newshapexValLabel = new JLabel("x-val:");
    JLabel newshapeyValLabel = new JLabel("y-val:");
    JLabel newshapewidthLabel = new JLabel("width:");
    JLabel newshapeheightLabel = new JLabel("height:");
    JLabel newshaperedLabel = new JLabel("red-val:");
    JLabel newshapegreenLabel = new JLabel("green-val:");
    JLabel newshapeblueLabel = new JLabel("blue-val:");

    String[] shapeOptions = {"Rectangle", "Ellipse"};
    String[] modifyshapeOptions = {"Rectangle", "Ellipse"};
    JLabel keyFrameOptionsLabel = new JLabel("KeyFrame Operations:");
    String[] keyFrameOptions = {"Add", "Edit", "Remove"};

    JComboBox newShapeType = new JComboBox(shapeOptions);
    newShapeInputPanel = new JPanel();
    add(newShapeInputPanel);
    newShapeInputPanel.add(newShapeNameLabel);
    newShapeInputPanel.add(newShapeName);
    newShapeInputPanel.add(newShapeTypeLabel);
    newShapeInputPanel.add(newShapeType);
    newShapeInputPanel.add(newshapexValLabel);
    newShapeInputPanel.add(newshapexValueText);
    newShapeInputPanel.add(newshapeyValLabel);
    newShapeInputPanel.add(newshapeyValueText);
    newShapeInputPanel.add(newshapewidthLabel);
    newShapeInputPanel.add(newshapewidthValueText);
    newShapeInputPanel.add(newshapeheightLabel);
    newShapeInputPanel.add(newshapeheightValueText);
    newShapeInputPanel.add(newshaperedLabel);
    newShapeInputPanel.add(newshaperedValueText);
    newShapeInputPanel.add(newshapegreenLabel);
    newShapeInputPanel.add(newshapegreenValueText);
    newShapeInputPanel.add(newshapeblueLabel);
    newShapeInputPanel.add(newshapeblueValueText);
    newShapeInputPanel.add(newshapesubmitButton);
    newShapeInputPanel.setVisible(false);
    newshapesubmitButton.setActionCommand("submit");
    newShapeInputPanel.add(exitNewShapeButton);
    exitNewShapeButton.setActionCommand("exitNewShape");

    shapesListPanel = new JPanel();
    DefaultListModel shapesModel = new DefaultListModel();
    shapesList = new JComboBox(getShapeNamesAsArray());
    JScrollPane shapesScroller = new JScrollPane(shapesList);
    shapesList.setName("Shapes");
    shapesListPanel.add(shapesList);
    shapesListPanel.add(shapesScroller);
    add(shapesListPanel);
    shapesListPanel.add(submitButton);
    shapesListPanel.setVisible(false);


    //keyframe Panel
    keyFrameOpType = new JComboBox(keyFrameOptions);
    JComboBox keyShapeType = new JComboBox(modifyshapeOptions);
    modifyKeyFramePanel = new JPanel();
    add(modifyKeyFramePanel);
    modifyshapesList = new JComboBox(getShapeNamesAsArray());
    JScrollPane modifyshapesScroller = new JScrollPane(modifyshapesList);
    modifyshapesList.setName("Shapes");
    modifyKeyFramePanel.add(modifyshapesList);
    modifyKeyFramePanel.add(modifyshapesScroller);
    modifyKeyFramePanel.add(keyFrameOptionsLabel);
    modifyKeyFramePanel.add(keyFrameOpType);
    modifyKeyFramePanel.add(modifyshapesList);
    modifyKeyFramePanel.add(modifyshapesScroller);
    modifyKeyFramePanel.add(tickValueLabel);
    modifyKeyFramePanel.add(tickValueText);
    modifyKeyFramePanel.add(xValLabel);
    modifyKeyFramePanel.add(xValueText);
    modifyKeyFramePanel.add(yValLabel);
    modifyKeyFramePanel.add(yValueText);
    modifyKeyFramePanel.add(widthLabel);
    modifyKeyFramePanel.add(widthValueText);
    modifyKeyFramePanel.add(heightLabel);
    modifyKeyFramePanel.add(heightValueText);
    modifyKeyFramePanel.add(redLabel);
    modifyKeyFramePanel.add(redValueText);
    modifyKeyFramePanel.add(greenLabel);
    modifyKeyFramePanel.add(greenValueText);
    modifyKeyFramePanel.add(blueLabel);
    modifyKeyFramePanel.add(blueValueText);
    modifyKeyFramePanel.add(keyFramesubmitButton);
    keyFramesubmitButton.setActionCommand("submit");
    modifyKeyFramePanel.add(exitKeyFramesButton);
    exitKeyFramesButton.setActionCommand("exitKeyFramePanel");
    modifyKeyFramePanel.setVisible(false);


    add(playPauseButton);
    playPauseButton.setActionCommand("playPause");

    add(reverseButton);
    reverseButton.setActionCommand("reverse");

    add(loopingButton);
    loopingButton.setActionCommand("loop");

    add(speedUpButton);
    speedUpButton.setActionCommand("faster");

    add(slowDownButton);
    slowDownButton.setActionCommand("slower");

    add(addShapeOptionsButton);
    addShapeOptionsButton.setActionCommand("addShapes");

    add(deleteShapeOptionsButton);
    deleteShapeOptionsButton.setActionCommand("deleteShapes");

    add(modifyKeyFrameButton);
    modifyKeyFrameButton.setActionCommand("modifyKeyFrame");

    add(addedShapeDimsInput);
    addedShapeDimsInput.setVisible(false);

    add(addedShapeMotionsInput);
    addedShapeMotionsInput.setVisible(false);
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    if (isPaused) {
      return;
    }
    for (Shape s : shapes) {
      for (IAction a : s.getActions()) {
        if (currTick >= a.getStartTick() && currTick <= a.getEndTick()) {
          executeMove(s, a);
        }
      }
    }
    repaint();
    if (isReversed) {
      if (currTick <= 0) {
        currTick = 0;
      }
      else {
        currTick--;
      }

    } else {
      currTick++;
    }
    if (currTick >= maxTick && willLoop) {

      currTick = 0;
      repaint();

    }
    else if (currTick >= maxTick & !willLoop) {
      currTick = maxTick;
      repaint();
    }
    maxTick = getMaxTick();
    System.out.println(currTick);
  }

  @Override
  public void setActionListener(ActionListener listener) {
    playPauseButton.addActionListener(listener);
    reverseButton.addActionListener(listener);
    loopingButton.addActionListener(listener);
    speedUpButton.addActionListener(listener);
    slowDownButton.addActionListener(listener);
    addShapeOptionsButton.addActionListener(listener);
    deleteShapeOptionsButton.addActionListener(listener);
    modifyKeyFrameButton.addActionListener(listener);
    exitKeyFramesButton.addActionListener(listener);
    exitNewShapeButton.addActionListener(listener);
    submitButton.addActionListener(listener);
    keyFramesubmitButton.addActionListener(listener);
    newshapesubmitButton.addActionListener(listener);
  }

  @Override
  public void flipPause() {
    isPaused = !isPaused;
  }

  @Override
  public void flipReverse() {
    isReversed = !isReversed;
    if (isReversed) {
      reverseButton.setText("Rewinding");
    } else {
      reverseButton.setText("Forward");
    }
  }

  @Override
  public void flipLooping() {
    willLoop = !willLoop;
    loopingButton.setText(String.format("Looping: %s", willLoop));
  }

  @Override
  public void speedUp() {
    if (t.getDelay() - 50 <= 20) {
      t.setDelay(20);
    }
    else {
      t.setDelay(t.getDelay() - 50);
    }
  }

  @Override
  public void slowDown() {
    if (t.getDelay() + 50 >= 1000) {
      t.setDelay(1000);
    }
    else {
      t.setDelay(t.getDelay() + 50);
    }
  }

  public void toggleAddShapeOptions() {
    areAddShapeOptionsShown = !areAddShapeOptionsShown;
    newShapeInputPanel.setVisible(areAddShapeOptionsShown);
  }

  public void toggleModifyKeyFrameOptions() {
    areModifyKeyFrameOptionsShown = !areModifyKeyFrameOptionsShown;
    modifyKeyFramePanel.setVisible(areModifyKeyFrameOptionsShown);
  }

  public ArrayList<String> getShapeToAdd() {
    ArrayList<String> temp = new ArrayList<>();
    if (newShapeName.getText().length() > 0) {
      temp.add(newShapeName.getText());
    }
    else {
      temp.add(null);
    }
    temp.add(newShapeName.getSelectedText());
    return temp;
  }

  @Override
  public void toggleDeleteShapeOptions() {
    areDeleteShapeOptionsShown = !areDeleteShapeOptionsShown;
    shapesListPanel.setVisible(areDeleteShapeOptionsShown);
  }

  public String getSelectedShape() {
    return (String) shapesList.getSelectedItem();
  }

  public String getkeyFrameSelectedShape() {
    return (String) modifyshapesList.getSelectedItem();
  }

  public String getSelectedKeyFrameOp() {
    return (String) keyFrameOpType.getSelectedItem();
  }

  /**
   * Method gathering all of the user-defined values from the shape editor panel on the view and
   * compiling all values into one array, to be utilized easier in other methods. Used when user
   * is modifying an existing shape.
   * @return array representation of the new state of the shape
   */
  public int[] getNewStateValues() {
    int tick = parseStateVal(tickValueText.getText());
    int xval = parseStateVal(xValueText.getText());
    int yval = parseStateVal(yValueText.getText());
    int width = parseStateVal(widthValueText.getText());
    int height = parseStateVal(heightValueText.getText());
    int redval = parseStateVal(redValueText.getText());
    int greenval = parseStateVal(greenValueText.getText());
    int blueval = parseStateVal(blueValueText.getText());
    int[] newState = {tick,xval,yval,width,height,redval,greenval,blueval};
    return newState;
  }

  /**
   * Method gathering all of the user-defined values from the shape creator panel on the view and
   * compiling all values into one array, to be utilized easier in other methods. Used when user is
   * defining a new shape.
   * @return array representation of the state of the new shape
   */
  public int[] getShapeState() {
    int xval = parseStateVal(newshapexValueText.getText());
    int yval = parseStateVal(newshapeyValueText.getText());
    int width = parseStateVal(newshapewidthValueText.getText());
    int height = parseStateVal(newshapeheightValueText.getText());
    int redval = parseStateVal(newshaperedValueText.getText());
    int greenval = parseStateVal(newshapegreenValueText.getText());
    int blueval = parseStateVal(newshapeblueValueText.getText());
    int[] shapeState = {xval,yval,width,height,redval,greenval,blueval};
    return shapeState;
  }

  private int parseStateVal(String val) {
    int parsedVal;
    try {
      parsedVal = Integer.parseInt(val);
    }
    catch (NumberFormatException n) {
      throw new NumberFormatException("Invalid State Values");
    }
    return parsedVal;
  }

  public boolean isPaused() {
    return isPaused;
  }

  public boolean isReversed() {
    return isReversed;
  }

  public boolean isWillLoop() {
    return willLoop;
  }

  @Override
  public void addActionListener(ActionListener excellenceController) {
    t.addActionListener(excellenceController);
    playPauseButton.addActionListener(excellenceController);
    reverseButton.addActionListener(excellenceController);
    loopingButton.addActionListener(excellenceController);
    speedUpButton.addActionListener(excellenceController);
    slowDownButton.addActionListener(excellenceController);
    addShapeOptionsButton.addActionListener(excellenceController);
    deleteShapeOptionsButton.addActionListener(excellenceController);
    modifyKeyFrameButton.addActionListener(excellenceController);
    exitKeyFramesButton.addActionListener(excellenceController);
    exitNewShapeButton.addActionListener(excellenceController);
    submitButton.addActionListener(excellenceController);
    keyFramesubmitButton.addActionListener(excellenceController);
    newshapesubmitButton.addActionListener(excellenceController);
  }


  /**
   * Method used to provide the user with options of shapes to modify. Returns an array of strings
   * representing all the shapes in the animation.
   * @return array of strings of shape names
   */
  public String[] getShapeNamesAsArray() {
    ArrayList<String> tempNames = new ArrayList<>();

    for (Shape s : shapes) {
      tempNames.add(s.getShapeName());
      System.out.println(s.getShapeName());
    }
    Object[] oArr = tempNames.toArray();
    return Arrays.copyOf(oArr, oArr.length, String[].class);
  }
}
