package cs3500.animator.controller;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.view.IView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * A pseudo-controller for our EasyAnimator that displays the correct type of animation based
 * on text inputs.
 */
public final class ExcellenceController implements ActionListener, ListSelectionListener {
  private AnimationModel model;
  private IView view;
  private IView panel;

  public ExcellenceController(AnimationModel model, IView view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Neither model nor view can be null.");
    }
    this.model = model;
    this.view = view;
    this.view.addActionListener(this);
    try {
      this.panel = this.view.getPanel();
    }
    catch (Exception e) {
      //pass
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand() != null) {
      switch (e.getActionCommand()) {

        case "playPause":
          panel.flipPause();
          break;

        case "reverse":
          panel.flipReverse();
          break;

        case "loop":
          panel.flipLooping();
          break;

        case "faster":
          panel.speedUp();
          break;

        case "slower":
          panel.slowDown();
          break;

        case "addShapes":
          panel.flipPause();
          panel.toggleAddShapeOptions();
          if (e.getActionCommand().equals("submit")) {
            panel.getShapeToAdd();
            //TODO: Add logic to listen for shape-related inputs
          }
          break;

        case "deleteShapes":
          panel.flipPause();
          panel.toggleDeleteShapeOptions();
          if (e.getActionCommand().equals("submit")) {
            //panel.getShapeToDelete();
            //TODO: Add logic to listen for shape-related inputs
          }

          //TODO: Add logic to listen for shape choice to delete
          break;

        case "modifyKeyFrame":
          panel.flipPause();
          panel.toggleModifyKeyFrameOptions();
          break;

        case "exitKeyFramePanel":
          System.out.println("Exit Key Frame Panel");
          panel.flipPause();
          panel.toggleModifyKeyFrameOptions();
          break;

        case "addKeyFrame":
          System.out.println("addKeyFrame");
          //TODO: Add logic to listen for shape name and tick value to insert keyframe at
          try {
            model.insertKeyFrame("",0);
          }
          catch (Exception addException) {
            System.out.println(addException);
          }
          break;

        case "editKeyFrame":
          System.out.println("editKeyFrame");
          //TODO: Add logic to listen for shape name and new state for shape
          try {
            model.editKeyFrame("",0, 0,0,0,0,0,0,0);
          }
          catch (Exception editException) {
            System.out.println(editException);
          }
          break;

        case "removeKeyFrame":
          System.out.println("removeKeyFrame");
          //TODO: Add logic to listen for shape name and tick to delete keyframe at
          try {
            model.removeKeyFrame("",0);
          }
          catch (Exception removeException) {
            System.out.println(removeException);
          }
          break;

        default:
          break;
      }
    }
  }

  public void makeVisible() {
    this.view.makeVisible();
  }

  @Override
  public void valueChanged(ListSelectionEvent e) {
    JList changedList = (JList) e.getSource();
  }
}
