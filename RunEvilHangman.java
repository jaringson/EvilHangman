package hangman;

import java.io.File;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import hangman.IEvilHangmanGame.GuessAlreadyMadeException;

public class RunEvilHangman {

	public static void main(String[] args) throws GuessAlreadyMadeException {
		IEvilHangmanGame game = new EvilHangmanGame();
		File file = new File(args[0]);
		int wordlength = new Integer(args[1]);
		int numguesses = new Integer(args[2]);
		int numblanks = numguesses;
		game.startGame(file, wordlength);
		
		char[] guesses = new char[numguesses];
		StringBuilder word = new StringBuilder();
		for(int h = 0; h< wordlength; h++){
			word.append("-");
		}
		for(int i = numguesses;i > 0 ;i--){
			System.out.println("You have "+i+" guesses left");
			System.out.print("Used Letters: ");
			for(int j = 0; j < guesses.length; j++){
				System.out.print(guesses[j] + " ");
			}
			System.out.print("\nWord: " + word);
			System.out.print("\nEnter Guess: ");
			Scanner user_input = new Scanner(System.in);
			String guess = user_input.next();
			guesses[Math.abs(i - numguesses)] = guess.charAt(0);
			Set<String> returnSet = game.makeGuess(guess.charAt(0));
			System.out.println(returnSet);
			String sampleword = returnSet.iterator().next();
			//word.delete(0, word.length());
			Boolean lost = true;
			int numtimes = 0;
			
			for(int k = 0; k < sampleword.length();k++){
				if(sampleword.charAt(k) == guess.charAt(0)){
					word.insert(k, guess.charAt(0));
					word.deleteCharAt(k+1);
					numtimes++;
					lost = false;
				}
			}
			
			if(lost){
				System.out.println("Sorry, there is no " + guess.charAt(0)+"'s \n");
				lost = true;
			}
			else{
				System.out.println("Yes, there is "+numtimes+ " "+ guess.charAt(0) + "\n");
				numblanks = numblanks - numtimes;
			}
			if(numblanks == 0){
				System.out.println("You Win!");
				break;
			}
		}
	}
}
