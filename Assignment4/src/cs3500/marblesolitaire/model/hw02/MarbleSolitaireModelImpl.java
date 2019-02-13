package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.hw04.AbstractMarbleSolitaireModel;

import java.util.ArrayList;


/**
 * Represents the board of a Marble Solitaire game.
 * Allows for variable board size and validity checking of moves.
*/
public class MarbleSolitaireModelImpl
        extends AbstractMarbleSolitaireModel
        implements MarbleSolitaireModel {


  /**
   * Constructs a {@code MarbleSolitaireModelImpl} object
   * with a default value of 3 for {@code armThickness}, {@code sRow},
   * and {@code sCol}.
   */

  public MarbleSolitaireModelImpl() {
    super();
  }

  public MarbleSolitaireModelImpl(int sRow, int sCol) {
    super(sRow, sCol);
  }

  public MarbleSolitaireModelImpl(int armThickness) {
    super(armThickness);
  }

  public MarbleSolitaireModelImpl(int armThickness, int sRow, int sCol) {
    super(armThickness, sRow, sCol);
  }






  protected boolean isInsideBoardPossibilities(int armThickness,
                                                    int freeVal,
                                                    int constrainedVal) {
    int middlePiece = (armThickness * 2 + (armThickness - 3)) / 2;
    int validMargin = (armThickness - 1) / 2;


    return (freeVal <= middlePiece + validMargin
            && freeVal >= middlePiece - validMargin)
            || (constrainedVal <= middlePiece + validMargin
              && constrainedVal >= middlePiece - validMargin);
  }
}
