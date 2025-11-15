import java.util.ArrayList;
import java.util.Random;

public class Hangman {
	
	private String[] words = {"program", "java", "arrays", "object", "school", "hangman", "university", "copilot", "engineering", "tictactoe"};
	private String currentWord;
	private char[] displayWord;
	private int misses;
	private ArrayList<Character> guessedLetters;
	
	public Hangman() {
		startNewGame();
	}
	
	public void startNewGame() {
		Random rand = new Random();
		currentWord = words[rand.nextInt(words.length)];
		displayWord = new char[currentWord.length()];
		for (int i = 0; i < displayWord.length; i++) displayWord[i] = '*';
		misses = 0;
		guessedLetters = new ArrayList<>();
	}
	
	public String getDisplayWord() {
		return new String(displayWord);
	}
	
	public int getMisses() {
		return misses;
	}
	
	public boolean isWordComplete() {
		return currentWord.equals(getDisplayWord());
	}
	
	public String guessLetter(char c) {
		c = Character.toLowerCase(c);
		if (guessedLetters.contains(c)) {
			return c + " is already guessed.";
		}
		guessedLetters.add(c);
		
		boolean found = false;
		for (int i = 0; i < currentWord.length(); i++) {
			if (currentWord.charAt(i) == c) {
				displayWord[i] = c;
				found = true;
			}
		}
		
		if (!found) {
			misses++;
			return c + " is not in the word.";
		}
		return "Good guess!";
	}
	
	public String getWord() {
		return currentWord;
	}
	
}
