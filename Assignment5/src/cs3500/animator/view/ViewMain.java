package cs3500.animator.view;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.model.Shape;
import cs3500.animator.model.util.AnimationReader;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public final class ViewMain {

  // CL args will follow this template:
  // -in "name-of-animation-file" -view "type-of-view" -out "where-output-show-go" -speed "integer-ticks-per-second"

  public static void main(String[] args) throws FileNotFoundException, IOException {
    AnimationModel model;
    Readable file;
    Appendable output;

    HashMap<String, String> cla = new HashMap<>();
    for (int i = 0; i < args.length; i++) {
      cla.put(args[i], args[i+1]);
      i++;
    }

    try {
      file = new FileReader(setFile(cla));
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("File not found");
    }

    model = AnimationReader.parseFile(file,
            new AnimationModelImpl.Builder(
                    new AnimationModelImpl(
                            new StringBuilder()
                            , new HashMap<>(),
                            500,500,500,500)));

    switch (cla.get("-view")) {
      case "text":
        output = System.out;
        break;
      case "svg":
        try {
          output = new FileWriter(cla.get("out"));
          break;
        }
        catch (IOException e) {
          throw new IOException("File could not be written");
      }
      case "visual":
        break;
    }


    IView gui = new AnimationGraphicsView();
    AnimationPanelView panel = new AnimationPanelView((ArrayList<Shape>) model.getShapes());
    gui.makeVisible();

      while (true) {
        gui.refresh();
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
