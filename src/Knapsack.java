import java.util.Arrays;
import java.util.Random;

/**
 * Created by muhsinking on 12/21/15.
 */
public class Knapsack {
    double[] sizes;

    public Knapsack(int n){
        Random random = new Random();
        sizes = new double[n];
        for(int i = 0; i < n; i++){
            sizes[i] = 100 * random.nextDouble();
        }
        System.out.println("Knapsack sizes: " + Arrays.toString(sizes));
    }

    public double[] getFitness(Chromosome[] population){
        double[] fitness = new double[population.length];

        for(int i = 0; i < population.length; i++){
            int sum = 0;
            for(int j = 0; j < sizes.length; j++){
                if(population[i].getBit(j)) sum += sizes[j];
            }
            fitness[i] = sum;
        }
        return fitness;
    }
}
