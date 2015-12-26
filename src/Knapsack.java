import java.util.*;
import java.util.Random;

public class Knapsack {
    double[] sizes;
    double sizeLimit;
    double maxSize;

    public Knapsack(int n, double sizeLimit, double maxSize){
        this.sizeLimit = sizeLimit;
        this.maxSize = maxSize;
        Random random = new Random();
        sizes = new double[n];
//        use below for randomized initialization of values:
//        for(int i = 0; i < n; i++){
//            sizes[i] = maxSize * random.nextDouble();
//        }
        sizes = new double[] {169.90, 155.05, 122.19, 173.93, 92.95, 93.14, 195.55, 13.34, 125.59, 58.67, 61.87, 132.49, 102.33, 197.77, 192.57, 86.16, 189.10,52.16, 194.03, 128.90};
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