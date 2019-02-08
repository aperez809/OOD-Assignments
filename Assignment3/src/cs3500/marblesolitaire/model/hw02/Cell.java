package cs3500.marblesolitaire.model.hw02;


/**
 * Represents a single cell of a Marble Solitaire game.
 * provides functionality for determining if there is a marble in the cell
 * and if the cell is within the confines of the game board (i.e. not invalid like 0,0)
 */
class Cell {

  boolean hasMarble;
  final boolean isOnBoard;


  Cell(boolean hasMarble, boolean isOnBoard) {
    this.hasMarble = hasMarble;
    this.isOnBoard = isOnBoard;

  }
}
