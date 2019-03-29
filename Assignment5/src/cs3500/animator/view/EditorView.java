package cs3500.animator.view;

import cs3500.animator.controller.ExcellenceController;
import cs3500.animator.model.IAction;
import cs3500.animator.model.Shape;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class EditorView extends AnimationPanelView implements ActionListener {

  //Utility booleans that are swapped every time a button is pressed
  //i.e. true to false and false to true
  private boolean isPaused;
  private boolean isReversed;
  private boolean willLoop;
  private boolean areAddShapeOptionsShown;
  private boolean areDeleteShapeOptionsShown;

  private DefaultListModel shapesModel;
  //private JList shapesList;
  private JComboBox shapesList;

  private JScrollPane shapesScroller;



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

  private JTextField newShapeName;
  private JComboBox newShapeType;
  private JPanel newShapeInputPanel;

  //Clickable Shape types list
  //TODO: Capture selected element in a string after clicking submit button
  private JButton submitButton;

  //Text input boxes for providing shape attributes with a submit button that will add the shape a
  //first is for dimensions (height and width)
  //second is for the start and end attributes of each motion
  //TODO: Decide if shape motions should be in 9 separate boxes (1 for name and 8 for
  //TODO: pairs of start and end vals) or a long string of space delimited vals
  //TODO: (then call str.split() on long string)
  //TODO: calls addShape in model, sets shapes in view to model.getShapes(), and restarts animation
  private JTextField addedShapeDimsInput;
  private JTextField addedShapeMotionsInput;
  private JPanel shapesListPanel;

  public EditorView(ArrayList<Shape> shapes, int speed, boolean willLoop) {
    super(shapes, speed);
    this.isPaused = false;
    this.isReversed = false;
    this.willLoop = willLoop;
    this.areAddShapeOptionsShown = false;
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
    this.addedShapeMotionsInput = new JTextField("t1 t2 x1 x2 y1 y2 h1 h2 w1 w2 " +
            "r1 r2 g1 g2 b1 b2",
            "t1 t2 x1 x2 y1 y2 h1 h2 w1 w2 r1 r2 g1 g2 b1 b2".length());
    this.addedShapeDimsInput = new JTextField("Input shape's dims as 'H W'", 16);

    this.submitButton = new JButton("Submit");


    JLabel newShapeNameLabel = new JLabel("Shape Name:");
    JLabel newShapeTypeLabel = new JLabel("Shape Type:");
    String[] shapeOptions = {"Rectangle", "Ellipse"};

    newShapeType = new JComboBox(shapeOptions);
    newShapeInputPanel = new JPanel();
    add(newShapeInputPanel);
    newShapeInputPanel.add(newShapeNameLabel);
    newShapeInputPanel.add(newShapeName);
    newShapeInputPanel.add(newShapeTypeLabel);
    newShapeInputPanel.add(newShapeType);
    newShapeInputPanel.add(submitButton);
    newShapeInputPanel.setVisible(false);
    submitButton.setActionCommand("submit");

    shapesListPanel = new JPanel();
    shapesModel = new DefaultListModel();
    shapesList = new JComboBox(getShapeNamesAsArray());
    shapesScroller = new JScrollPane(shapesList);
    shapesList.setName("Shapes");
    shapesListPanel.add(shapesList);
    shapesListPanel.add(shapesScroller);
    add(shapesListPanel);
    shapesListPanel.add(submitButton);
    shapesListPanel.setVisible(false);



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
  public void setActionListener(ExcellenceController listener) {
    playPauseButton.addActionListener(listener);
    reverseButton.addActionListener(listener);
    loopingButton.addActionListener(listener);
    speedUpButton.addActionListener(listener);
    slowDownButton.addActionListener(listener);
    addShapeOptionsButton.addActionListener(listener);
    deleteShapeOptionsButton.addActionListener(listener);
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
    if (t.getDelay() + 50 >= 1000){
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

  public String getShapeToDelete() {
    return (String) shapesList.getSelectedItem();
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
  public void addActionListener(ExcellenceController excellenceController) {
    t.addActionListener(excellenceController);
    playPauseButton.addActionListener(excellenceController);
    reverseButton.addActionListener(excellenceController);
    loopingButton.addActionListener(excellenceController);
    speedUpButton.addActionListener(excellenceController);
    slowDownButton.addActionListener(excellenceController);
    addShapeOptionsButton.addActionListener(excellenceController);
    deleteShapeOptionsButton.addActionListener(excellenceController);
  }


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
