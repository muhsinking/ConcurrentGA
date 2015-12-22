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
        Collections.sort(scaledFitness, Collections.reverseOrder());
        System.out.println(scaledFitness);
        List<Double> ANF = accumulatedNormalizedFitness(scaledFitness);
        System.out.println(ANF);
        return newPop;
    }

    // scales a list of doubles by the sum of the list
    private List<Double> scaleDouble(List<Double> dl){
//        Double maximum = Collections.max(dl);
        Double sum = 0.0;
        List<Double> newList = new ArrayList<Double>(dl.size());
        for(Double d : dl){
            sum += d;
        }
        for(Double d : dl){
            d /= sum;
            newList.add(d);
        }

        sum = 0.0;
        for(Double d : newList){
            sum += d;
        }
        return newList;
    }

    private List<Double> accumulatedNormalizedFitness(List<Double> dl){
        System.out.println(dl);
        Double sum = 0.0;
        List<Double> newList = new ArrayList<Double>(dl.size());

        for(Double d : dl){
            d += sum;
            newList.add(d);
            sum += d;
        }

        System.out.println(sum);
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

    private List<Chromosome> mutate(List<Chromosome> pop){
        List<Chromosome> newPop = new ArrayList<Chromosome>();
        for(Chromosome c : pop){
            newPop.add(c.mutate(mutationProb));
        }
        return newPop;
    }

    public static void main(String[] args){
        GA ga = new GA(20, 20, 500, .05);
        ga.run();
    }
}
