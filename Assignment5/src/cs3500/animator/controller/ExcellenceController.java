package cs3500.animator.controller;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.view.IView;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * A pseudo-controller for our EasyAnimator that displays the correct type of animation based
 * on text inputs.
 */
public final class ExcellenceController implements IAnimControl, ActionListener,
        ListSelectionListener {
  private AnimationModel model;
  private IView view;
  private IView panel;
  private SubmitFrame submitType;

  /**
   * Constructor for Excellence Controller, taking in an Animation and View and first checking
   * that neither are null.
   */
  public ExcellenceController(AnimationModel model, IView view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Neither model nor view can be null.");
    }
    this.model = model;
    this.view = view;
    this.view.addActionListener(this);
    this.submitType = SubmitFrame.NONE;
    try {
      this.panel = this.view.getPanel();
    }
    catch (Exception e) {
      //pass
    }
  }

  private enum SubmitFrame {
    ADDSHAPE, REMOVESHAPE, MODIFYKEYFRAME, NONE
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String action = e.getActionCommand();
    if (action != null) {
      switch (action) {
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
          submitType = SubmitFrame.ADDSHAPE;
          break;

        case "deleteShapes":
          panel.flipPause();
          panel.toggleDeleteShapeOptions();
          submitType = SubmitFrame.REMOVESHAPE;
          break;

        case "modifyKeyFrame":
          panel.flipPause();
          panel.toggleModifyKeyFrameOptions();
          submitType = SubmitFrame.MODIFYKEYFRAME;
          break;

        case "exitKeyFramePanel":
          panel.flipPause();
          panel.toggleModifyKeyFrameOptions();
          submitType = SubmitFrame.NONE;
          break;

        case "exitNewShape":
          panel.flipPause();
          panel.toggleAddShapeOptions();
          submitType = SubmitFrame.NONE;
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

        case "submit":
          if (submitType == SubmitFrame.ADDSHAPE) {
            try {
              String shapeName = panel.getShapeToAdd().get(0);
            }
            catch (Exception except) {
              throw new IllegalArgumentException(except.toString());
            }
            int[] shapeState = panel.getShapeState();
            //TODO: Add logic to listen for shape-related inputs
          }
          else if (submitType == SubmitFrame.REMOVESHAPE) {
            String removeshapeName = panel.getSelectedShape();
            model.removeShape(removeshapeName);
          }
          else if (submitType == SubmitFrame.MODIFYKEYFRAME) {
            String editshapeName = panel.getkeyFrameSelectedShape();
            String keyFrameOp = panel.getSelectedKeyFrameOp();
            this.keyFrameHandler(editshapeName,keyFrameOp);
          }
          break;

        default:
          break;
      }
    }
  }

  /**
   * Helper method for handling modification of keyframes in the edit view. Given the shape name
   * and desired keyframe operation to be performed on said shape and calls the respective
   * model methods to make the desired modifications.
   */
  public void keyFrameHandler(String shapeName, String operation) {
    int[] stateVals = panel.getNewStateValues();
    switch (operation) {
      case "Remove":
        model.removeKeyFrame(shapeName,stateVals[0]);
        break;
      case "Edit":
        model.editKeyFrame(shapeName,stateVals[0],stateVals[1],
                stateVals[2],stateVals[3],stateVals[4],stateVals[5],stateVals[6],stateVals[7]);
        break;
      case "Add":
        model.insertKeyFrame(shapeName,stateVals[0]);
        break;
      default:
        break;
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
