package optivolution;
import java.util.*;

public abstract class Population {
	
	protected int populationSize;
	protected int generationCount = 0;
	
	//tournament selection percentage
	protected double tournamentPercentage = 0.5;
	
	
	//Population list
	protected ArrayList<Object> population;
	
	//Default constructor... Do not use.
	protected Population() {
		
	}
	
	protected Population(int populationSize, ArrayList<Object> population) {
		this.populationSize = populationSize;
		this.population =  new ArrayList<Object>();
		if (population.size() == 0) {
			for (int i = 0; i < populationSize; i++) {
				population.add(this.randomIndividual());
			}
		}
		else {
			this.population = population;
		}
	}
	
	protected Population(int populationSize) {
		this.populationSize = populationSize;
		this.population = new ArrayList<Object>();
		for (int i = 0; i < populationSize; i++) {
			this.population.add(this.randomIndividual());
		}
	}
	
	//runs evolve for <numIterations> times.
	public void run(int numIterations) {
		for (int i = 0; i < numIterations; i++) {
			this.evolve();
		}
	}
	
	/*
	 * override this function in the child class
	 * should return a new Chromosome object
	 */
	protected abstract Object randomIndividual();
	
	
	/*
	 * override this function in the child class
	 * should implement the selection algorithm
	 */
	protected abstract Object selectIndividual();
	
	/*
	 * override this function in the child class
	 * should apply selection and crossover to generate a new population
	 */
	protected abstract void evolve();
	

	/*
	 * override this function in the child class
	 * should return the Chromosome object with highest fitnessValue
	 */
	protected abstract Object getBestIndividual();
	
	
}
