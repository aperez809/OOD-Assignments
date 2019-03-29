package cs3500.animator.view;

import cs3500.animator.controller.ExcellenceController;
import cs3500.animator.model.IAction;
import cs3500.animator.model.Shape;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;
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

  //Clickable Shape types list
  //TODO: Capture selected element in a string after clicking submit button
  private JList<String> shapeTypesList;
  private JList<String> allShapesList;
  private JButton submitShapeSelection;

  //Text input boxes for providing shape attributes with a submit button that will add the shape a
  //first is for dimensions (height and width)
  //second is for the start and end attributes of each motion
  //TODO: Decide if shape motions should be in 9 separate boxes (1 for name and 8 for
  //TODO: pairs of start and end vals) or a long string of space delimited vals
  //TODO: (then call str.split() on long string)
  private JTextField addedShapeDimsInput;
  private JTextField addedShapeMotionsInput;
  private JButton submitShapeAttrInputs; //calls addShape in model, sets shapes in view to model.getShapes(), and restarts animation


  //TODO:


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

    this.shapeTypesList = new JList(new String[] {"Rect", "Oval"});
    this.allShapesList = new JList(this.getShapeNamesAsArray());

    this.addedShapeMotionsInput = new JTextField(16);
    this.addedShapeDimsInput = new JTextField("Input shape's dims as 'H W'", 16);



    this.add(playPauseButton);
    playPauseButton.setActionCommand("playPause");

    this.add(reverseButton);
    reverseButton.setActionCommand("reverse");

    this.add(loopingButton);
    loopingButton.setActionCommand("loop");

    this.add(speedUpButton);
    speedUpButton.setActionCommand("faster");

    this.add(slowDownButton);
    slowDownButton.setActionCommand("slower");

    this.add(addShapeOptionsButton);
    addShapeOptionsButton.setActionCommand("addShapes");

    this.add(deleteShapeOptionsButton);
    deleteShapeOptionsButton.setActionCommand("deleteShapes");

    this.add(shapeTypesList);
    shapeTypesList.setVisible(false);

    this.add(addedShapeDimsInput);
    addedShapeDimsInput.setVisible(false);

    this.add(addedShapeMotionsInput);
    addedShapeMotionsInput.setVisible(false);
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    if (isPaused) {
        return;
      }
      for (Shape s : this.shapes) {
        for (IAction a : s.getActions()) {
          if (currTick >= a.getStartTick() && currTick <= a.getEndTick()) {
            executeMove(s, a);
          }
        }
      }
      this.repaint();
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
    allShapesList.addListSelectionListener(listener);
    shapeTypesList.addListSelectionListener(listener);
  }

  @Override
  public void flipPause() {
    this.isPaused = !this.isPaused;
  }

  @Override
  public void flipReverse() {
    this.isReversed = !this.isReversed;
    if (isReversed) {
      reverseButton.setText("Rewinding");
    } else {
      reverseButton.setText("Forward");
    }
  }

  @Override
  public void flipLooping() {
    this.willLoop = !this.willLoop;
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
    shapeTypesList.setVisible(areAddShapeOptionsShown);
    addedShapeDimsInput.setVisible(areAddShapeOptionsShown);
    addedShapeMotionsInput.setVisible(areAddShapeOptionsShown);
  }

  @Override
  public void toggleDeleteShapeOptions() {
    areDeleteShapeOptionsShown = !areDeleteShapeOptionsShown;
    allShapesList.setVisible(areDeleteShapeOptionsShown);
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
    this.t.addActionListener(excellenceController);
    playPauseButton.addActionListener(excellenceController);
    reverseButton.addActionListener(excellenceController);
    loopingButton.addActionListener(excellenceController);
    speedUpButton.addActionListener(excellenceController);
    slowDownButton.addActionListener(excellenceController);
    addShapeOptionsButton.addActionListener(excellenceController);
    deleteShapeOptionsButton.addActionListener(excellenceController);

    allShapesList.addListSelectionListener(excellenceController);
    shapeTypesList.addListSelectionListener(excellenceController);
  }

  public String getSelectedItem() {
    return shapeTypesList.getSelectedValue();
  }

  public String[] getShapeNamesAsArray() {
    ArrayList<String> tempNames = new ArrayList<String>();

    for (Shape s : this.shapes) {
      tempNames.add(s.getShapeName());
      System.out.println(s.getShapeName());
    }
    Object[] oArr = tempNames.toArray();
    return Arrays.copyOf(oArr, oArr.length, String[].class);
  }
}
