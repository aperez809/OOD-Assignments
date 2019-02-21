package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Represents a triangular marble solitaire board with non-Cartesian points.
 * Extends the AbstractMarbleSolitaireModel class but needs some custom methods
 * due to its divergent nature.
 *
 */

public class TriangleSolitaireModelImpl extends AbstractMarbleSolitaireModel
        implements MarbleSolitaireModel {

  /**
   * Constructs a {@code TriangleMarbleSolitaireModel} object.
   *
   *     @param armThickness width of the inner square of the board
   *     @param sRow row coordinate of empty starting cell
   *     @param sCol column coordinate of empty starting cell
   *
   *     @throws IllegalArgumentException {@code armThickness} is invalid (i.e. less than 1)
   *                                      or {@code sRow} or {@code sCol} are invalid
   *                                      (i.e. outside board, negative, etc)
   */
  public TriangleSolitaireModelImpl(int armThickness, int sRow, int sCol) {
    super(armThickness, sRow, sCol);
    if (!this.isValidAT(armThickness)) {
      throw new IllegalArgumentException("armThickness must be a positive number.");
    }
    this.armThickness = armThickness;

    if (isInvalidEmptyPosition(armThickness, sRow, sCol)) {
      throw new IllegalArgumentException(
              String.format("Invalid empty cell position (%d, %d)", sRow, sCol));
    }
    this.sRow = sRow;
    this.sCol = sCol;

    this.cells = this.makeBoard();
  }

  /**
   * Constructs a {@code TriangleMarbleSolitaireModel} object with a default value of 5 for
   * {@code armThickness}.
   *
   *     @param sRow row coordinate of empty starting cell
   *     @param sCol column coordinate of empty starting cell
   *
   *     @throws IllegalArgumentException {@code sRow} or {@code sCol} is invalid (
   *     i.e. outside board, negative, etc)
   */
  public TriangleSolitaireModelImpl(int sRow, int sCol) {
    this(5, sRow, sCol);
  }

  /**
   * Constructs a {@code TriangleMarbleSolitaireModel} object with default values for
   * {@code sRow} and {@code sCol} set to the middle of the board.
   *
   *     @param armThickness width of the inner square of the board
   *
   *     @throws IllegalArgumentException {@code armThickness} is invalid (i.e. less than 1)
   */
  public TriangleSolitaireModelImpl(int armThickness) {
    this(armThickness, 0, 0);
  }

  /**
   * Constructs a {@code TriangleMarbleSolitaireModel} object with default values for
   * {@code armThickness} as 5, {@code sRow} and {@code sCol} set to the middle of the board.
   *
   *
   */
  public TriangleSolitaireModelImpl() {
    this(5, 0, 0);
  }

  @Override
  protected boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
    return !isInvalidEmptyPosition(this.armThickness, fromRow, fromCol)
            && !isInvalidEmptyPosition(this.armThickness, toRow, toCol)
            && !hasMarbleSafe(toRow, toCol)
            && hasMarbleSafe((toRow + fromRow) / 2, (toCol + fromCol) / 2)
            && hasMarbleSafe(fromRow, fromCol)
            && (Math.abs(fromRow - toRow) + Math.abs(fromCol - toCol) == 2
            || (Math.abs(fromRow - toRow) == 2 && Math.abs(fromCol - toCol) == 2));
    //(toRow - fromRow == 2 && toCol - fromCol == 2));
  }

  @Override
  protected boolean isInsideBoardPossibilities(int armThickness, int freeVal, int constrainedVal) {
    return constrainedVal <= this.getBoardLength() - (this.getBoardLength() - freeVal);
  }

  protected int getBoardLength() {
    return this.armThickness;
  }

  @Override
  protected boolean isValidAT(int armThickness) {
    return armThickness > 0;
  }


  //determines if position is invalid (negative in row or col coordinates, outside of board bounds
  //at at empty spot on the board (ex: 0,0)
  @Override
  protected boolean isInvalidEmptyPosition(int armThickness, int row, int col) {
    return row < 0
            || col < 0
            || row >= this.getBoardLength()
            || col > row
            || !isInsideBoardPossibilities(armThickness, row, col);
  }


  @Override
  public String getGameState() {
    StringBuilder boardState = new StringBuilder();

    for (int row = 0; row < this.getBoardLength(); row++) {
      for (int spaces = 0; spaces < this.getBoardLength() - (row + 1); spaces++) {
        boardState.append(" ");
      }
      boardState.append(this.createSingleRow(this.cells.get(row)));
    }
    boardState.deleteCharAt(boardState.length() - 1);
    return boardState.toString();
  }

  @Override
  public boolean isGameOver() {
    for (int i = 0; i < this.getBoardLength(); i++) {
      for (int j = 0; j < this.getBoardLength(); j++) {
        if (isValidMove(i, j, i + 2, j)
                || isValidMove(i, j, i - 2, j)
                || isValidMove(i, j, i, j + 2)
                || isValidMove(i, j, i, j - 2)
                || isValidMove(i, j, i + 2, j - 2)
                || isValidMove(i, j, i - 2, j + 2)
                || isValidMove(i, j, i - 2, j - 2)) {
          return false;
        }
      }
    }
    return true;
  }
}
