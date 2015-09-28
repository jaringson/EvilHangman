package hangman;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class EvilHangmanGame implements IEvilHangmanGame {

	private Map<String, Set<String>> comparer = new HashMap<String, Set<String>>();
	private Set<String> dictionarySet = new TreeSet<String>();
	
	@Override
	public void startGame(File dictionary, int wordLength) {
		comparer.clear();
		dictionarySet.clear();
		try {
			Scanner reader = new Scanner (new BufferedInputStream(new FileInputStream(dictionary)));
			while(reader.hasNextLine()){
				String word = reader.nextLine();
				if(word.length() == wordLength){
					//System.out.println(word);
					dictionarySet.add(word);
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find File specified");
			System.exit(0);
		}
	}
	
	String makePattern(String word, char guess){
		StringBuilder patt = new StringBuilder();
		for(int i = 0; i < word.length(); i++){
			if(word.charAt(i) == guess){
				patt.append(guess);
			}
			else{
				patt.append("-");
			}
		}
		//System.out.println(patt.toString());
		return patt.toString();
	}
	
	@Override
	public Set<String> makeGuess(char guess) throws GuessAlreadyMadeException {
		comparer.clear();
		for(String word: dictionarySet){
			String patt = makePattern(word, guess);
			if(comparer.containsKey(patt)){
				comparer.get(patt).add(word);
			}
			else{
				Set temp = new TreeSet<String>();
				temp.add(word);
				comparer.put(patt, temp);
			}
		}
		String winner = "";
		Set returnSet = new TreeSet<String>();
		for(Entry<String, Set<String>> entry: comparer.entrySet()){
			//System.out.println(entry.getKey() + "   " + entry.getValue());
			Set next = entry.getValue();
			if(next.size() > returnSet.size()){
				returnSet = next;
				winner = entry.getKey();
			}
			else if(next.size() == returnSet.size()){
				int numA = 0;
				int numB = 0;
				for(int i = entry.getKey().length()-1; i >= 0;i--){
					if(winner.charAt(i) == guess){
						numA++;
					}
					if(entry.getKey().charAt(i) == guess){
						numB++;
					}
				}
				if(numA == numB){
					for(int i = entry.getKey().length()-1; i >= 0;i--){
						if(winner.charAt(i) != entry.getKey().charAt(i)){
							//System.out.print("here1");
							if(entry.getKey().charAt(i) == guess){
								//System.out.print("here2");
								winner = entry.getKey();
							}
							break;
						}
					}
				}
				else if(numB < numA){
					winner = entry.getKey();
				}
			}
		}
		dictionarySet = comparer.get(winner);
		return comparer.get(winner);
	}

}
