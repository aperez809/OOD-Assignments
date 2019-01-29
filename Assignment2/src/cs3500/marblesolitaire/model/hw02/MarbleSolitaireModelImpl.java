package cs3500.marblesolitaire.model.hw02;

import java.util.ArrayList;


/**
 * Represents the board of a Marble Solitaire game.
*/
public class MarbleSolitaireModelImpl implements MarbleSolitaireModel {
  private final int armThickness;
  private ArrayList<ArrayList<Cell>> cells;
  private int sRow;
  private int sCol;

  /**
   * Constructs a {@code MarbleSolitaireModelImpl} object
   * with a default value of 3 for {@code armThickness}, {@code sRow},
   * and {@code sCol}.
   */

  public MarbleSolitaireModelImpl() {
    this.armThickness = 3;
    this.sRow = 3;
    this.sCol = 3;

    this.cells = this.makeBoard();
  }


  /**
   * Constructs a {@code MarbleSolitaireModelImpl} object with a default value of 3 for
   * {@code armThickness}.
   *
   *     @param sRow row coordinate of empty starting cell
   *     @param sCol column coordinate of empty starting cell
   *
   *     @throws IllegalArgumentException {@code sRow} or {@code sCol} is invalid (
   *     i.e. outside board, negative, etc)
   */

  public MarbleSolitaireModelImpl(int sRow, int sCol) {
    this.armThickness = 3;
    if (isInvalidEmptyPosition(armThickness, sRow, sCol)) {
      throw new IllegalArgumentException(
              String.format("Invalid empty cell position (%d, %d)", sRow, sCol));
    }
    this.sRow = sRow;
    this.sCol = sCol;

    this.cells = this.makeBoard();

  }

  /**
   * Constructs a {@code MarbleSolitaireModelImpl} object with default values for
   * {@code sRow} and {@code sCol} set to the middle of the board.
   *
   *     @param armThickness width of the inner square of the board
   *
   *     @throws IllegalArgumentException {@code armThickness} is invalid (i.e. even
   *     or less than or equal to 0)
   */
  public MarbleSolitaireModelImpl(int armThickness) {
    if (armThickness % 2 != 1 || armThickness <= 0) {
      throw new IllegalArgumentException("armThickness must be a positive, odd number.");
    }
    this.sRow = armThickness - 1 + armThickness / 2;
    this.sCol = armThickness - 1 + armThickness / 2;
    this.armThickness = armThickness;

    this.cells = this.makeBoard();
  }

  /**
   * Constructs a {@code MarbleSolitaireModelImpl} object.
   *
   *     @param armThickness width of the inner square of the board
   *     @param sRow row coordinate of empty starting cell
   *     @param sCol column coordinate of empty starting cell
   *
   *     @throws IllegalArgumentException {@code armThickness} is invalid (i.e. even
   *     or less than or equal to 0) or {@code sRow} or {@code sCol} are invalid
   *     (i.e. outside board, negative, etc)
   */

  public MarbleSolitaireModelImpl(int armThickness, int sRow, int sCol) {
    if (armThickness % 2 != 1 || armThickness <= 0) {
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

  private ArrayList<ArrayList<Cell>> makeBoard() {

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

    movedFrom.hasMarble = false;
    movedTo.hasMarble = true;
    jumped.hasMarble = false;
  }

  private boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
    return !isInvalidEmptyPosition(this.armThickness, fromRow, fromCol)
            && !isInvalidEmptyPosition(this.armThickness, toRow, toCol)
            && !hasMarbleSafe(toRow, toCol)
            && hasMarbleSafe((toRow + fromRow) / 2, (toCol + fromCol) / 2)
            && hasMarbleSafe(fromRow, fromCol)
            && !(Math.abs(fromRow - toRow) > 0 && Math.abs(fromCol - toCol) > 0)
            && (Math.abs(fromRow - toRow) == 2 || Math.abs(fromCol - toCol) == 2);
  }

  private boolean hasMarbleSafe(int row, int col) {
    try {
      return this.cells.get(row).get(col).hasMarble;
    }
    catch (IndexOutOfBoundsException e) {
      return false;
    }
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
    ArrayList<ArrayList<String>> boardState = new ArrayList<ArrayList<String>>();

    for (int row = 0; row < this.getBoardLength(); row++) {
      boardState.add(createSingleRow(this.cells.get(row)));
    }

    return formattedBoardState(boardState);
  }

  private ArrayList<String> createSingleRow(ArrayList<Cell> arr) {
    boolean fullRow = arr.get(0).isOnBoard;
    ArrayList<String> temp = new ArrayList<>();
    if (fullRow) {
      for (int i = 0; i < this.getBoardLength(); i++) {
        if (arr.get(i).hasMarble) {
          temp.add("O");
        }

        else if (!arr.get(i).hasMarble) {
          temp.add("_");
        }
        if (i != this.getBoardLength() - 1) {
          temp.add(" ");
        }
      }
    }

    else {
      for (int i = 0; i < this.armThickness * 2 - 1; i++) {
        if (!arr.get(i).isOnBoard) {
          temp.add(" ");
        }

        else if (arr.get(i).hasMarble) {
          temp.add("O");
        }

        else if (!arr.get(i).hasMarble) {
          temp.add("_");
        }
        if (i != this.armThickness * 2 - 2) {
          temp.add(" ");
        }
      }
    }

    temp.add("\n");
    return temp;
  }

  //delete spaces in first row, then last row, then second row, then second to last row
  private String formattedBoardState(ArrayList<ArrayList<String>> boardState) {

    StringBuilder formattedBoard = new StringBuilder();

    for (ArrayList<String> arr: boardState) {
      for (String s: arr) {
        formattedBoard.append(s);
      }
    }

    formattedBoard.deleteCharAt(formattedBoard.length() - 1);
    return formattedBoard.toString();
  }

  @Override
  public int getScore() {
    int score = 0;

    for (ArrayList<Cell> arr: this.cells) {
      for (Cell c: arr) {
        if (c.hasMarble) {
          score++;
        }
      }
    }
    return score;
  }

  //determines if position is invalid (negative in row or col coordinates, outside of board bounds
  //at at empty spot on the board (ex: 0,0)
  private static boolean isInvalidEmptyPosition(int armThickness, int row, int col) {
    return row < 0
            || col < 0
            || row > armThickness * 2 + (armThickness - 3)
            || col > armThickness * 2 + (armThickness - 3)
            || !isInsideBoardPossibilities(armThickness, row, col);
  }

  private static boolean isInsideBoardPossibilities(int armThickness,
                                                    int freeVal,
                                                    int constrainedVal) {
    int middlePiece = (armThickness * 2 + (armThickness - 3)) / 2;
    int validMargin = (armThickness - 1) / 2;


    return (freeVal <= middlePiece + validMargin
            && freeVal >= middlePiece - validMargin)
            || (constrainedVal <= middlePiece + validMargin
              && constrainedVal >= middlePiece - validMargin);
  }


  private int getBoardLength() {
    return this.armThickness * 2 + (this.armThickness - 2);
  }
}
