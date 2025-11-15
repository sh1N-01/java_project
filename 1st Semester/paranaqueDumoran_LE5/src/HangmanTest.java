import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HangmanTest 
{
	
	private Hangman game;

    @BeforeEach
    void setUp() {
        game = new Hangman();
        game.startNewGame(); // ensure fresh word
    }

    @Test
    void testWordStartsHidden() {
        String display = game.getDisplayWord();
        assertTrue(display.chars().allMatch(ch -> ch == '*'));
    }

    @Test
    void testCorrectGuessRevealsLetter() {
        String word = game.getWord();
        char firstLetter = word.charAt(0);
        game.guessLetter(firstLetter);
        assertTrue(game.getDisplayWord().contains(String.valueOf(firstLetter)));
    }

    @Test
    void testIncorrectGuessIncreasesMisses() {
        int missesBefore = game.getMisses();
        game.guessLetter('z'); // unlikely to be in word
        assertEquals(missesBefore + 1, game.getMisses());
    }

    @Test
    void testAlreadyGuessedLetterMessage() {
        char c = game.getWord().charAt(0);
        game.guessLetter(c);
        String result = game.guessLetter(c);
        assertTrue(result.contains("already"));
    }

    @Test
    void testWordCompletion() {
        String word = game.getWord();
        for (char c : word.toCharArray()) {
            game.guessLetter(c);
        }
        assertTrue(game.isWordComplete());
    }

}
