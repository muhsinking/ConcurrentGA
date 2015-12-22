import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by muhsinking on 12/21/15.
 */
public class GA {
    List<Chromosome> population;
    List<Double> fitness;
    Knapsack ks;
    int stringLength;
    int numEpochs;
    double mutationProb;

    public GA(int populationSize, int stringLength, int numEpochs, double mutationProb){
        this.stringLength = stringLength;
        population = initPop(populationSize);
        ks = new Knapsack(stringLength, 500, 100);
        fitness = ks.getFitness(population);
        this.numEpochs = numEpochs;
        this.mutationProb = mutationProb;
    }

    public void run(){
//        System.out.println(population);
        System.out.println(fps(population));
//        for(int i = 0; i < numEpochs; i++){
//
//        }
    }

    private List<Chromosome> initPop(int size){
        List<Chromosome> pop = new ArrayList<Chromosome>();
        for(int i = 0; i < size; i++){pop.add(new Chromosome(stringLength));}
        return pop;
    }

    private List<Chromosome> fps(List<Chromosome> pop){
        List<Chromosome> newPop = new ArrayList<Chromosome>();
        List<Double> scaledFitness = scaleDouble(fitness);
        System.out.println(scaledFitness);
        return newPop;
    }

    // scales a list of doubles from -1 to 1
    private List<Double> scaleDouble(List<Double> dl){
        Double maximum = Collections.max(dl);
        Double minimum = Collections.min(dl);
        Double signedRangeInverse = 1/(maximum - minimum);
        List<Double> newList = new ArrayList<Double>(dl.size());

        for(Double d : dl){
            d = (d-minimum) * signedRangeInverse * 2 - 1.0;
            newList.add(d);
        }
        return newList;
    }

    private List<Chromosome> crossover(List<Chromosome> pop){
        Random random = new Random();
        List<Chromosome> newPop = new ArrayList<Chromosome>();
        Chromosome last = pop.get(pop.size()-1);

        for(Chromosome c : pop){
            int crossoverPoint = (int)(Math.random() * ((stringLength - 1) + 1));
            c.crossover(last,crossoverPoint,stringLength);
        }

        return newPop;
    }

    private void mutate(){
    }

    public static void main(String[] args){
        GA ga = new GA(20, 20, 500, .05);
        ga.run();
    }
}
