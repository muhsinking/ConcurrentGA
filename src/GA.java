import java.util.List;
import java.util.Random;

/**
 * Created by muhsinking on 12/21/15.
 */
public class GA {
    String[] population;
    double[] fitness;
    Knapsack ks;
    int stringLength;
    int numEpochs;
    double mutationProb;

    public GA(int populationSize, int stringLength, int numEpochs, double mutationProb){
        ks = new Knapsack(stringLength);
        population = initPop(populationSize);
        fitness = ks.getFitness(population);
        this.stringLength = stringLength;
        this.numEpochs = numEpochs;
        this.mutationProb = mutationProb;
    }

    public void run(){
        for(int i = 0; i < numEpochs; i++){

        }
    }

    private String[] initPop(int size){
        Random random = new Random();
        for(int i = 0; i < size; i++){

        }
        return 0;
    }

    private String[] crossover(){
        return population;
    }

    private String[] mutate(){

        return population;
    }

    public static void main(String[] args){
        GA ga = new GA(1000, 20, 500, .05);
        ga.run();
    }

}
