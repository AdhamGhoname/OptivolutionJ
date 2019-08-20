package optivolution;
import java.util.*;

public abstract class Chromosome {
	
	
	protected double mutationRate = 0.01;
	
	//length of the genes list
	public int genesLength = 0;
	
	//genes list
	public ArrayList<Object> genes = new ArrayList<Object>();
	
	//boolean to check if fitness is already calculated
	protected boolean fitnessUpdated = false;
	
	//fitness of the object
	protected double fitnessValue = 0.0;
	
	
	//Constructors
	protected Chromosome() {
		//
	}
	protected Chromosome(int genesLength, ArrayList<Object> genes) {
		this.genesLength = genesLength;
		if (genes.size() == 0) {
			for (int i = 0; i < genesLength; i++) {
				this.genes.add(this.randomGene());
			}
		}
	}
	
	protected Chromosome(int genesLength) {
		this.genesLength = genesLength;
		for (int i = 0; i < genesLength; i++) {
			this.genes.add(this.randomGene());
		}
	}
	
	
	
	
	// Crossover function
	//override in child class 
	protected abstract Object crossover(Object mother);
	
	//Generates a random gene
	//override in child class
	protected abstract Object randomGene();
	
	/* Override this function to calculate your object's fitness
	 * make sure to set super().fitnessUpdated to true when you call this function
	 */
	protected abstract void calculateFitness();
	
	//Mutation Function
	public void mutate() {
		for (int i = 0; i < this.genesLength; i++) {
			if ((new Random()).nextDouble() < this.mutationRate) {
				this.genes.set(i, this.randomGene());
			}
		}
	}
	
	
	//returns object fitness
	public double getFitness() {
		if (this.fitnessUpdated)
			return this.fitnessValue;
		else {
			this.calculateFitness();
			return this.fitnessValue;
		}
	}

}
