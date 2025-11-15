import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TicTacToeTest {
	
	private TicTacToe game;
	
	@BeforeEach
    void setUp() {
        game = new TicTacToe();
    }

    @Test
    void testInitialTurnIsX() {
        assertEquals('X', game.getTurn());
    }

    @Test
    void testAddMoveAndSwitchTurn() {
        assertTrue(game.addMove(0, 0));
        assertEquals('O', game.getTurn()); // turn switches
        assertEquals('X', game.displayBoard().charAt(0)); // X placed
    }

    @Test
    void testWinnerRow() {
        game.addMove(0, 0); // X
        game.addMove(1, 0); // O
        game.addMove(0, 1); // X
        game.addMove(1, 1); // O
        game.addMove(0, 2); // X wins
        assertTrue(game.hasWinner());
        assertEquals('X', game.getWinner());
    }

    @Test
    void testInvalidMoveOnFilledCell() {
        game.addMove(0, 0);
        assertFalse(game.addMove(0, 0)); // cannot place again
    }

    @Test
    void testResetGame() {
        game.addMove(0, 0);
        game.resetGame();
        assertEquals('X', game.getTurn());
        assertEquals(" | | \n | | \n | | \n", game.displayBoard().replaceAll("[^XO|\\n ]", ""));
    }

}
