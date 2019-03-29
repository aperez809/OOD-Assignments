package cs3500.animator.view;

import cs3500.animator.controller.ExcellenceController;
import cs3500.animator.model.IAction;
import cs3500.animator.model.Shape;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EditorView extends AnimationPanelView implements ActionListener {
  private boolean isPaused;
  private boolean isReversed;
  private boolean willLoop;
  private int maxTick;
  private JButton playPauseButton;
  private JButton reverseButton;
  private JButton loopingButton;
  private JButton speedUpButton;
  private JButton slowDownButton;


  public EditorView(ArrayList<Shape> shapes, int speed, boolean willLoop) {
    super(shapes, speed);
    this.isPaused = false;
    this.isReversed = false;
    this.willLoop = willLoop;
    this.playPauseButton = new JButton(
            "Play/Pause",
            new ImageIcon("resources/playpause.png"));
    this.reverseButton = new JButton(
            "Rewind",
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
  }

  @Override
  public void flipPause() {
    this.isPaused = !this.isPaused;
  }

  @Override
  public void flipReverse() {
    this.isReversed = !this.isReversed;
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

  }
}
