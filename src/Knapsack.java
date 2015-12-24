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
//        sizes = new double[n];
//        for(int i = 0; i < n; i++){
//            sizes[i] = maxSize * random.nextDouble();
//        }
        sizes = new double[] {109.60, 125.48, 52.16, 195.55, 58.67, 61.87, 92.95, 93.14, 155.05, 110.89, 13.34, 132.49, 194.03, 121.29, 179.33, 139.02, 198.78, 192.57, 81.66, 128.90};
        System.out.println("Knapsack sizes: " + Arrays.toString(sizes));
    }

    public List<Chromosome> getFitness(List<Chromosome> population){
        List<Chromosome> newList = new ArrayList<Chromosome>();
        Random random = new Random();
        for(Chromosome c:population){
            double sum = 0;
            for(int i = 0; i < sizes.length; i++){if(c.getBit(i)) sum += sizes[i];}
            while(sum > sizeLimit){
                int clear = random.nextInt(sizes.length);
                if (c.getBit(clear)){
                    sum -= sizes[clear];
                    c.setBit(clear,false);
                }
            }
            c.setFitness(sum);
            newList.add(c);
        }
        return newList;
    }
}