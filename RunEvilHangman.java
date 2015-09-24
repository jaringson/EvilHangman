package hangman;

import java.io.File;

import hangman.IEvilHangmanGame.GuessAlreadyMadeException;

public class RunEvilHangman {

	public static void main(String[] args) throws GuessAlreadyMadeException {
		IEvilHangmanGame game = new EvilHangmanGame();
		File file = new File(args[0]);
		int wordlength = new Integer(args[1]);
		game.startGame(file, wordlength);
		System.out.println(game.makeGuess('a'));
	}
}
