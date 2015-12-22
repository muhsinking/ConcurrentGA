import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by muhsinking on 12/21/15.
 */
public class GA {
    List<Chromosome> population;
//    List<Double> fitness;
    Knapsack ks;
    int stringLength;
    int numEpochs;
    double mutationProb;

    public GA(int populationSize, int stringLength, int numEpochs, double mutationProb){
        this.stringLength = stringLength;
        population = initPop(populationSize);
        ks = new Knapsack(stringLength, 500, 100);
        population = ks.getFitness(population);
//        printFitness(population);
        this.numEpochs = numEpochs;
        this.mutationProb = mutationProb;
    }

    public void printFitness(List<Chromosome> lc){
        System.out.println("");
        for(Chromosome c : lc){
            System.out.print(c.getFitness() + " ");
        }
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
        List<Chromosome> scaledUp = scaleUp(pop);
        List<Chromosome> scaledFitness = scaleFitness(scaledUp);
        Collections.sort(scaledFitness, Collections.reverseOrder());
        printFitness(scaledFitness);
        List<Chromosome> ANF = accumulatedNormalizedFitness(scaledFitness);
        printFitness(ANF);
        return newPop;
    }

    // scales a list of doubles by the sum of the list
    private List<Chromosome> scaleFitness(List<Chromosome> pop){
        Double sum = 0.0;
        List<Chromosome> newList = new ArrayList<Chromosome>(pop.size());
        for(Chromosome c : pop){
            sum += c.getFitness();
        }
        for(Chromosome c : pop){
            double fitness = c.getFitness();
            fitness /= sum;
            c.setFitness(fitness);
            newList.add(c);
        }

        return newList;
    }

    private List<Chromosome> scaleUp(List<Chromosome> pop){
        double minimum = Collections.min(pop).getFitness();
        List<Chromosome> newList = new ArrayList<Chromosome>(pop.size());
        for(Chromosome c : pop){
            double fitness = c.getFitness();
            c.setFitness(fitness + minimum);
            newList.add(c);
        }
        return newList;
    }

    // adds a running sum of accumulated fitness to each fitness value
    private List<Chromosome> accumulatedNormalizedFitness(List<Chromosome> pop){

        Double sum = 0.0;
        List<Chromosome> newList = new ArrayList<Chromosome>(pop.size());

        for(Chromosome c : pop){
            double fitness = c.getFitness();
            c.setFitness(fitness + sum);
            newList.add(c);
            sum += fitness;
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
