package cs3500.animator.controller;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.model.util.AnimationReader;
import cs3500.animator.view.AnimationGraphicsView;
import cs3500.animator.view.AnimationPanelView;
import cs3500.animator.view.EditorView;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGRepresentation;
import cs3500.animator.view.TextRepresentation;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * Excellence class containing the main method which the user may use to start the application,
 * allowing user to decide which type of view to utilize by passing in command line arguments.
 */
public class Excellence {

  /**
   * Main class used to run the entire application. Allows for user selection of view type and
   * instantiates the model and controller, so all three may be used in the program's execution.
   */
  public static void main(String[] args) throws IOException {
    AnimationModel model;
    IView view = null;
    Readable file;
    Appendable output;


    HashMap<String, String> cla = argsToHM(args);

    if (checkValidArgs(cla)) {
      showError("Invalid command line args");
      return;
    }

    try {
      file = new FileReader(setFile(cla));
    } catch (FileNotFoundException e) {
      showError("File not found");
      return;
    }


    model = AnimationReader.parseFile(file,
            new AnimationModelImpl.Builder(
                    new AnimationModelImpl()));


    switch (cla.get("-view")) {
      case "text":
        output = System.out;
        view = new TextRepresentation(
                model.getShapes(),
                model.getMaxX(),
                model.getMaxY(),
                model.getWidth(),
                model.getHeight());
        view.createAnimOutput();
        output.append(view.getOutput().toString());
        return;

      case "svg":
        try {
          File f = new File(cla.get("-out"));
          FileWriter fw = new FileWriter(f);
          BufferedWriter bw = new BufferedWriter(fw);
          view = new SVGRepresentation(model.getShapes(),
                  model.getWidth(),
                  model.getHeight(),
                  Integer.valueOf(cla.get("-speed")));
          view.createAnimOutput();
          System.out.println(view.getOutput().toString());
          bw.append(view.getOutput().toString());
          bw.close();
          return;
        }
        catch (IOException e) {
          showError("File unable to be written");
        }
        break;

      case "visual":
        IView visPanel = new AnimationPanelView(model.getShapes(),
                Integer.valueOf(cla.get("-speed")));

        view = new AnimationGraphicsView(visPanel, model.getMaxX(), model.getMaxY(),
                model.getWidth(),
                model.getHeight());
        break;

      case "edit":
        AnimationPanelView editPanel = new EditorView(model.getShapes(),
                Integer.valueOf(cla.get("-speed")), true);
        view = new AnimationGraphicsView(editPanel, model.getMaxX(), model.getMaxY(),
                model.getWidth(),
                model.getHeight());
        break;
      default:
        showError("unrecognized/incorrect animation type specified");
        break;
    }

    ExcellenceController controller = new ExcellenceController(model, view);
    controller.makeVisible();
  }

  private static void showError(String error) {
    JOptionPane op = new JOptionPane(JOptionPane.ERROR_MESSAGE);
    JDialog message = op.createDialog(error);
    message.setAlwaysOnTop(true);
    message.setVisible(true);
  }

  private static boolean checkValidArgs(HashMap<String, String> cla) {
    if (!cla.containsKey("-in") || !cla.containsKey("-view")) {
      return true;
    }
    else if (!cla.containsKey("-out")) {
      cla.put("-out", "System.out");
    }

    else if (!cla.containsKey("-speed")) {
      cla.put("-out", "1");
    }
    return false;
  }

  private static String setFile(HashMap<String, String> cla) {
    if (cla.containsKey("-in")) {
      return cla.get("-in");
    }
    else {
      throw new IllegalArgumentException("Arguments passed to command line must include -in flag");
    }
  }

  private static HashMap<String, String> argsToHM(String[] args) {
    HashMap<String, String> temp = new HashMap<>();
    int startVal;
    if (args.length % 2 == 0) {
      startVal = 0;
    }
    else {
      startVal = 1;
    }

    for (int i = startVal; i < args.length; i++) {
      temp.put(args[i], args[i + 1]);
      i++;
    }
    return temp;
  }
}
