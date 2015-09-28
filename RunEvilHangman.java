package hangman;

import java.io.File;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import hangman.IEvilHangmanGame.GuessAlreadyMadeException;

public class RunEvilHangman {

	private static int wordlength;
	private static int numguesses;
	private static int numblanks;
	private static File file;
	static void rungame(String fileStr, String length, String blanks) throws GuessAlreadyMadeException{
		IEvilHangmanGame game = new EvilHangmanGame();
		file = new File(fileStr);
		wordlength = new Integer(length);
		numguesses = new Integer(blanks);
		numblanks = wordlength;
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
			Boolean guessing = true;
			String guess = "";
			while(guessing){
				Scanner user_input = new Scanner(System.in);
				guess = user_input.next();
				
				for(int g= 0; g <guesses.length; g++){
					if(guess.length() != 1 || !guess.matches("[a-z]")){
						System.out.println("Invalid Input: " + guess);
						guessing = true;
						break;
					}
					if(guess.charAt(0) == guesses[g]){
						System.out.println("Already Guessed " + guess.charAt(0) + " Try Again");
						guessing=true;
						break;
					}
					guessing = false;
				}
			}
			
			
			
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
				System.out.println(numblanks);
			}
			if(numblanks == 0){
				System.out.println("You Win!");
				break;
			}
			if(i == 1){
				System.out.println("You lose! \nThe word was: "+ returnSet.iterator().next());
			}
		}
	}
	
	public static void main(String[] args) throws GuessAlreadyMadeException {
		if(args.length != 3){
			System.out.println("Usage: java [your main class name] dictionary wordLength guesses");
		}
		rungame(args[0], args[1], args[2]);
	}
}
