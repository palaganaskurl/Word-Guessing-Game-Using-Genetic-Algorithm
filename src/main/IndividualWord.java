package main;

import java.util.Random;

public class IndividualWord {
	
	private String guessWord;
	private String targetWord;
	private int fitness;
	
	public IndividualWord(String guessWord, String targetWord) {
		this.guessWord = guessWord;
		this.targetWord = targetWord;
		setFitness();
	}
	
	public int size() {
		return guessWord.length();
	}
	
	public String getGuessWord() {
		return this.guessWord;
	}
	
	public String getTargetWord() {
		return this.targetWord;
	}
	
	public void setFitness() {
		this.fitness = getFitness(this);
	}
	
	public int getFitness() {
		return this.fitness;
	}
	
	private char randomLetter() {
		Random rand = new Random();
		char[] alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		return alphabet[rand.nextInt(alphabet.length)];
	}
	
	public IndividualWord mutate() {
		final double MUTATION_RATE = 0.015;
		String word = "";
		for(int i = 0; i < size(); i++) {
			if(Math.random() <= MUTATION_RATE) {
				word += randomLetter();
			} else {
				word += getGuessWord().charAt(i);
			}
		}
		return new IndividualWord(word, getTargetWord());
	}
	
	public IndividualWord crossOver(IndividualWord partner) {
		String word = "";
//		for(int i = 0; i < size(); i++) {
//			if(Math.random() <= 0.5) { // Cross Over Rate
//				word += getGuessWord().charAt(i);
//			} else {
//				word += partner.getGuessWord().charAt(i);
//			}
//		}
		
		int pivot = this.size() / 2;
		
		for(int i = 0; i < size(); i++) {
			if(i >= pivot) {
				word += this.guessWord.charAt(i);
			} else {
				word += partner.getGuessWord().charAt(i);
			}
		}
		
		System.out.println("Guess Word :" + this.guessWord);
		System.out.println("Partner Word : " + partner.getGuessWord());
		System.out.println("Word : " + word);
		
		return new IndividualWord(word, getTargetWord());
	}
	
	private int getFitness(IndividualWord individualWord) {
		int fitness = 0;
		int wordLength = individualWord.getGuessWord().length();
		String word = individualWord.getGuessWord();
		
		for(int i = 0; i < wordLength; i++) {
			char wordCurrentLetter = word.charAt(i);
			int wordCurrentLetterIndex = indexOf(wordCurrentLetter);
			int targetWordCurrentLetterIndex = indexOf(getTargetWord().charAt(i));
			fitness += Math.pow(wordCurrentLetterIndex - targetWordCurrentLetterIndex, 2);
		}
		return fitness;
	}

	private int indexOf(char letter) {
		char[] alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		
		for(int i = 0; i < alphabet.length; i++) {
			if(letter == alphabet[i]) {
				return i + 1;
			}
		}
		return 0;
	}
}
