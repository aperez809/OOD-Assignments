package cs3500.marblesolitaire.model.hw02;

public class Cell {
  final int rowPos;
  final int colPos;
  boolean hasMarble;
  final boolean isOnBoard;
  Cell left;
  Cell right;
  Cell up;
  Cell down;


  public Cell(int rowPos, int colPos, boolean hasMarble, boolean isOnBoard, Cell left, Cell right, Cell up, Cell down) {
    this.rowPos = rowPos;
    this.colPos = colPos;
    this.hasMarble = hasMarble;
    this.isOnBoard = isOnBoard;
    this.left = left;
    this.right = right;
    this.up = up;
    this.down = down;
  }

  public Cell(int rowPos, int colPos, boolean hasMarble, boolean isOnBoard) {
    this.rowPos = rowPos;
    this.colPos = colPos;
    this.hasMarble = hasMarble;
    this.isOnBoard = isOnBoard;
    this.left = null;
    this.right = null;
    this.up = null;
    this.down = null;
  }
}
