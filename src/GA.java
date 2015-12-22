import java.util.List;
import java.util.Random;

/**
 * Created by muhsinking on 12/21/15.
 */
public class GA {
    Chromosome[] population;
    double[] fitness;
    Knapsack ks;
    int stringLength;
    int numEpochs;
    double mutationProb;

    public GA(int populationSize, int stringLength, int numEpochs, double mutationProb){
        this.stringLength = stringLength;
        population = initPop(populationSize);
        ks = new Knapsack(stringLength);
        fitness = ks.getFitness(population);
        this.numEpochs = numEpochs;
        this.mutationProb = mutationProb;
    }

    public void run(){
        for(int i = 0; i < numEpochs; i++){

        }
    }

    private Chromosome[] initPop(int size){
        Chromosome[] pop = new Chromosome[size];
        for(int i = 0; i < size; i++){
            pop[i] = new Chromosome(stringLength);
        }
        return pop;
    }

    private Chromosome[] crossover(){
        return population;
    }

    private Chromosome[] mutate(){
        return population;
    }

    public static void main(String[] args){
        GA ga = new GA(1000, 20, 500, .05);
        ga.run();
    }

}
