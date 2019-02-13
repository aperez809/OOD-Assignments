package cs3500.marblesolitaire.model.hw02;


/**
 * Represents a single cell of a Marble Solitaire game.
 * provides functionality for determining if there is a marble in the cell
 * and if the cell is within the confines of the game board (i.e. not invalid like 0,0)
 */
public class Cell {

  private boolean containsMarble;
  private final boolean isOnBoard;


  public Cell(boolean hasMarble, boolean isOnBoard) {
    this.containsMarble = hasMarble;
    this.isOnBoard = isOnBoard;
  }
  public boolean containsMarble() {
    return this.containsMarble;
  }


  public void setHasMarble(boolean hasMarble) {
    this.containsMarble = hasMarble;
  }

  public boolean isOnBoard() {
    return isOnBoard;
  }
}

