package main;

import java.util.ArrayList;
import java.util.Random;

public class Population {

	private ArrayList<IndividualWord> individualWords;
	
	public Population(int populationSize, String targetWord) {
		char[] alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		String individualWord = "";
		Random rand = new Random();
		
		individualWords = new ArrayList<>(populationSize);
		
		for(int i = 0; i < populationSize; i++) {
			for(int j = 0; j < targetWord.length(); j++) {
				individualWord += alphabet[rand.nextInt(alphabet.length)];
			}
			individualWords.add(new IndividualWord(individualWord, targetWord));
			individualWord = "";
		}
	}
	
	public ArrayList<IndividualWord> getPopulation() {
		return this.individualWords;
	}
	
	public void setPopulation(ArrayList<IndividualWord> population) {
		this.individualWords = population;
	}
	
	public IndividualWord getIndividual(int index) {
		return getPopulation().get(index);
	}
	
	public int size() {
		return getPopulation().size();
	}
	
	public IndividualWord getFittest() {
		IndividualWord bestFitness = getPopulation().get(0);
		for(IndividualWord individualWord : getPopulation()) {
			if(bestFitness.getFitness() < individualWord.getFitness()) {
				bestFitness = individualWord;
			}
		}
		return bestFitness;
	}
	
	public ArrayList<IndividualWord> evolvePopulation(Population population) {
		ArrayList<IndividualWord> newPopulation = new ArrayList<IndividualWord>();
		for(int i = 0; i < population.size(); i++) {
			IndividualWord parentA = Selection.tournamentSelection(population);
			IndividualWord parentB = Selection.tournamentSelection(population);
			IndividualWord child = parentA.crossOver(parentB).mutate();
			newPopulation.add(child);
		}
		return newPopulation;
	}
	
	@Override
	public String toString() {
		String ret = "";
		for(int i = 0; i < getPopulation().size(); i++) { 
			ret += getPopulation().get(i).getGuessWord() + " | ";
		}
		return ret;
	}
}
