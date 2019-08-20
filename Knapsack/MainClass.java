package optivolution.Knapsack;


public class MainClass {
	public static void main(String[] args) {
		KnapsackPopulation pop = new KnapsackPopulation(20);
		pop.run(10);
		KnapsackChromosome best = pop.getBestIndividual();
		System.out.println(best.getFitness());
		for (int i = 0; i < pop.getBestIndividual().genesLength; i++) {
			System.out.print(best.genes.get(i) + " ");
		}
	}
}
