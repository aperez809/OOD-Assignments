package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;


/**
 * Represents the board of a European Marble Solitaire game.
 * Allows for variable board size and validity checking of moves.
 */
public class EuropeanSolitaireModelImpl
        extends AbstractMarbleSolitaireModel
        implements MarbleSolitaireModel {


  /**
   * Constructs a {@code EuropeanMarbleSolitaireImpl} object
   * with a default value of 3 for {@code armThickness}, {@code sRow},
   * and {@code sCol}.
   *
   * @throws IllegalArgumentException {@code sRow} or {@code sCol} is invalid (
   *                                  i.e. outside board, negative, etc)
   */
  public EuropeanSolitaireModelImpl(int armThickness, int sRow, int sCol) {
    super(armThickness, sRow, sCol);
  }

  /**
   * Constructs a {@code EuropeanMarbleSolitaireModel} object with default values for
   * {@code armThickness} as 3, {@code sRow} and {@code sCol} set to the middle of the board.
   *
   *
   */
  public EuropeanSolitaireModelImpl() {
    this(3,3,3);
  }

  /**
   * Constructs a {@code EuropeanMarbleSolitaireModel} object with a default value of 3 for
   * {@code armThickness}.
   *
   *     @param sRow row coordinate of empty starting cell
   *     @param sCol column coordinate of empty starting cell
   *
   *     @throws IllegalArgumentException {@code sRow} or {@code sCol} is invalid (
   *     i.e. outside board, negative, etc)
   */
  public EuropeanSolitaireModelImpl(int sRow, int sCol) {
    this(3, sRow, sCol);
  }

  /**
   * Constructs a {@code EuropeanMarbleSolitaireModel} object with default values for
   * {@code sRow} and {@code sCol} set to the middle of the board.
   *
   *     @param armThickness width of the inner square of the board
   *
   *     @throws IllegalArgumentException {@code armThickness} is invalid (i.e. even
   *     or less than or equal to 0)
   */
  public EuropeanSolitaireModelImpl(int armThickness) {
    this(armThickness,
            armThickness * 2 - (armThickness - 2),
            armThickness * 2 - (armThickness - 2));
  }



  protected boolean isInsideBoardPossibilities(int armThickness,
                                               int freeVal,
                                               int constrainedVal) {
    if (freeVal <= armThickness - 2) {
      return constrainedVal <= (this.getBoardLength() - armThickness) + freeVal
              && constrainedVal >= (this.getBoardLength() - armThickness) / 2 - freeVal;
    }
    else if (freeVal >= armThickness - 1 && freeVal < (armThickness * 2 - 1)) {
      return true;
    }
    else {
      return constrainedVal <= (this.getBoardLength() - armThickness)
              + (this.getBoardLength() - freeVal - 1)
              && constrainedVal >= (this.getBoardLength() - armThickness) / 2
              - (this.getBoardLength() - freeVal - 1);
    }
  }
}
