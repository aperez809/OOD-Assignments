package cs3500.animator.provider.model;

import cs3500.animator.model.Action;
import cs3500.animator.model.IAction;


/**
 * Adapter class for using our provider's Transition objects in harmony with our
 * Action objects.
 */
public class TransitionToActionAdapter {
  private Transition t;

  /**
   * Creates a TransitionToActionAdapter object using an instance of our provider's
   * Transition class.
   *
   * @param t an instance of our provider's Transition class
   */
  public TransitionToActionAdapter(Transition t) {
    this.t = t;
  }


  /**
   * Takes every field of the Transition class and uses them to construct a new instance of an
   * Action object so that we can seamlessly integrate our model with theirs.
   *
   * @return an IAction object
   */
  public IAction getTransAsAction() {
    return new Action(t.beginTime, t.endTime, t.x1, t.x2, t.y1,t.y2,t.h1,t.h2, t.w1, t.w2, t.r1,
            t.r2,t.g1,t.g2,t.b1,t.b2);
  }
}
