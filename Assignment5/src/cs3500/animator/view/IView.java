package cs3500.animator.view;

import cs3500.animator.controller.ExcellenceController;
import cs3500.animator.model.IAction;
import cs3500.animator.model.Shape;

import java.io.IOException;
import java.util.function.Consumer;

/**
 * Interface implemented by all view types (Text, SVG, and Visual). Maintains a list of common
 * operations between the different representations and documents them.
 */
public interface IView {
  /**
   * Make the view visible. This is usually called
   * after the view is constructed.
   */
  void makeVisible();

  /**
   * Provide the view with a callback option to
   * process a command. Not yet implemented.
   *
   * @param callback object
   */
  void setCommandCallback(Consumer<String> callback);

  /**
   * Transmit an error message to view, in case
   * the command could not be processed correctly. Comes in the form of either a JOptionPane
   * or simple text.
   *
   * @param error The error message to be transmitted
   */
  void showErrorMessage(String error);

  /**
   * Signal the view to draw itself again.
   */
  void refresh();

  /**
   * Writes the output of the program to a file. File name, location, etc. are handled externally
   * and collected by the method.
   *
   * @throws IOException in case there is an issue with writing to the file
   *          (not found, corrupt, etc)
   */
  void createAnimOutput() throws IOException;

  /**
   * Gets the output of the program for the SVG and Text representations.
   *
   * @return Appendable that changes based on both the command line arguments and the
   *          type of representation
   */
  Appendable getOutput();

  /**
   * adds a panel of type AnimationPanelView (extended from JPanel) to the JFrame. Only used in
   * by the visual representations.
   *
   * @param panel AnimationPanelView to add to the JFrame
   */
  void add(AnimationPanelView panel);

  void setActionListener(ExcellenceController listener);

  void flipPause();
  void flipReverse();
  void flipLooping();
  void speedUp();
  void slowDown();
  boolean isPaused();
  boolean isReversed();
  boolean isWillLoop();

  int getCurrTick();

  void executeMove(Shape s, IAction a);

  void repaint();

  void setCurrTick(int i);

  int getMaxTick();

  void setMaxTick(int newMax);

  void startTimer();

  IView getPanel();

  void addActionListener(ExcellenceController excellenceController);
}
