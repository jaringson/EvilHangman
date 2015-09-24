package hangman;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class EvilHangmanGame implements IEvilHangmanGame {
	
	

	private Map<String, Set<String>> comparer = new HashMap();
	private Set<String> dictionarySet = new TreeSet();
	@Override
	public void startGame(File dictionary, int wordLength) {
		comparer.clear();
		try {
			Scanner reader = new Scanner (new BufferedInputStream(new FileInputStream(dictionary)));
			while(reader.hasNextLine()){
				String word = reader.nextLine();
				if(word.length() == wordLength){
					dictionarySet.add(word);
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Set<String> makeGuess(char guess) throws GuessAlreadyMadeException {
		// TODO Auto-generated method stub
		return null;
	}

}
