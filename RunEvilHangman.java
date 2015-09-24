package hangman;

import java.io.File;

public class RunEvilHangman {

	public static void main(String[] args) {
		IEvilHangmanGame game = new EvilHangmanGame();
		File file = args[0];
		game.startGame(args[0], args[1]);

	}

}
