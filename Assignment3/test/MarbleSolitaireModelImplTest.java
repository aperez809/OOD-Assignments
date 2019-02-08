import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class MarbleSolitaireModelImplTest {

  @Test
  public void testConstructorNoParam() {
    MarbleSolitaireModelImpl initData = new MarbleSolitaireModelImpl();
    assertEquals("    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O O O _ O O O\n"
                    + "O O O O O O O\n"
                    + "    O O O\n"
                    + "    O O O",
            initData.getGameState());
  }

  @Test
  public void testConstructorRowAndColParamCorrect() {
    MarbleSolitaireModelImpl initData = new MarbleSolitaireModelImpl(2,1);
    assertEquals("    O O O\n"
                   + "    O O O\n"
                   + "O _ O O O O O\n"
                   + "O O O O O O O\n"
                   + "O O O O O O O\n"
                   + "    O O O\n"
                   + "    O O O",
            initData.getGameState());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorRowAndColParamInvalidOOB() {
    new MarbleSolitaireModelImpl(6,6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorRowAndColParamInvalidRowNeg() {
    new MarbleSolitaireModelImpl(-1,6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorRowAndColParamInvalidColNeg() {
    new MarbleSolitaireModelImpl(6,-1);
  }

  @Test
  public void testConstructorArmThicknessCorrect() {
    MarbleSolitaireModelImpl initData = new MarbleSolitaireModelImpl(7);
    assertEquals("            O O O O O O O\n"
                    + "            O O O O O O O\n"
                    + "            O O O O O O O\n"
                    + "            O O O O O O O\n"
                    + "            O O O O O O O\n"
                    + "            O O O O O O O\n"
                    + "O O O O O O O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O _ O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O O O O O O O\n"
                    + "            O O O O O O O\n"
                    + "            O O O O O O O\n"
                    + "            O O O O O O O\n"
                    + "            O O O O O O O\n"
                    + "            O O O O O O O\n"
                    + "            O O O O O O O",
            initData.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorArmThicknessInvalidEven() {
    new MarbleSolitaireModelImpl(8);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorArmThicknessInvalidZero() {
    new MarbleSolitaireModelImpl(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorArmThicknessInvalidNeg() {
    new MarbleSolitaireModelImpl(-5);
  }

  @Test
  public void testConstructorAllPartsCorrect() {
    MarbleSolitaireModelImpl initData = new MarbleSolitaireModelImpl(7, 5, 6);
    assertEquals("            O O O O O O O\n"
                   + "            O O O O O O O\n"
                   + "            O O O O O O O\n"
                   + "            O O O O O O O\n"
                   + "            O O O O O O O\n"
                   + "            _ O O O O O O\n"
                   + "O O O O O O O O O O O O O O O O O O O\n"
                   + "O O O O O O O O O O O O O O O O O O O\n"
                   + "O O O O O O O O O O O O O O O O O O O\n"
                   + "O O O O O O O O O O O O O O O O O O O\n"
                   + "O O O O O O O O O O O O O O O O O O O\n"
                   + "O O O O O O O O O O O O O O O O O O O\n"
                   + "O O O O O O O O O O O O O O O O O O O\n"
                   + "            O O O O O O O\n"
                   + "            O O O O O O O\n"
                   + "            O O O O O O O\n"
                   + "            O O O O O O O\n"
                   + "            O O O O O O O\n"
                   + "            O O O O O O O",
            initData.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorAllPartsInvalidATEven() {
    new MarbleSolitaireModelImpl(8, 2, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorAllPartsInvalidATZero() {
    new MarbleSolitaireModelImpl(0, 2, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorAllPartsInvalidATNeg() {
    new MarbleSolitaireModelImpl(-7, 2, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorAllPartsInvalidRowOOB() {
    new MarbleSolitaireModelImpl(5, 12, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorAllPartsInvalidColOOB() {
    new MarbleSolitaireModelImpl(5, 0, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorAllPartsInvalidRowNeg() {
    new MarbleSolitaireModelImpl(5, -1, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorAllPartsInvalidColNeg() {
    new MarbleSolitaireModelImpl(5, 0, -1);
  }

  @Test
  public void testGetGameState() {
    MarbleSolitaireModelImpl initData = new MarbleSolitaireModelImpl();
    assertEquals("    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O O O _ O O O\n"
                    + "O O O O O O O\n"
                    + "    O O O\n"
                    + "    O O O",
            initData.getGameState());
  }

  @Test
  public void testGetScore() {
    MarbleSolitaireModelImpl initData = new MarbleSolitaireModelImpl();
    assertEquals(32, initData.getScore());
  }

  @Test
  public void testValidMoveLeft() {
    MarbleSolitaireModelImpl initData = new MarbleSolitaireModelImpl();
    initData.move(3, 5, 3,3);
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O _ _ O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", initData.getGameState());
  }

  @Test
  public void testValidMoveRight() {
    MarbleSolitaireModelImpl initData = new MarbleSolitaireModelImpl();
    initData.move(3, 1, 3,3);
    assertEquals("    O O O\n"
           + "    O O O\n"
           + "O O O O O O O\n"
           + "O _ _ O O O O\n"
           + "O O O O O O O\n"
           + "    O O O\n"
           + "    O O O", initData.getGameState());
  }

  @Test
  public void testValidMoveUp() {
    MarbleSolitaireModelImpl initData = new MarbleSolitaireModelImpl();
    initData.move(5, 3, 3,3);
    assertEquals("    O O O\n"
           + "    O O O\n"
           + "O O O O O O O\n"
           + "O O O O O O O\n"
           + "O O O _ O O O\n"
           + "    O _ O\n"
           + "    O O O", initData.getGameState());
  }

  @Test
  public void testValidMoveDown() {
    MarbleSolitaireModelImpl initData = new MarbleSolitaireModelImpl();
    initData.move(1, 3, 3,3);
    assertEquals("    O O O\n"
            + "    O _ O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", initData.getGameState());
  }

  @Test
  public void testMoveFromEdge() {
    MarbleSolitaireModelImpl initData = new MarbleSolitaireModelImpl(4,0);
    initData.move(2, 0, 4,0);
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "_ O O O O O O\n"
            + "_ O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", initData.getGameState());
  }





  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveIllegalStart() {
    MarbleSolitaireModelImpl initData = new MarbleSolitaireModelImpl();
    initData.move(0,0, 0, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveToOccupiedSpace() {
    MarbleSolitaireModelImpl initData = new MarbleSolitaireModelImpl();
    initData.move(3,2,1,2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveOffBoard() {
    MarbleSolitaireModelImpl initData = new MarbleSolitaireModelImpl();
    initData.move(5,1,7,1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveOverEmptySpace() {
    MarbleSolitaireModelImpl initData = new MarbleSolitaireModelImpl();
    initData.move(2,3,4,3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveTooManySpacesRow() {
    MarbleSolitaireModelImpl initData = new MarbleSolitaireModelImpl();
    initData.move(3, 2, 6,2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveTooManySpacesCol() {
    MarbleSolitaireModelImpl initData = new MarbleSolitaireModelImpl();
    initData.move(2, 3, 2,6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveTooManySpacesRowAndCol() {
    MarbleSolitaireModelImpl initData = new MarbleSolitaireModelImpl();
    initData.move(2, 3, 3,6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveDiagonalJump() {
    MarbleSolitaireModelImpl initData = new MarbleSolitaireModelImpl(7);
    initData.move(11, 7, 9,9);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveNeg() {
    MarbleSolitaireModelImpl initData = new MarbleSolitaireModelImpl(7);
    initData.move(-2, -2, 0,-2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveEmptyBoardSpace() {
    MarbleSolitaireModelImpl initData = new MarbleSolitaireModelImpl();
    initData.move(0, 3, 0,1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveMoreThanTwo() {
    MarbleSolitaireModelImpl initData = new MarbleSolitaireModelImpl();
    initData.move(3, 0, 3,3);
  }



}
