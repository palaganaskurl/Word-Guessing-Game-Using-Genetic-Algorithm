package main;

import java.util.Random;

public class Selection {	
	
	public static IndividualWord tournamentSelection(Population population) {
		final int TOURNAMENT_SIZE = 20;
		Random rand = new Random();
		IndividualWord bestFitness = population.getIndividual(rand.nextInt(population.size()));
		for(int i = 0; i < TOURNAMENT_SIZE; i++) {
			IndividualWord contFitness = population.getIndividual(rand.nextInt(population.size()));
			if(contFitness.getFitness() < bestFitness.getFitness()) {
				bestFitness = contFitness;
			}
		}
		return bestFitness;
	}
}
