package main;

import java.util.Scanner;

public class Main {

	private String targetWord;
	
	public Main(String targetWord) {
		this.targetWord = targetWord;
	}
	
	public String getTargetWord() {
		return this.targetWord;
	}
	
	public void launchGuessingGame() {
		Population population = new Population(30, getTargetWord());
		int generation = 1;
		while(!population.getFittest().getGuessWord().equals(getTargetWord())) {
			population.setPopulation(population.evolvePopulation(population));
			System.out.println("WORD : " + population.getFittest().getGuessWord());	
			System.out.println("Generation : " + generation);
			generation++;
		}
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter word to be guessed: ");
		String targetWord = scan.nextLine();
		scan.close();
		Main main = new Main(targetWord);
		main.launchGuessingGame();
	}
}
