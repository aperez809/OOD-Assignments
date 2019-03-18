package cs3500.animator.view;

import java.util.function.Consumer;

public interface IView {
  /**
   * Make the cs3500.animator.view visible. This is usually called
   * after the cs3500.animator.view is constructed
   */
  void makeVisible();

  /**
   * Provide the cs3500.animator.view with a callback option to
   * process a command.
   *
   * @param callback object
   */
  void setCommandCallback(Consumer<String> callback);

  /**
   * Transmit an error message to the cs3500.animator.view, in case
   * the command could not be processed correctly
   *
   * @param error
   */
  void showErrorMessage(String error);

  /**
   * Signal the cs3500.animator.view to draw itself
   */
  void refresh();
}
