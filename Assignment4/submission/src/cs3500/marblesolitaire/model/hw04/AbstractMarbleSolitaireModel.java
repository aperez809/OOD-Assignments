package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.Cell;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

import java.util.ArrayList;

/**
 * Represents the board of a Marble Solitaire game. Provides modular methods that apply to
 * many different kinds of boards.
 * Allows for variable board size and validity checking of moves.
 */
public abstract class AbstractMarbleSolitaireModel implements MarbleSolitaireModel {
  protected int armThickness;
  protected ArrayList<ArrayList<Cell>> cells;
  protected int sRow;
  protected int sCol;



  /**
   * Constructs a {@code AbstractMarbleSolitaireModel} object.
   *
   *     @param armThickness width of the inner square of the board
   *     @param sRow row coordinate of empty starting cell
   *     @param sCol column coordinate of empty starting cell
   *
   *     @throws IllegalArgumentException {@code armThickness} is invalid (i.e. even
   *     or less than or equal to 0) or {@code sRow} or {@code sCol} are invalid
   *     (i.e. outside board, negative, etc)
   */

  public AbstractMarbleSolitaireModel(int armThickness, int sRow, int sCol) {
    if (!this.isValidAT(armThickness)) {
      throw new IllegalArgumentException("armThickness must be a positive, odd number.");
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
   * Constructs a {@code AbstractMarbleSolitaireModel} object with a default value of 3 for
   * {@code armThickness}.
   *
   *     @param sRow row coordinate of empty starting cell
   *     @param sCol column coordinate of empty starting cell
   *
   *     @throws IllegalArgumentException {@code sRow} or {@code sCol} is invalid (
   *     i.e. outside board, negative, etc)
   */

  public AbstractMarbleSolitaireModel(int sRow, int sCol) {
    this(3,sRow,sCol);
  }

  /**
   * Constructs a {@code AbstractMarbleSolitaireModel} object with default values for
   * {@code sRow} and {@code sCol} set to the middle of the board.
   *
   *     @param armThickness width of the inner square of the board
   *
   *     @throws IllegalArgumentException {@code armThickness} is invalid (i.e. even
   *     or less than or equal to 0)
   */
  public AbstractMarbleSolitaireModel(int armThickness) {
    this(armThickness,
            armThickness * 2 - (armThickness - 2),
            armThickness * 2 - (armThickness - 2));
  }


  public AbstractMarbleSolitaireModel() {
    this(3,3,3);
  }


  protected ArrayList<ArrayList<Cell>> makeBoard() {

    ArrayList<ArrayList<Cell>> board = new ArrayList<ArrayList<Cell>>();

    for (int rowNum = 0; rowNum < this.getBoardLength(); rowNum++) {
      ArrayList<Cell> temp = new ArrayList<>();

      for (int colNum = 0; colNum < this.getBoardLength(); colNum++) {
        temp.add(createProperCell(rowNum, colNum));
      }

      board.add(temp);
    }

    return board;
  }

  private Cell createProperCell(int rowNum, int colNum) {

    if (isInvalidEmptyPosition(this.armThickness, rowNum, colNum)) {
      return new Cell(false, false);
    }
    else if (rowNum == sRow && colNum == sCol) {
      return new Cell(false, true);
    }
    else {
      return new Cell(true, true);
    }
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (!isValidMove(fromRow, fromCol, toRow, toCol)) {
      throw new IllegalArgumentException("Illegal move attempted.");
    }

    Cell movedFrom = this.cells.get(fromRow).get(fromCol);
    Cell movedTo = this.cells.get(toRow).get(toCol);
    Cell jumped = this.cells.get((fromRow + toRow) / 2).get((fromCol + toCol) / 2);

    movedFrom.setHasMarble(false);
    movedTo.setHasMarble(true);
    jumped.setHasMarble(false);
  }

  protected boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
    return !isInvalidEmptyPosition(this.armThickness, fromRow, fromCol)
            && !isInvalidEmptyPosition(this.armThickness, toRow, toCol)
            && !hasMarbleSafe(toRow, toCol)
            && hasMarbleSafe((toRow + fromRow) / 2, (toCol + fromCol) / 2)
            && hasMarbleSafe(fromRow, fromCol)
            && !(Math.abs(fromRow - toRow) > 0 && Math.abs(fromCol - toCol) > 0)
            && (Math.abs(fromRow - toRow) == 2 || Math.abs(fromCol - toCol) == 2);
  }

  protected boolean hasMarbleSafe(int row, int col) {
    if (row == this.getBoardLength() || col == this.getBoardLength()) {
      return false;
    }
    return this.cells.get(row).get(col).containsMarble();
  }

  @Override
  //test all positions on the board for valid moves. If none, then the game is over
  public boolean isGameOver() {
    for (int i = 0; i < this.getBoardLength(); i++) {
      for (int j = 0; j < this.getBoardLength(); j++) {
        if (isValidMove(i, j, i + 2, j)
                || isValidMove(i, j, i - 2, j)
                || isValidMove(i, j, i, j + 2)
                || isValidMove(i, j, i, j - 2)) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  //build list of lists of string, then create each row in a for loop with different behavior
  //depending on the quality of the row (full or outer)
  public String getGameState() {
    StringBuilder boardState = new StringBuilder();
    for (int row = 0; row < this.getBoardLength(); row++) {
      boardState.append(createSingleRow(this.cells.get(row)));
    }
    boardState.deleteCharAt(boardState.length() - 1);
    return boardState.toString();
  }

  protected String createSingleRow(ArrayList<Cell> arr) {
    boolean prevOn = false;
    StringBuilder temp = new StringBuilder();

    for (int i = 0; i < this.getBoardLength(); i++) {
      if (prevOn && !arr.get(i).isOnBoard()) {
        break;
      }
      else {
        if (!arr.get(i).isOnBoard()) {
          temp.append("  ");
        }
        else if (arr.get(i).containsMarble()) {
          temp.append("O ");
          prevOn = true;
        }
        else if (!arr.get(i).containsMarble()) {
          temp.append("_ ");
          prevOn = true;
        }
      }
    }
    temp.deleteCharAt(temp.length() - 1);
    temp.append("\n");

    return temp.toString();
  }

  @Override
  public int getScore() {
    int score = 0;

    for (ArrayList<Cell> arr: this.cells) {
      for (Cell c: arr) {
        if (c.containsMarble()) {
          score++;
        }
      }
    }
    return score;
  }

  protected int getBoardLength() {
    return this.armThickness * 2 + (this.armThickness - 2);
  }

  //determines if position is invalid (negative in row or col coordinates, outside of board bounds
  //at at empty spot on the board (ex: 0,0)
  protected boolean isInvalidEmptyPosition(int armThickness, int row, int col) {
    return row < 0
            || col < 0
            || row >= this.getBoardLength()
            || col >= this.getBoardLength()
            || !isInsideBoardPossibilities(armThickness, row, col);
  }

  protected abstract boolean isInsideBoardPossibilities(int armThickness,
                                                        int freeVal,
                                                        int constrainedVal);

  protected boolean isValidAT(int armThickness) {
    return armThickness % 2 == 1 && armThickness > 0;
  }
}
