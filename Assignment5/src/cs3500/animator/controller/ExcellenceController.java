package cs3500.animator.controller;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.view.IView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * A pseudo-controller for our EasyAnimator that displays the correct type of animation based
 * on text inputs.
 */
public final class ExcellenceController implements ActionListener {
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

        case "addShape":

          break;

        case "deleteShape":
          break;

        case "addKey":
          break;

        case "deleteKey":
          break;

        case "editKey":
          break;

        default:
          break;
      }
    }
  }

  public void makeVisible() {
    this.view.makeVisible();
  }
}
