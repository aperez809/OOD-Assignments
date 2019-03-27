package cs3500.animator.view;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.model.util.AnimationReader;
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
 * A pseudo-controller for our EasyAnimator that displays the correct type of animation based
 * on text inputs.
 */
public final class Excellence {

  /**
   * Takes in command line arguments, parses them, and displays the correct type of
   * animation with the correct attributes. If it fails, it displays a JOptionPane error message.
   */
  public static void main(String[] args) throws IOException {
    AnimationModel model;
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
        IView view = new TextRepresentation(
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
          IView svg = new SVGRepresentation(model.getShapes(),
                  model.getWidth(),
                  model.getHeight(),
                  Integer.valueOf(cla.get("-speed")));
          svg.createAnimOutput();
          System.out.println(svg.getOutput().toString());
          bw.append(svg.getOutput().toString());
          bw.close();
          return;
        }
        catch (IOException e) {
          showError("File unable to be written");
        }
        break;

      case "visual":
        //AnimationPanelView panel = new AnimationPanelView(model.getShapes(),
                 //Integer.valueOf(cla.get("-speed")));
        AnimationPanelView panel = new EditorView(model.getShapes(),
                Integer.valueOf(cla.get("-speed")), true);
        IView gui = new AnimationGraphicsView(panel, model.getMaxX(), model.getMaxY(),
                model.getWidth(),
                model.getHeight());
        gui.makeVisible();
        break;
      default:
        showError("unrecognized/incorrect animation type specified");
        break;
    }
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
    for (int i = 0; i < args.length; i++) {
      temp.put(args[i], args[i + 1]);
      i++;
    }
    return temp;
  }
}
