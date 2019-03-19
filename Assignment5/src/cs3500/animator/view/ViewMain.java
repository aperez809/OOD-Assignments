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


    HashMap<String, String> cla = new HashMap<>();
    for (int i = 0; i < args.length; i++) {
      cla.put(args[i], args[i+1]);
      i++;
    }

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
        IView view = new TextRepresentation(model.getShapes());
        view.createAnimOutput();
        output.append(view.getOutput().toString());
        System.out.println(output);
        return;

      case "svg":
        try {
          output = new FileWriter(cla.get("-out"));
          IView svg = new SVGRepresentation(model.getShapes(), Integer.valueOf(cla.get("-speed")));
          svg.createAnimOutput();
          //System.out.println(svg.getOutput().toString());
          output.append(svg.getOutput().toString());
          return;
          //TODO: Figure out SVG

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

        setTimerSpeed(cla, panel);
        gui.add(panel);
        gui.makeVisible();
        //TODO: Put swing stuff here
        break;
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

  private static void setTimerSpeed(HashMap<String, String> cla, AnimationPanelView panel) {
    if (cla.containsKey("-speed")) {
      panel.setTimerSpeed(Integer.valueOf(cla.get("-speed")));
    }
    else {
      panel.setTimerSpeed(1);
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
}
