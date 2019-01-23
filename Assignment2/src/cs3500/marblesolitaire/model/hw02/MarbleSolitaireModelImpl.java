package cs3500.marblesolitaire.model.hw02;

/**
 * Represents the board of a Marble Solitaire game
*/
public class MarbleSolitaireModelImpl implements MarbleSolitaireModel {
  private int armThickness;
  private int sRow;
  private int sCol;

  public MarbleSolitaireModelImpl() {
    this.armThickness = 3;
    this.sRow = 3;
    this.sCol = 3;
  }

  public MarbleSolitaireModelImpl(int sRow, int sCol) {
    this.armThickness = 3;
    if (invalidEmptyPosition(armThickness, sRow,sCol)) {
      throw new IllegalArgumentException(
              String.format("Invalid empty cell position (%d, %d)", sRow, sCol));
    }
    this.sRow = sRow;
    this.sCol = sCol;
  }

  public MarbleSolitaireModelImpl(int armThickness) {
    if (armThickness % 2 != 1 || armThickness <= 0) {
      throw new IllegalArgumentException("armThickness must be a positive, odd number.");
    }
    this.armThickness = armThickness;
  }

  public MarbleSolitaireModelImpl(int armThickness, int sRow, int sCol) {
    if (armThickness % 2 != 1 || armThickness <= 0) {
      throw new IllegalArgumentException("armThickness must be a positive, odd number.");
    }
    this.armThickness = armThickness;

    if (invalidEmptyPosition(armThickness, sRow, sCol)) {
      throw new IllegalArgumentException(
              String.format("Invalid empty cell position (%d, %d)", sRow, sCol));
    }
    this.sRow = sRow;
    this.sCol = sCol;
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {

  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public String getGameState() {
    //TODO: for loop running through each row. At each point print either a space, o, or _
    //TODO: followed by a space
    int boardLength = this.armThickness * 2 + (this.armThickness - 2);
    StringBuilder boardState = new StringBuilder();

    for (int i = 0; i < boardLength; i++) {
      for (int j = 0; j < boardLength; j++) {
        if
      }
    }
  }

  @Override
  public int getScore() {
    return 0;
  }

  private static boolean invalidEmptyPosition(int armThickness, int sRow, int sCol) {

    //TODO: First 4 lines are correct and work
    //TODO: Need to have logic for handling when the sRow value makes certain values of sCol illegal
    //TODO: And vice versa
    return sRow >= 0
            && sCol >= 0
            && sRow <= armThickness * 2 + (armThickness - 2)
            && sCol <= armThickness * 2 + (armThickness - 2)
            && (insideBoardPossibilities(armThickness, sRow, sCol)
            || insideBoardPossibilities(armThickness, sCol, sRow));

    //TODO: Middle piece == Math.floor(armThickness * 2 + (armThickness - 2) / 2)
    //TODO: so if either sRow or sCol is outside the bound of middle
    //TODO: piece +/- Math.floor(armThickness / 2
  }

  private static boolean insideBoardPossibilities(int armThickness, int a, int b) {
    int middlePiece = (int) Math.floor(armThickness * 2 + (armThickness - 2) / 2);
    if (a > middlePiece + (int) Math.floor(armThickness / 2)
            || a < middlePiece - (int) Math.floor(armThickness / 2)) {
      return b > middlePiece + (int) Math.floor(armThickness / 2)
              || b < middlePiece - (int) Math.floor(armThickness / 2);
    }

    else {
      return true;
    }
  }
}
