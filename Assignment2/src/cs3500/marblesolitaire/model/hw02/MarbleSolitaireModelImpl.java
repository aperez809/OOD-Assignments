package cs3500.marblesolitaire.model.hw02;

import java.util.ArrayList;

/**
 * Represents the board of a Marble Solitaire game
*/
public class MarbleSolitaireModelImpl implements MarbleSolitaireModel {
  private final int armThickness;
  private ArrayList<ArrayList<Cell>> cells;
  private int sRow;
  private int sCol;

  public MarbleSolitaireModelImpl() {
    this.armThickness = 3;
    this.sRow = 3;
    this.sCol = 3;

    this.cells = this.makeBoard();
    this.assignNeighbors();
  }

  public MarbleSolitaireModelImpl(int sRow, int sCol) {
    this.armThickness = 3;
    if (isInvalidEmptyPosition(armThickness, sRow, sCol)) {
      throw new IllegalArgumentException(
              String.format("Invalid empty cell position (%d, %d)", sRow, sCol));
    }
    this.sRow = sRow;
    this.sCol = sCol;

    this.cells = this.makeBoard();
    this.assignNeighbors();

  }

  public MarbleSolitaireModelImpl(int armThickness) {
    if (armThickness % 2 != 1 || armThickness <= 0) {
      throw new IllegalArgumentException("armThickness must be a positive, odd number.");
    }
    this.armThickness = armThickness;

    this.cells = this.makeBoard();
    this.assignNeighbors();
  }

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
    this.assignNeighbors();
  }

  private ArrayList<ArrayList<Cell>> makeBoard() {

    ArrayList<ArrayList<Cell>> board = new ArrayList<ArrayList<Cell>>();

    for (int colNum = 0; colNum < this.getBoardLength(); colNum++) {
      ArrayList<Cell> temp = new ArrayList<>();

      for (int rowNum = 0; rowNum < this.getBoardLength(); rowNum++) {
        temp.add(createProperCell(rowNum, colNum));
      }

      board.add(temp);
    }

    return board;
  }

  private Cell createProperCell(int rowNum, int colNum) {

    if (isInvalidEmptyPosition(this.armThickness, rowNum, colNum)) {
      return new Cell(rowNum, colNum, false, false);
    }
    else if (rowNum == sRow && colNum == sCol) {
      return new Cell(rowNum, colNum, false, true);
    }
    else {
      return new Cell(rowNum, colNum, true, true);
    }
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (isInvalidMoveAttempted(fromRow, fromCol, toRow, toCol)) {
      throw new IllegalArgumentException("Illegal move attempted.");
    }
  }

  private boolean isInvalidMoveAttempted(int fromRow, int fromCol, int toRow, int toCol) {
    return Math.abs(fromRow - toRow) != 2
            || Math.abs(fromCol - toCol) != 2
            || (Math.abs(fromRow - toRow) > 0 && Math.abs(fromCol - toCol) > 0)
            || this.cells.get(toCol).get(toRow).hasMarble
            || isInvalidEmptyPosition(this.armThickness, toRow, toCol);
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public String getGameState() {

    StringBuilder boardState = new StringBuilder();

    for (ArrayList<Cell> arr: this.cells) {
      for (Cell c : arr) {
        if (!c.isOnBoard) {
          boardState.append(" ");
        }
        else if (c.hasMarble) {
          boardState.append("O");
        }
        else {
          boardState.append("_");
        }
        boardState.append(" ");
      }

      boardState.append("\n");
    }
    boardState.deleteCharAt(boardState.length() - 1);

    return boardState.toString();
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

  private static boolean isInvalidEmptyPosition(int armThickness, int sRow, int sCol) {
    return sRow < 0
            || sCol < 0
            || sRow > armThickness * 2 + (armThickness - 3)
            || sCol > armThickness * 2 + (armThickness - 3)
            || !isInsideBoardPossibilities(armThickness, sRow, sCol);
  }

  private static boolean isInsideBoardPossibilities(int armThickness,
                                                    int freeVal,
                                                    int constrainedVal) {
    int middlePiece = (armThickness * 2 + (armThickness - 3)) / 2;
    int validMargin = (armThickness - 1) / 2;

    if (freeVal <= middlePiece + validMargin
            && freeVal >= middlePiece - validMargin) {
      return true;
    }

    else {
      return constrainedVal <= middlePiece + validMargin
              && constrainedVal >= middlePiece - validMargin;
    }
  }

  //uses nested loops to access elements of list of lists. Depending on which neighbor is being
  //assigned, sets neighbor to either next or previous indexed element in same list or
  //same indexed element in next or previous list
  private void assignNeighbors() {
    for (int i = 0; i < this.cells.size(); i++) {
      for (int j = 0; j < this.cells.size(); j++) {
        this.cells.get(i).get(j).up = this.findNeighbor(i - 1, j);
        this.cells.get(i).get(j).down = this.findNeighbor(i + 1, j);
        this.cells.get(i).get(j).left = this.findNeighbor(i, j - 1);
        this.cells.get(i).get(j).right = this.findNeighbor(i, j + 1);
      }
    }
  }

  //confirms given i and j indexes are not outside bounds of board. If so, set neighbor to null as,
  //as that would indicate an edge cell.
  //Otherwise, sets neighbor to element at given index of board
  private Cell findNeighbor(int i, int j) {
    if (i < 0 || i >= this.cells.size()
            || j < 0 || j >= this.cells.size()) {
      return null;
    }
    else {
      return this.cells.get(i).get(j);
    }
  }

  private int getBoardLength() {
    return this.armThickness * 2 + (this.armThickness - 2);
  }
}
