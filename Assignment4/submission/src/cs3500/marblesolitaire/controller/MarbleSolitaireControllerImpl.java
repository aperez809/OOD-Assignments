package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

import java.io.IOException;

import java.util.ArrayList;

import java.util.Scanner;

/**
 * Represents the controller of a Marble Solitaire game.
 * provides functionality for reading and writing user input to
 * make moves within the game and get information about its state.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private final Readable rd;
  private final Appendable ap;
  private boolean hasQuit;



  /**
   * Constructs a {@code MarbleSolitaireControllerImpl} object.
   *
   *     @param rd Readable object such as StringReader for taking user input
   *     @param ap Appendable object such as StringBuilder for printing user input
   *
   *     @throws IllegalArgumentException if rd or ap are null.
   */
  public MarbleSolitaireControllerImpl(Readable rd, Appendable ap) throws IllegalArgumentException {
    if (rd == null || ap == null) {
      throw new IllegalArgumentException("Readable and Appendable must not be null.");
    }

    this.rd = rd;
    this.ap = ap;
  }

  /**
   * starts a game of Marble Solitaire by interacting with the given model.
   *
   *     @param model MarbleSolitaireMarble object to be controlled by controller.
   *
   *     @throws IllegalArgumentException if model is null.
   *     @throws IllegalStateException if controller is unable to write to the ap field or read
   *              from rd field.
   */
  @Override
  public void playGame(MarbleSolitaireModel model) {
    //ensure model is non-null and instantiate Scanner
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }


    Scanner sc = new Scanner(this.rd);


    //if the game is over, proceed to end game sequence
    while (!model.isGameOver()) {

      //write both current game state and current score to this.ap
      this.transmitStateAndScore(model);
      this.getMoveCoords(sc, model);
      if (this.hasQuit) {
        this.endGamePlayerQuit(model);
        return;
      }
    }

    this.endGameNoMovesLeft(model);
  }

  //use model param to get game state and score then transmit to this.ap
  private void transmitStateAndScore(MarbleSolitaireModel model) {
    sendToAppendable(String.format(
            "%s\nScore: %s\n",
            model.getGameState(),
            Integer.toString(model.getScore())));
  }

  //for each of the 4 coordinates in the move, check for either validity or quit
  private void getMoveCoords(Scanner sc, MarbleSolitaireModel model) {
    ArrayList<Integer> coords = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      Integer val = this.getValidCoordInput(sc, model);
      if (val == null) {
        this.hasQuit = true;
        return;
      }
      coords.add(val - 1);
    }

    try {
      model.move(coords.get(0), coords.get(1), coords.get(2), coords.get(3));
    }
    catch (IllegalArgumentException illArgE) {
      sendToAppendable("Invalid move. Play again. Moves must be made to an"
              + " unoccupied space from and over a single occupied one in either a "
              + "vertical or horizontal direction.\n");
    }
  }


  //if user has passed "q" or "Q" to scanner, end game
  private void endGameNoMovesLeft(MarbleSolitaireModel model) {
    sendToAppendable(String.format(
            "Game over!\n%s\nScore: %s\n",
            model.getGameState(),
            Integer.toString(model.getScore())));
  }

  private Integer getValidCoordInput(Scanner sc, MarbleSolitaireModel model) {
    //TODO: Add support for quitting input ("Q" or "q")

    while (sc.hasNext()) {

      String val = sc.next();

      try {

        if (Integer.parseInt(val) >= 1) {
          return Integer.parseInt(val);
        }
        else {
          System.out.println("Input must be positive integer greater than 1, Q, or q.");
        }
      }

      catch (NumberFormatException nfE) {

        if (val.equalsIgnoreCase("q")) {
          return null;
        }
        else {
          System.out.println("Input must be positive integer greater than 1, Q, or q.");
        }
      }
    }
    throw new IllegalStateException("Unable to read from input.");
  }


  private void endGamePlayerQuit(MarbleSolitaireModel model) {
    sendToAppendable(String.format("Game quit!\nState of game when quit:\n%s\nScore: %s\n",
              model.getGameState(),
              Integer.toString(model.getScore())));
  }

  private void sendToAppendable(String toSend) {
    try {
      this.ap.append(toSend);
    }
    catch (IOException e) {
      throw new IllegalStateException("Unable to successfully write to output.");
    }
  }
}

