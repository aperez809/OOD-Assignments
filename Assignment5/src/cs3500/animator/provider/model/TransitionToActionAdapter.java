package cs3500.animator.provider.model;

import cs3500.animator.model.Action;
import cs3500.animator.model.IAction;

public class TransitionToActionAdapter {
  private Transition t;

  public TransitionToActionAdapter(Transition t) {
    this.t = t;
  }

  public IAction getTransAsAction() {
    return new Action(t.beginTime, t.endTime, t.x1, t.x2, t.y1,t.y2,t.h1,t.h2, t.w1, t.w2, t.r1,
            t.r2,t.g1,t.g2,t.b1,t.b2);
  }
}
