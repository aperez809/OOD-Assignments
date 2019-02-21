import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModelImpl;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModelImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AbstractMarbleSolitaireModelImplTest {

  @Test
  public void testConstructorNoParamImpl() {
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
  public void testConstructorRowAndColParamCorrectImpl() {
    MarbleSolitaireModelImpl initData = new MarbleSolitaireModelImpl(2, 1);
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
  public void testConstructorRowAndColParamInvalidOOBImpl() {
    new MarbleSolitaireModelImpl(6, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorRowAndColParamInvalidRowNegImpl() {
    new MarbleSolitaireModelImpl(-1, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorRowAndColParamInvalidColNegImpl() {
    new MarbleSolitaireModelImpl(6, -1);
  }

  @Test
  public void testConstructorArmThicknessCorrectImpl() {
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
  public void testConstructorArmThicknessInvalidEvenImpl() {
    new MarbleSolitaireModelImpl(8);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorArmThicknessInvalidZeroImpl() {
    new MarbleSolitaireModelImpl(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorArmThicknessInvalidNegImpl() {
    new MarbleSolitaireModelImpl(-5);
  }

  @Test
  public void testConstructorAllPartsCorrectImpl() {
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
  public void testConstructorAllPartsInvalidATEvenImpl() {
    new MarbleSolitaireModelImpl(8, 2, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorAllPartsInvalidATZeroImpl() {
    new MarbleSolitaireModelImpl(0, 2, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorAllPartsInvalidATNegImpl() {
    new MarbleSolitaireModelImpl(-7, 2, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorAllPartsInvalidRowOOBImpl() {
    new MarbleSolitaireModelImpl(5, 12, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorAllPartsInvalidColOOBImpl() {
    new MarbleSolitaireModelImpl(5, 0, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorAllPartsInvalidRowNegImpl() {
    new MarbleSolitaireModelImpl(5, -1, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorAllPartsInvalidColNegImpl() {
    new MarbleSolitaireModelImpl(5, 0, -1);
  }

  @Test
  public void testGetGameStateImpl() {
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
  public void testGetScoreImpl() {
    MarbleSolitaireModelImpl initData = new MarbleSolitaireModelImpl();
    assertEquals(32, initData.getScore());
  }

  @Test
  public void testValidMoveLeftImpl() {
    MarbleSolitaireModelImpl initData = new MarbleSolitaireModelImpl();
    initData.move(3, 5, 3, 3);
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O _ _ O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", initData.getGameState());
  }

  @Test
  public void testValidMoveRightImpl() {
    MarbleSolitaireModelImpl initData = new MarbleSolitaireModelImpl();
    initData.move(3, 1, 3, 3);
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", initData.getGameState());
  }

  @Test
  public void testValidMoveUpImpl() {
    MarbleSolitaireModelImpl initData = new MarbleSolitaireModelImpl();
    initData.move(5, 3, 3, 3);
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "    O _ O\n"
            + "    O O O", initData.getGameState());
  }

  @Test
  public void testValidMoveDownImpl() {
    MarbleSolitaireModelImpl initData = new MarbleSolitaireModelImpl();
    initData.move(1, 3, 3, 3);
    assertEquals("    O O O\n"
            + "    O _ O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", initData.getGameState());
  }

  @Test
  public void testMoveFromEdgeImpl() {
    MarbleSolitaireModelImpl initData = new MarbleSolitaireModelImpl(4, 0);
    initData.move(2, 0, 4, 0);
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "_ O O O O O O\n"
            + "_ O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", initData.getGameState());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveIllegalStartImpl() {
    MarbleSolitaireModelImpl initData = new MarbleSolitaireModelImpl();
    initData.move(0, 0, 0, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveToOccupiedSpaceImpl() {
    MarbleSolitaireModelImpl initData = new MarbleSolitaireModelImpl();
    initData.move(3, 2, 1, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveOffBoardImpl() {
    MarbleSolitaireModelImpl initData = new MarbleSolitaireModelImpl();
    initData.move(5, 1, 7, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveOverEmptySpaceImpl() {
    MarbleSolitaireModelImpl initData = new MarbleSolitaireModelImpl();
    initData.move(2, 3, 4, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveTooManySpacesRowImpl() {
    MarbleSolitaireModelImpl initData = new MarbleSolitaireModelImpl();
    initData.move(3, 2, 6, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveTooManySpacesColImpl() {
    MarbleSolitaireModelImpl initData = new MarbleSolitaireModelImpl();
    initData.move(2, 3, 2, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveTooManySpacesRowAndColImpl() {
    MarbleSolitaireModelImpl initData = new MarbleSolitaireModelImpl();
    initData.move(2, 3, 3, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveDiagonalJumpImpl() {
    MarbleSolitaireModelImpl initData = new MarbleSolitaireModelImpl(7);
    initData.move(11, 7, 9, 9);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveNegImpl() {
    MarbleSolitaireModelImpl initData = new MarbleSolitaireModelImpl(7);
    initData.move(-2, -2, 0, -2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveEmptyBoardSpaceImpl() {
    MarbleSolitaireModelImpl initData = new MarbleSolitaireModelImpl();
    initData.move(0, 3, 0, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveMoreThanTwoImpl() {
    MarbleSolitaireModelImpl initData = new MarbleSolitaireModelImpl();
    initData.move(3, 0, 3, 3);
  }

  @Test
  public void testConstructorNoParamEuro() {
    MarbleSolitaireModel initData = new EuropeanSolitaireModelImpl();
    assertEquals("    O O O\n" +
                    "  O O O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "  O O O O O\n" +
                    "    O O O",
            initData.getGameState());
  }

  @Test
  public void testConstructorRowAndColParamCorrectEuro() {
    MarbleSolitaireModel initData = new EuropeanSolitaireModelImpl(2, 1);
    assertEquals("    O O O\n" +
                    "  O O O O O\n" +
                    "O _ O O O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "  O O O O O\n" +
                    "    O O O",
            initData.getGameState());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorRowAndColParamInvalidOOBEuro() {
    new EuropeanSolitaireModelImpl(6, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorRowAndColParamInvalidRowNegEuro() {
    new EuropeanSolitaireModelImpl(-1, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorRowAndColParamInvalidColNegEuro() {
    new EuropeanSolitaireModelImpl(6, -1);
  }

  @Test
  public void testConstructorArmThicknessCorrectEuro() {
    MarbleSolitaireModel initData = new EuropeanSolitaireModelImpl(7);
    assertEquals("            O O O O O O O\n" +
                    "          O O O O O O O O O\n" +
                    "        O O O O O O O O O O O\n" +
                    "      O O O O O O O O O O O O O\n" +
                    "    O O O O O O O O O O O O O O O\n" +
                    "  O O O O O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O _ O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O O O O O O O\n" +
                    "  O O O O O O O O O O O O O O O O O\n" +
                    "    O O O O O O O O O O O O O O O\n" +
                    "      O O O O O O O O O O O O O\n" +
                    "        O O O O O O O O O O O\n" +
                    "          O O O O O O O O O\n" +
                    "            O O O O O O O",
            initData.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorArmThicknessInvalidEvenEuro() {
    new EuropeanSolitaireModelImpl(8);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorArmThicknessInvalidZeroEuro() {
    new EuropeanSolitaireModelImpl(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorArmThicknessInvalidNegEuro() {
    new EuropeanSolitaireModelImpl(-5);
  }

  @Test
  public void testConstructorAllPartsCorrectEuro() {
    MarbleSolitaireModel initData = new EuropeanSolitaireModelImpl(7, 5, 6);
    assertEquals("            O O O O O O O\n" +
                    "          O O O O O O O O O\n" +
                    "        O O O O O O O O O O O\n" +
                    "      O O O O O O O O O O O O O\n" +
                    "    O O O O O O O O O O O O O O O\n" +
                    "  O O O O O _ O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O O O O O O O\n" +
                    "  O O O O O O O O O O O O O O O O O\n" +
                    "    O O O O O O O O O O O O O O O\n" +
                    "      O O O O O O O O O O O O O\n" +
                    "        O O O O O O O O O O O\n" +
                    "          O O O O O O O O O\n" +
                    "            O O O O O O O",
            initData.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorAllPartsInvalidATEvenEuro() {
    new EuropeanSolitaireModelImpl(8, 2, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorAllPartsInvalidATZeroEuro() {
    new EuropeanSolitaireModelImpl(0, 2, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorAllPartsInvalidATNegEuro() {
    new EuropeanSolitaireModelImpl(-7, 2, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorAllPartsInvalidRowOOBEuro() {
    new EuropeanSolitaireModelImpl(5, 12, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorAllPartsInvalidColOOBEuro() {
    new EuropeanSolitaireModelImpl(5, 0, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorAllPartsInvalidRowNegEuro() {
    new EuropeanSolitaireModelImpl(5, -1, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorAllPartsInvalidColNegEuro() {
    new EuropeanSolitaireModelImpl(5, 0, -1);
  }

  @Test
  public void testGetGameStateEuro() {
    MarbleSolitaireModel initData = new EuropeanSolitaireModelImpl();
    assertEquals("    O O O\n" +
                    "  O O O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "  O O O O O\n" +
                    "    O O O",
            initData.getGameState());
  }

  @Test
  public void testGetScoreEuro() {
    MarbleSolitaireModel initData = new EuropeanSolitaireModelImpl();
    assertEquals(36, initData.getScore());
  }

  @Test
  public void testValidMoveLeftEuro() {
    MarbleSolitaireModel initData = new EuropeanSolitaireModelImpl();
    initData.move(3, 5, 3, 3);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O _ _ O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", initData.getGameState());
  }

  @Test
  public void testValidMoveRightEuro() {
    MarbleSolitaireModel initData = new EuropeanSolitaireModelImpl();
    initData.move(3, 1, 3, 3);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", initData.getGameState());
  }

  @Test
  public void testValidMoveUpEuro() {
    MarbleSolitaireModel initData = new EuropeanSolitaireModelImpl();
    initData.move(5, 3, 3, 3);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "  O O _ O O\n" +
            "    O O O", initData.getGameState());
  }

  @Test
  public void testValidMoveDownEuro() {
    MarbleSolitaireModel initData = new EuropeanSolitaireModelImpl();
    initData.move(1, 3, 3, 3);
    assertEquals("    O O O\n" +
            "  O O _ O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", initData.getGameState());
  }

  @Test
  public void testMoveFromEdgeEuro() {
    MarbleSolitaireModel initData = new EuropeanSolitaireModelImpl(4, 0);
    initData.move(2, 0, 4, 0);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "_ O O O O O O\n" +
            "_ O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", initData.getGameState());
  }

  @Test
  public void testMoveIntoCornerEuro() {
    MarbleSolitaireModel initData = new EuropeanSolitaireModelImpl(1, 1);
    initData.move(3, 1, 1, 1);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O _ O O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", initData.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveIllegalStartEuro() {
    MarbleSolitaireModel initData = new EuropeanSolitaireModelImpl();
    initData.move(0, 0, 0, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveToOccupiedSpaceEuro() {
    MarbleSolitaireModel initData = new EuropeanSolitaireModelImpl();
    initData.move(3, 2, 1, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveOffBoardEuro() {
    MarbleSolitaireModel initData = new EuropeanSolitaireModelImpl();
    initData.move(5, 1, 7, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveOverEmptySpaceEuro() {
    MarbleSolitaireModel initData = new EuropeanSolitaireModelImpl();
    initData.move(2, 3, 4, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveTooManySpacesRowEuro() {
    MarbleSolitaireModel initData = new EuropeanSolitaireModelImpl();
    initData.move(3, 2, 6, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveTooManySpacesColEuro() {
    MarbleSolitaireModel initData = new EuropeanSolitaireModelImpl();
    initData.move(2, 3, 2, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveTooManySpacesRowAndColEuro() {
    MarbleSolitaireModel initData = new EuropeanSolitaireModelImpl();
    initData.move(2, 3, 3, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveDiagonalJumpEuro() {
    MarbleSolitaireModel initData = new EuropeanSolitaireModelImpl(7);
    initData.move(11, 7, 9, 9);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveNegEuro() {
    MarbleSolitaireModel initData = new EuropeanSolitaireModelImpl(7);
    initData.move(-2, -2, 0, -2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveEmptyBoardSpaceEuro() {
    MarbleSolitaireModel initData = new EuropeanSolitaireModelImpl();
    initData.move(0, 3, 0, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveMoreThanTwoEuro() {
    MarbleSolitaireModel initData = new EuropeanSolitaireModelImpl();
    initData.move(3, 0, 3, 3);
  }

  @Test
  public void testConstructorNoParamTri() {
    MarbleSolitaireModel initData = new TriangleSolitaireModelImpl();
    assertEquals("    _\n" +
                    "   O O\n" +
                    "  O O O\n" +
                    " O O O O\n" +
                    "O O O O O",
            initData.getGameState());
  }

  @Test
  public void testConstructorRowAndColParamCorrectTri() {
    MarbleSolitaireModel initData = new TriangleSolitaireModelImpl(2, 1);
    assertEquals("    O\n" +
                    "   O O\n" +
                    "  O _ O\n" +
                    " O O O O\n" +
                    "O O O O O",
            initData.getGameState());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorRowAndColParamInvalidOOBTri() {
    new TriangleSolitaireModelImpl(6, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorRowAndColParamInvalidRowNegTri() {
    new TriangleSolitaireModelImpl(-1, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorRowAndColParamInvalidColNegTri() {
    new TriangleSolitaireModelImpl(6, -1);
  }

  @Test
  public void testConstructorArmThicknessCorrectTri() {
    MarbleSolitaireModel initData = new TriangleSolitaireModelImpl(7);
    assertEquals("      _\n" +
                    "     O O\n" +
                    "    O O O\n" +
                    "   O O O O\n" +
                    "  O O O O O\n" +
                    " O O O O O O\n" +
                    "O O O O O O O",
            initData.getGameState());
  }

  @Test
  public void testConstructorArmThicknessValidEvenTri() {
    MarbleSolitaireModel initData = new TriangleSolitaireModelImpl(8);
    assertEquals(initData.getGameState(), "       _\n" +
            "      O O\n" +
            "     O O O\n" +
            "    O O O O\n" +
            "   O O O O O\n" +
            "  O O O O O O\n" +
            " O O O O O O O\n" +
            "O O O O O O O O");
  }

  @Test
  public void testIsGameOverTrue() {
    MarbleSolitaireModel initData = new TriangleSolitaireModelImpl(1);
    assertEquals(true, initData.isGameOver());
  }


  @Test
  public void testIsGameOverFalse() {
    MarbleSolitaireModel initData = new TriangleSolitaireModelImpl();
    assertEquals(false, initData.isGameOver());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorArmThicknessInvalidZeroTri() {
    new TriangleSolitaireModelImpl(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorArmThicknessInvalidNegTri() {
    new TriangleSolitaireModelImpl(-5);
  }

  @Test
  public void testConstructorAllPartsCorrectTri() {
    MarbleSolitaireModel initData = new TriangleSolitaireModelImpl(7, 3, 2);
    assertEquals("      O\n" +
                    "     O O\n" +
                    "    O O O\n" +
                    "   O O _ O\n" +
                    "  O O O O O\n" +
                    " O O O O O O\n" +
                    "O O O O O O O",
            initData.getGameState());
  }

  @Test
  public void testConstructorAllPartsValidATEvenTri() {
    MarbleSolitaireModel initData = new TriangleSolitaireModelImpl(8, 2, 1);
    assertEquals(initData.getGameState(), "       O\n" +
            "      O O\n" +
            "     O _ O\n" +
            "    O O O O\n" +
            "   O O O O O\n" +
            "  O O O O O O\n" +
            " O O O O O O O\n" +
            "O O O O O O O O");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorAllPartsInvalidATZeroTri() {
    new TriangleSolitaireModelImpl(0, 2, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorAllPartsInvalidATNegTri() {
    new TriangleSolitaireModelImpl(-7, 2, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorAllPartsInvalidRowOOBTri() {
    new TriangleSolitaireModelImpl(5, 12, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorAllPartsInvalidColOOBTri() {
    new TriangleSolitaireModelImpl(5, 0, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorAllPartsInvalidRowNegTri() {
    new TriangleSolitaireModelImpl(5, -1, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorAllPartsInvalidColNegTri() {
    new TriangleSolitaireModelImpl(5, 0, -1);
  }

  @Test
  public void testGetGameStateTri() {
    MarbleSolitaireModel initData = new TriangleSolitaireModelImpl();
    initData.move(2,0,0,0);
    assertEquals("    O\n" +
                    "   _ O\n" +
                    "  _ O O\n" +
                    " O O O O\n" +
                    "O O O O O",
            initData.getGameState());
  }

  @Test
  public void testGetScoreTri() {
    MarbleSolitaireModel initData = new TriangleSolitaireModelImpl();
    assertEquals(14, initData.getScore());
  }




  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveBadStartTri() {
    MarbleSolitaireModel initData = new TriangleSolitaireModelImpl(4,2);
    initData.move(5, 2, 3, 2);
  }

  @Test
  public void testValidMoveLeftTri() {
    MarbleSolitaireModel initData = new TriangleSolitaireModelImpl(4,2);
    initData.move(4, 4, 4, 2);
    assertEquals("    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O _ _", initData.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveLeftTri() {
    MarbleSolitaireModel initData = new TriangleSolitaireModelImpl();
    initData.move(3, 5, 3, 3);
  }

  @Test
  public void testValidMoveRightTri() {
    MarbleSolitaireModel initData = new TriangleSolitaireModelImpl(3,2);
    initData.move(3, 0, 3, 2);
    assertEquals("    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " _ _ O O\n" +
            "O O O O O", initData.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveRightTri() {
    MarbleSolitaireModel initData = new TriangleSolitaireModelImpl();
    initData.move(3, 1, 3, 3);
  }

  @Test
  public void testValidMoveUpTri() {
    MarbleSolitaireModel initData = new TriangleSolitaireModelImpl(2, 0);
    initData.move(4, 0, 2, 0);
    assertEquals("    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " _ O O O\n" +
            "_ O O O O", initData.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveUpTri() {
    MarbleSolitaireModel initData = new TriangleSolitaireModelImpl();
    initData.move(5, 3, 3, 3);
  }

  @Test
  public void testValidMoveDownTri() {
    MarbleSolitaireModel initData = new TriangleSolitaireModelImpl(4,2);
    initData.move(2, 2, 4, 2);
    assertEquals(
            "    O\n" +
            "   O O\n" +
            "  O O _\n" +
            " O O _ O\n" +
            "O O O O O", initData.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveDownTri() {
    MarbleSolitaireModel initData = new TriangleSolitaireModelImpl();
    initData.move(1, 3, 3, 3);
  }

  @Test
  public void testMoveDownFromEdgeTri() {
    MarbleSolitaireModel initData = new TriangleSolitaireModelImpl(4, 0);
    initData.move(2, 0, 4, 0);
    assertEquals("    O\n" +
            "   O O\n" +
            "  _ O O\n" +
            " _ O O O\n" +
            "O O O O O", initData.getGameState());
  }

  @Test
  public void testMoveDiagonalTri() {
    MarbleSolitaireModel initData = new TriangleSolitaireModelImpl(4, 4);
    initData.move(2, 2, 4, 4);
    assertEquals("    O\n" +
            "   O O\n" +
            "  O O _\n" +
            " O O O _\n" +
            "O O O O O", initData.getGameState());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveIllegalStartTri() {
    MarbleSolitaireModel initData = new TriangleSolitaireModelImpl();
    initData.move(0, 0, 0, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveToOccupiedSpaceTri() {
    MarbleSolitaireModel initData = new TriangleSolitaireModelImpl(5);
    initData.move(0,0,0,2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveOffBoardTri() {
    MarbleSolitaireModel initData = new TriangleSolitaireModelImpl();
    initData.move(5, 1, 7, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveOverEmptySpaceTri() {
    MarbleSolitaireModel initData = new TriangleSolitaireModelImpl();
    initData.move(2, 3, 4, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveTooManySpacesRowTri() {
    MarbleSolitaireModel initData = new TriangleSolitaireModelImpl();
    initData.move(3, 2, 6, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveTooManySpacesColTri() {
    MarbleSolitaireModel initData = new TriangleSolitaireModelImpl();
    initData.move(2, 3, 2, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveTooManySpacesRowAndColTri() {
    MarbleSolitaireModel initData = new TriangleSolitaireModelImpl();
    initData.move(2, 3, 3, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveDiagonalJumpTri() {
    MarbleSolitaireModel initData = new TriangleSolitaireModelImpl(7);
    initData.move(11, 7, 9, 9);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveNegTri() {
    MarbleSolitaireModel initData = new TriangleSolitaireModelImpl(7);
    initData.move(-2, -2, 0, -2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveEmptyBoardSpaceTri() {
    MarbleSolitaireModel initData = new TriangleSolitaireModelImpl(3,1);
    initData.move(2, 1, 4, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveMoreThanTwoTri() {
    MarbleSolitaireModel initData = new TriangleSolitaireModelImpl();
    initData.move(3, 0, 3, 3);
  }

  @Test
  public void testGameIsOverAT3Tri() {
    MarbleSolitaireModel initData = new TriangleSolitaireModelImpl(3);
    initData.move(2,0,0,0);
    initData.move(2,2,2,0);
    initData.move(0,0,2,2);
    assertEquals(true, initData.isGameOver());
  }

  @Test
  public void testGameIsOverAT5Tri() {
    MarbleSolitaireModel initData = new TriangleSolitaireModelImpl();
    initData.move(2,0,0,0);
    initData.move(2,2,2,0);
    initData.move(0,0,2,2);
    assertEquals(false, initData.isGameOver());
  }

}
