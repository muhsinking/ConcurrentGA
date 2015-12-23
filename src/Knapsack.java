import java.util.*;
import java.util.Random;

/**
 * Created by muhsinking on 12/21/15.
 */
public class Knapsack {
    double[] sizes;
    double sizeLimit;
    double maxSize;

    public Knapsack(int n, double sizeLimit, double maxSize){
        this.sizeLimit = sizeLimit;
        this.maxSize = maxSize;
        Random random = new Random();
        sizes = new double[n];
        for(int i = 0; i < n; i++){
            sizes[i] = maxSize * random.nextDouble();
        }
        System.out.println("Knapsack sizes: " + Arrays.toString(sizes));
    }

    public List<Chromosome> getFitness(List<Chromosome> population){
        List<Chromosome> newList = new ArrayList<Chromosome>();
        for(Chromosome c:population){
            double sum = 0;
            for(int i = 0; i < sizes.length; i++){if(c.getBit(i)) sum += sizes[i];}
            if(sum > sizeLimit) c.setFitness(maxSize-2*(sum-maxSize));
            else c.setFitness(sum);
            newList.add(c);
        }
        return newList;
    }
}