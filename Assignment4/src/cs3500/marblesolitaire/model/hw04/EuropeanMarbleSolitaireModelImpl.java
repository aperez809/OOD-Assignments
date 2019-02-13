package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.Cell;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

import java.util.ArrayList;

public class EuropeanMarbleSolitaireModelImpl
        extends AbstractMarbleSolitaireModel
        implements MarbleSolitaireModel {


  /**
   * Constructs a {@code MarbleSolitaireModelImpl} object
   * with a default value of 3 for {@code armThickness}, {@code sRow},
   * and {@code sCol}.
   */

  public EuropeanMarbleSolitaireModelImpl() {
    super();
  }

  public EuropeanMarbleSolitaireModelImpl(int sRow, int sCol) {
    super(sRow, sCol);
  }

  public EuropeanMarbleSolitaireModelImpl(int armThickness) {
    super(armThickness);
  }

  public EuropeanMarbleSolitaireModelImpl(int armThickness, int sRow, int sCol) {
    super(armThickness, sRow, sCol);
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


//}
