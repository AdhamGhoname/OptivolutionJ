package optivolution.Knapsack;
import optivolution.Chromosome;
import java.util.*;


public class KnapsackChromosome extends Chromosome {
	int maximumWeight = 15;
	ArrayList<KnapsackData> knapsackData = new ArrayList<KnapsackData>();
	
	KnapsackChromosome() {
		//test data
		super(5);
		knapsackData.add(new KnapsackData(12, 4));
		knapsackData.add(new KnapsackData(1, 2));
		knapsackData.add(new KnapsackData(4, 10));
		knapsackData.add(new KnapsackData(1, 1));
		knapsackData.add(new KnapsackData(2, 2));
	}
	
	
	
	@Override
	public double getFitness() {
		if (this.fitnessUpdated) {
			return this.fitnessValue;
		}
		else {
			this.calculateFitness();
			return this.fitnessValue;
		}
	}
	
	@Override
	public void calculateFitness() {
		int totalValue = 0;
		int totalWeight = 0;
		for (int i = 0; i < this.genesLength; i++) {
			if ((int)this.genes.get(i) == 1) {
				totalValue += this.knapsackData.get(i).value;
				totalWeight += this.knapsackData.get(i).weight;
			}
		}
		if (totalWeight > this.maximumWeight)
			totalValue = 0;
		this.fitnessValue = totalValue;
		this.fitnessUpdated = true;
	}
	
	@Override
	public Object randomGene() {
		Random rand = new Random();
		return rand.nextInt(2);
	}
	
	
	@Override
	public KnapsackChromosome crossover(Object motherObj) {
		KnapsackChromosome mother = (KnapsackChromosome)(motherObj);
		Random rand = new Random();
		int mid = rand.nextInt(this.genesLength);
		ArrayList<Object> childGenes = new ArrayList<Object>(this.genes);
		for (int i = mid; i < this.genesLength; i++) {
			childGenes.set(i, mother.genes.get(i));
		}
		KnapsackChromosome child = new KnapsackChromosome();
		child.genes = childGenes;
		return child;
	}
}
