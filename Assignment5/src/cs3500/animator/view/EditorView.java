package cs3500.animator.view;

import cs3500.animator.model.IAction;
import cs3500.animator.model.Shape;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class EditorView extends AnimationPanelView {
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
    this.maxTick = this.getMaxTick();
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
    playPauseButton.addActionListener(this);
    playPauseButton.setActionCommand("playPause");

    this.add(reverseButton);
    reverseButton.addActionListener(this);
    reverseButton.setActionCommand("reverse");

    this.add(loopingButton);
    loopingButton.addActionListener(this);
    loopingButton.setActionCommand("loop");

    this.add(speedUpButton);
    speedUpButton.addActionListener(this);
    speedUpButton.setActionCommand("faster");

    this.add(slowDownButton);
    slowDownButton.addActionListener(this);
    slowDownButton.setActionCommand("slower");
  }


  public int getMaxTick() {
    int currMax = 0;

    for (Shape s: this.shapes) {
      for (IAction a: s.getActions()) {
        if (a.getEndTick() > currMax) {
          currMax = a.getEndTick();
        }
      }
    }
    return currMax;
  }



  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand() != null) {
      if (e.getActionCommand().equals("playPause")) {
        isPaused = !isPaused;
      } else if (e.getActionCommand().equals("reverse")) {
        isReversed = !isReversed;
      } else if (e.getActionCommand().equals("loop")) {
        willLoop = !willLoop;
        loopingButton.setText(String.format("Looping: %s", willLoop));
      }
      else if (e.getActionCommand().equals("faster")) {
        if (t.getDelay() - 50 <= 50) {
          t.setDelay(50);
        }
        else {
          t.setDelay(t.getDelay() - 50);
        }
      }
      else if (e.getActionCommand().equals("slower")) {
        if (t.getDelay() + 50 >= 1000){
          t.setDelay(1000);
        }
        else {
          t.setDelay(t.getDelay() + 50);
        }
      }
    }
    else {
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
      System.out.println(currTick);
    }
  }
}
