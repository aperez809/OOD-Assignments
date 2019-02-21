package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Represents the controller of a Marble Solitaire game.
 * provides functionality for running the game, and reading and writing user input.
 */
public interface MarbleSolitaireController {
  void playGame(MarbleSolitaireModel model) throws IllegalArgumentException, IllegalStateException;
}
