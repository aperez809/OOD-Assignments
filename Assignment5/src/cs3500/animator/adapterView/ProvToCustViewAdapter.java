package cs3500.animator.adapterView;

import cs3500.animator.model.IAction;
import cs3500.animator.model.Shape;
import cs3500.animator.provider.view.AnimationView;
import cs3500.animator.view.AnimationPanelView;
import cs3500.animator.view.IView;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Consumer;

public class ProvToCustViewAdapter implements IView {

  private cs3500.animator.provider.view.AnimationView prov;
  private int currTick;
  private ArrayList<Shape> shapes;
  private JPanel panel;
  private Timer t;
  private int speed;

  public ProvToCustViewAdapter(AnimationView prov, ArrayList<Shape> shapes, JPanel panel) {
    this.prov = prov;
    this.currTick = currTick;
    this.shapes = shapes;
    this.panel = panel;
    this.t = new Timer(this.speed, null);
  }

  /*
render: yurt
setBounds:
setListener: yurt
getSpeed:
setSpeed
setOutputFile
 */



  @Override
  public void makeVisible() {
    throw new UnsupportedOperationException("Not used for this view");
  }

  @Override
  public void setCommandCallback(Consumer<String> callback) {
    throw new UnsupportedOperationException("Not used for this view");
  }

  @Override
  public void showErrorMessage(String error) {
    throw new UnsupportedOperationException("Not used for this view");
  }

  @Override
  public void refresh() {
    this.prov.render(currTick, shapes);
  }

  @Override
  public void createAnimOutput() throws IOException {
    throw new UnsupportedOperationException("Not used for this view");

  }

  @Override
  public Appendable getOutput() {
    return null;
  }

  @Override
  public void add(AnimationPanelView panel) {
    throw new UnsupportedOperationException("Not used for this view");

  }

  @Override
  public void setActionListener(ActionListener listener) {
    throw new UnsupportedOperationException("Not used for this view");

  }

  @Override
  public void flipPause() {
    throw new UnsupportedOperationException("Not used for this view");

  }

  @Override
  public void flipReverse() {
    throw new UnsupportedOperationException("Not used for this view");

  }

  @Override
  public void flipLooping() {
    throw new UnsupportedOperationException("Not used for this view");

  }

  @Override
  public void speedUp() {
    this.prov.setSpeed(t.getDelay() - 50);
  }

  @Override
  public void slowDown() {
    this.prov.setSpeed(t.getDelay() + 50);

  }

  @Override
  public boolean isPaused() {
    return false;
  }

  @Override
  public boolean isReversed() {
    return false;
  }

  @Override
  public boolean isWillLoop() {
    return false;
  }

  @Override
  public int getCurrTick() {
    return this.currTick;
  }

  @Override
  public void executeMove(Shape s, IAction a) {
    throw new UnsupportedOperationException("Not used for this view");
  }

  @Override
  public void repaint() {
    this.panel.repaint();
  }

  @Override
  public void setCurrTick(int i) {
    this.currTick = i;

  }

  @Override
  public int getMaxTick() {
    return 0;
  }

  @Override
  public void setMaxTick(int newMax) {

  }

  @Override
  public void startTimer() {

  }

  @Override
  public IView getPanel() {
    return null;
  }

  @Override
  public void addActionListener(ActionListener excellenceController) {
    this.prov.setListener(excellenceController);
  }

  @Override
  public void toggleAddShapeOptions() {
    throw new UnsupportedOperationException("Not used for this view");

  }

  @Override
  public void toggleModifyKeyFrameOptions() {
    throw new UnsupportedOperationException("Not used for this view");

  }

  @Override
  public String getSelectedItem() {
    return null;
  }

  @Override
  public void toggleDeleteShapeOptions() {
    throw new UnsupportedOperationException("Not used for this view");

  }

  @Override
  public ArrayList<String> getShapeToAdd() {
    return null;
  }

  @Override
  public String getSelectedShape() {
    return null;
  }

  @Override
  public String getSelectedKeyFrameOp() {
    return null;
  }

  @Override
  public int[] getNewStateValues() {
    return new int[0];
  }

  @Override
  public int[] getShapeState() {
    return new int[0];
  }

  @Override
  public String getkeyFrameSelectedShape() {
    return null;
  }


}
