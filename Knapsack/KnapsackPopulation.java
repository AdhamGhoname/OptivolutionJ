package optivolution.Knapsack;
import optivolution.Population;
import java.util.*;


public class KnapsackPopulation extends Population {
	
	
	public KnapsackPopulation(int populationSize) {
		super(populationSize);
	}
	
	
	@Override
	public KnapsackChromosome randomIndividual() {
		return new KnapsackChromosome();
	}
	
	@Override
	public KnapsackChromosome selectIndividual() {
		int N = this.populationSize;
	
		boolean[] selected = new boolean[N];
		
		ArrayList<KnapsackChromosome> sample = new ArrayList<KnapsackChromosome>();
		Random rand = new Random();
		
		for (int i = 0; i < Math.max(N * this.tournamentPercentage, 2.0); i++) {
			int randomIndex = rand.nextInt(N);
			while(selected[randomIndex]) {
				randomIndex = rand.nextInt(N);
			}
			sample.add((KnapsackChromosome)this.population.get(randomIndex));
			selected[randomIndex] = true;
		}
		KnapsackChromosome best = sample.get(0);
		double bestFitness = best.getFitness();
		for (int i = 1; i < sample.size(); i++) {
			if (sample.get(i).getFitness() > bestFitness) {
				best = sample.get(i);
				bestFitness = best.getFitness();
			}
		}
		return best;
	}
	
	
	@Override
	public KnapsackChromosome getBestIndividual() {
		KnapsackChromosome best = (KnapsackChromosome)this.population.get(0);
		double bestFitness = best.getFitness();
		for (int i = 1; i < this.populationSize; i++) {
			if (((KnapsackChromosome)this.population.get(i)).getFitness() > bestFitness) {
				best = (KnapsackChromosome)this.population.get(i);
				bestFitness = best.getFitness();
			}
		}
		return best;
	}
	
	@Override
	public void evolve() {
		ArrayList<Object> newPopulation = new ArrayList<Object>(this.population);
		for (int i = 0; i < newPopulation.size(); i++) {
			KnapsackChromosome father = this.selectIndividual();
			KnapsackChromosome mother = this.selectIndividual();
			KnapsackChromosome child = father.crossover(mother);
			
			child.mutate();
			newPopulation.set(i, child);
		}
		this.population = newPopulation;
	}
	
}
