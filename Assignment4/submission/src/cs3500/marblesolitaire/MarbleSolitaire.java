package cs3500.marblesolitaire;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModelImpl;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModelImpl;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class made for starting program and controlling all elements of it.
 * (Model and Controller).
 *
 */
public class MarbleSolitaire {

  /**
   * Starts the program, instantiating the correct marble solitaire board with the
   * relevant armThickness and empty spaces.
   *
   * @param args list of arguments passed from the command line,
   *             contains type of board as mandatory argument and
   *             board size and empty space as optional flagged arguments
   *
   */
  public static void main(String[] args) {

    MarbleSolitaireModel model = modelMaker(args);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(
            new InputStreamReader(System.in),
            //new StringReader(new Scanner(System.in).nextLine()),
            System.out);
    controller.playGame(model);
  }

  private static MarbleSolitaireModel modelMaker(String[] args) {

    ArrayList<String> arr = new ArrayList<>();
    arr.addAll(Arrays.asList(args));

    MarbleSolitaireModel model = null;

    if (args.length == 6) {
      int holeIndex = arr.indexOf("-hole");
      int sizeIndex = arr.indexOf("-size");

      switch (args[0]) {
        case "english":
          model = new MarbleSolitaireModelImpl(sizeIndex + 1,
                  Integer.valueOf(arr.get(holeIndex + 1)),
                  Integer.valueOf(arr.get(holeIndex + 2)));
          break;

        case "european":
          model = new EuropeanSolitaireModelImpl(sizeIndex + 1,
                  Integer.valueOf(arr.get(holeIndex + 1)),
                  Integer.valueOf(arr.get(holeIndex + 2)));
          break;

        case "triangle":
          model = new TriangleSolitaireModelImpl(sizeIndex + 1,
                  Integer.valueOf(arr.get(holeIndex + 1)),
                  Integer.valueOf(arr.get(holeIndex + 2)));
          break;
        default:
          break;
      }
    }

    if (args.length == 4) {
      switch (args[0]) {
        case "english":
          model = new MarbleSolitaireModelImpl(
                  Integer.valueOf(args[2]), Integer.valueOf(args[3]));
          break;

        case "european":
          model = new EuropeanSolitaireModelImpl(
                  Integer.valueOf(args[2]), Integer.valueOf(args[3]));
          break;

        case "triangle":
          model = new TriangleSolitaireModelImpl(
                  Integer.valueOf(args[2]), Integer.valueOf(args[3]));
          break;
        default:
          break;
      }
    }

    if (args.length == 3) {
      switch (args[0]) {
        case "english":
          model = new MarbleSolitaireModelImpl(
                  Integer.valueOf(args[2]));
          break;

        case "european":
          model = new EuropeanSolitaireModelImpl(
                  Integer.valueOf(args[2]));
          break;

        case "triangle":
          model = new TriangleSolitaireModelImpl(
                  Integer.valueOf(args[2]));
          break;
        default:
          break;
      }
    }
    else {
      switch (args[0]) {
        case "english":
          model = new MarbleSolitaireModelImpl();
          break;

        case "european":
          model = new EuropeanSolitaireModelImpl();
          break;

        case "triangle":
          model = new TriangleSolitaireModelImpl();
          break;
        default:
          break;
      }
    }
    return model;
  }
}




