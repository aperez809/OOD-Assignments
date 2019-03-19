package cs3500.animator.view;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.model.util.AnimationReader;
import java.io.*;
import java.util.HashMap;

public final class ViewMain {

  // CL args will follow this template:
  // -in "name-of-animation-file" -view "type-of-view" -out "where-output-show-go" -speed "integer-ticks-per-second"

  public static void main(String[] args) throws IOException {
    AnimationModel model;
    Readable file;
    Appendable output;


    HashMap<String, String> cla = argsToHM(args);

    //throw IllegalArgumentException if this fails
    checkValidArgs(cla);

    try {
      file = new FileReader(setFile(cla));
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("File not found");
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
                  model.getMaxX(),
                  model.getMaxY(),
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
          throw new IOException("File could not be written");
        }

      case "visual":
        AnimationPanelView panel = new AnimationPanelView(
                model.getShapes(),
                Integer.valueOf(cla.get("-speed")));
        IView gui = new AnimationGraphicsView(panel, model.getMaxX(), model.getMaxY(),
                model.getWidth(), model.getHeight());

        gui.add(panel);
        gui.makeVisible();
    }
  }

  private static void checkValidArgs(HashMap<String, String> cla) {
    if (!cla.containsKey("-in") || !cla.containsKey("-view")) {
      throw new IllegalArgumentException("-in and -view flags are required arguments");
    }
    else if (!cla.containsKey("-out")) {
      cla.put("-out", "System.out");
    }

    else if (!cla.containsKey("-speed")) {
      cla.put("-out", "1");
    }
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
      temp.put(args[i], args[i+1]);
      i++;
    }
    return temp;
  }
}
