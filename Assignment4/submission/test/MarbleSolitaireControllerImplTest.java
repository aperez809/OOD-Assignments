import cs3500.marblesolitaire.controller.MarbleSolitaireController;

import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.Reader;

import java.io.StringReader;


public class MarbleSolitaireControllerImplTest {


  @Test (expected = IllegalArgumentException.class)
  public void testNullArgToCntrlConstructor() {
    new MarbleSolitaireControllerImpl(null, null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNullRdArgToCntrlConstructor() {
    new MarbleSolitaireControllerImpl(null, new StringBuilder());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNullApArgToCntrlConstructor() {
    new MarbleSolitaireControllerImpl(new StringReader("3 3 3 3"), null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNullModelPassed() {
    Reader in = new StringReader("3 4 3 q");
    StringBuilder out = new StringBuilder();
    MarbleSolitaireController initControl = new MarbleSolitaireControllerImpl(in, out);
    initControl.playGame(null);
  }

  @Test (expected = IllegalStateException.class)
  public void testTooFewCoordsPassedSingleMove() {
    Reader in = new StringReader("3 4 3");
    StringBuilder out = new StringBuilder();
    MarbleSolitaireController initControl = new MarbleSolitaireControllerImpl(in, out);
    MarbleSolitaireModel initModel = new MarbleSolitaireModelImpl();
    initControl.playGame(initModel);
  }


  @Test (expected = IllegalStateException.class)
  public void testTooFewCoordsPassedMultipleMoves() {
    Reader in = new StringReader("3 4 3 5 8 9 8 1 3 5");
    StringBuilder out = new StringBuilder();
    MarbleSolitaireController initControl = new MarbleSolitaireControllerImpl(in, out);
    MarbleSolitaireModel initModel = new MarbleSolitaireModelImpl();
    initControl.playGame(initModel);
  }



  @Test
  public void testInvalidMoveAttempted() {
    Reader in = new StringReader("3 4 3 5");
    StringBuilder out = new StringBuilder();
    MarbleSolitaireController initControl = new MarbleSolitaireControllerImpl(in, out);
    MarbleSolitaireModel initModel = new MarbleSolitaireModelImpl();
    initControl.playGame(initModel);
    assertEquals("Invalid move. Play again. Moves must be made to an"
            + " unoccupied space from and over a single occupied one in either a"
            + " vertical or horizontal direction.", out.toString());
  }

  @Test
  public void testEndGameCapitalQ() {
    Reader in = new StringReader("Q 4 3 4");
    StringBuilder out = new StringBuilder();
    MarbleSolitaireController initControl = new MarbleSolitaireControllerImpl(
            in, out);
    assertEquals("", out.toString());
  }

  @Test
  public void testEndGameFromRow() {
    Reader in = new StringReader("q 4 3 4");
    StringBuilder out = new StringBuilder();
    MarbleSolitaireController initControl = new MarbleSolitaireControllerImpl(
            in, out);

    MarbleSolitaireModel initModel = new MarbleSolitaireModelImpl();

    initControl.playGame(initModel);
    assertEquals("    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\nO O O O O O O\n"
            + "    O O O\n    O O O\nScore: 32\n"
            + "Game quit!\nState of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n", out.toString());

  }

  @Test
  public void testEndGameFromCol() {
    Reader in = new StringReader("3 q 3 4");
    StringBuilder out = new StringBuilder();
    MarbleSolitaireController initControl = new MarbleSolitaireControllerImpl(
            in, out);

    MarbleSolitaireModel initModel = new MarbleSolitaireModelImpl();

    initControl.playGame(initModel);
    assertEquals("    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\nO O O O O O O\n"
            + "    O O O\n    O O O\nScore: 32\n"
            + "Game quit!\nState of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n", out.toString());

  }

  @Test
  public void testEndGameToRow() {
    Reader in = new StringReader("3 4 q 4");
    StringBuilder out = new StringBuilder();
    MarbleSolitaireController initControl = new MarbleSolitaireControllerImpl(
            in, out);

    MarbleSolitaireModel initModel = new MarbleSolitaireModelImpl();

    initControl.playGame(initModel);
    assertEquals("    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\nO O O O O O O\n"
            + "    O O O\n    O O O\nScore: 32\n"
            + "Game quit!\nState of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n", out.toString());

  }

  @Test
  public void testEndGameToCol() {
    Reader in = new StringReader("3 4 3 q");
    StringBuilder out = new StringBuilder();
    MarbleSolitaireController initControl = new MarbleSolitaireControllerImpl(
            in, out);

    MarbleSolitaireModel initModel = new MarbleSolitaireModelImpl();

    initControl.playGame(initModel);
    assertEquals("    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\n"
            + "O O O O O O O\n    O O O\n    O O O\nScore: 32\n"
            + "Game quit!\nState of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n", out.toString());

  }

  @Test
  public void testEndGameAfterMove() {
    Reader in = new StringReader("2 4 4 4 q");
    StringBuilder out = new StringBuilder();
    MarbleSolitaireController initControl = new MarbleSolitaireControllerImpl(
            in, out);

    MarbleSolitaireModel initModel = new MarbleSolitaireModelImpl();

    initControl.playGame(initModel);
    assertEquals("    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\nO O O O O O O\n"
            + "    O O O\n    O O O\nScore: 32\n    O O O\n    O _ O"
            + "\nO O O _ O O O\nO O O O O O O\n"
            + "O O O O O O O\n    O O O\n    O O O\nScore: 31\n"
            + "Game quit!\nState of game when quit:\n"
            + "    O O O\n"
            + "    O _ O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 31\n", out.toString());

  }

  @Test
  public void testValidMoveVanilla() {
    Reader in = new StringReader("2 4 4 4");
    StringBuilder out = new StringBuilder();
    MarbleSolitaireController initControl = new MarbleSolitaireControllerImpl(
            in, out);

    MarbleSolitaireModel initModel = new MarbleSolitaireModelImpl();

    initControl.playGame(initModel);
    assertEquals("    O O O\n"
            + "    O _ O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", out.toString());
  }

  @Test
  public void testValidMoveHandleInvalidInput() {
    Reader in = new StringReader("2 4 -1 4 4 q");
    StringBuilder out = new StringBuilder();
    MarbleSolitaireController initControl = new MarbleSolitaireControllerImpl(
            in, out);

    MarbleSolitaireModel initModel = new MarbleSolitaireModelImpl();

    initControl.playGame(initModel);
    assertEquals("    O O O\n"
            + "    O _ O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", initModel.getGameState());
  }

  @Test
  public void testValidMoveWithNewlineDelimInputs() {
    Reader in = new StringReader("2\n4\n-1\n4\n4\nq");
    StringBuilder out = new StringBuilder();
    MarbleSolitaireController initControl = new MarbleSolitaireControllerImpl(
            in, out);

    MarbleSolitaireModel initModel = new MarbleSolitaireModelImpl();

    initControl.playGame(initModel);
    assertEquals("    O O O\n"
            + "    O _ O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", initModel.getGameState());
  }

  @Test
  public void testEndGameNoMoreMoves() {
    Reader in = new StringReader("2 4 4 4 "
            + "5 4 3 4 "
            + "7 4 5 4 "
            + "4 6 4 4 "
            + "6 5 4 5 "
            + "3 5 5 5 "
            + "1 5 3 5 "
            + "4 3 4 5 "
            + "4 5 2 5 "
            + "4 1 4 3 "
            + "3 3 3 5 "
            + "3 5 1 5 "
            + "3 7 3 5 "
            + "5 7 3 7 "
            + "5 5 5 7 "
            + "5 3 5 5 "
            + "7 3 5 3 "
            + "5 2 5 4 "
            + "5 4 5 6 "
            + "5 7 5 5 "
            + "3 1 3 3 "
            + "3 3 5 3 "
            + "1 3 3 3 "
            + "1 5 1 3 ");
    StringBuilder out = new StringBuilder();
    MarbleSolitaireController initControl = new MarbleSolitaireControllerImpl(
            in, out);

    MarbleSolitaireModel initModel = new MarbleSolitaireModelImpl();

    initControl.playGame(initModel);
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "    O O O\n"
            + "    O _ O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 31\n"
            + "    O O O\n    O _ O\nO O O O O O O\nO O O _ O O O\n"
            + "O O O _ O O O\n    O O O\n    O O O\n"
            + "Score: 30\n"
            + "    O O O\n    O _ O\nO O O O O O O\nO O O _ O O O\n"
            + "O O O O O O O\n    O _ O\n    O _ O\n"
            + "Score: 29\n"
            + "    O O O\n    O _ O\nO O O O O O O\nO O O O _ _ O\n"
            + "O O O O O O O\n    O _ O\n    O _ O\n"
            + "Score: 28\n"
            + "    O O O\n    O _ O\nO O O O O O O\nO O O O O _ O\n"
            + "O O O O _ O O\n    O _ _\n    O _ O\n"
            + "Score: 27\n"
            + "    O O O\n    O _ O\nO O O O _ O O\nO O O O _ _ O\n"
            + "O O O O O O O\n    O _ _\n    O _ O\n"
            + "Score: 26\n"
            + "    O O _\n    O _ _\nO O O O O O O\nO O O O _ _ O\n"
            + "O O O O O O O\n    O _ _\n    O _ O\n"
            + "Score: 25\n"
            + "    O O _\n    O _ _\nO O O O O O O\nO O _ _ O _ O\n"
            + "O O O O O O O\n    O _ _\n    O _ O\n"
            + "Score: 24\n"
            + "    O O _\n    O _ O\nO O O O _ O O\nO O _ _ _ _ O\n"
            + "O O O O O O O\n    O _ _\n    O _ O\n"
            + "Score: 23\n"
            + "    O O _\n    O _ O\nO O O O _ O O\n_ _ O _ _ _ O\n"
            + "O O O O O O O\n    O _ _\n    O _ O\n"
            + "Score: 22\n"
            + "    O O _\n    O _ O\nO O _ _ O O O\n_ _ O _ _ _ O\n"
            + "O O O O O O O\n    O _ _\n    O _ O\n"
            + "Score: 21\n"
            + "    O O O\n    O _ _\nO O _ _ _ O O\n_ _ O _ _ _ O\n"
            + "O O O O O O O\n    O _ _\n    O _ O\n"
            + "Score: 20\n"
            + "    O O O\n    O _ _\nO O _ _ O _ _\n_ _ O _ _ _ O\n"
            + "O O O O O O O\n    O _ _\n    O _ O\n"
            + "Score: 19\n"
            + "    O O O\n    O _ _\nO O _ _ O _ O\n_ _ O _ _ _ _\n"
            + "O O O O O O _\n    O _ _\n    O _ O\n"
            + "Score: 18\n"
            + "    O O O\n    O _ _\nO O _ _ O _ O\n_ _ O _ _ _ _\n"
            + "O O O O _ _ O\n    O _ _\n    O _ O\n"
            + "Score: 17\n"
            + "    O O O\n    O _ _\nO O _ _ O _ O\n_ _ O _ _ _ _\n"
            + "O O _ _ O _ O\n    O _ _\n    O _ O\n"
            + "Score: 16\n"
            + "    O O O\n    O _ _\nO O _ _ O _ O\n_ _ O _ _ _ _\n"
            + "O O O _ O _ O\n    _ _ _\n    _ _ O\n"
            + "Score: 15\n"
            + "    O O O\n    O _ _\nO O _ _ O _ O\n_ _ O _ _ _ _\n"
            + "O _ _ O O _ O\n    _ _ _\n    _ _ O\n"
            + "Score: 14\n"
            + "    O O O\n    O _ _\nO O _ _ O _ O\n_ _ O _ _ _ _\n"
            + "O _ _ _ _ O O\n    _ _ _\n    _ _ O\n"
            + "Score: 13\n"
            + "    O O O\n    O _ _\nO O _ _ O _ O\n_ _ O _ _ _ _\n"
            + "O _ _ _ O _ _\n    _ _ _\n    _ _ O\n"
            + "Score: 12\n"
            + "    O O O\n    O _ _\n_ _ O _ O _ O\n_ _ O _ _ _ _\n"
            + "O _ _ _ O _ _\n    _ _ _\n    _ _ O\n"
            + "Score: 11\n"
            + "    O O O\n    O _ _\n_ _ _ _ O _ O\n_ _ _ _ _ _ _\n"
            + "O _ O _ O _ _\n    _ _ _\n    _ _ O\n"
            + "Score: 10\n"
            + "    _ O O\n    _ _ _\n_ _ O _ O _ O\n_ _ _ _ _ _ _\n"
            + "O _ O _ O _ _\n    _ _ _\n    _ _ O\n"
            + "Score: 9\n"
            + "Game over!\n    O _ _\n    _ _ _\n_ _ O _ O _ O\n_ _ _ _ _ _ _\n"
            + "O _ O _ O _ _\n    _ _ _\n    _ _ O\n"
            + "Score: 8\n", out.toString());
  }
}
