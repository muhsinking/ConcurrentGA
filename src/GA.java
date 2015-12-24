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
    int populationSize;
    int stringLength;
    int numEpochs;
    double mutationProb;

    public GA(int populationSize, int stringLength, int numEpochs, double mutationProb){
        this.stringLength = stringLength;
        this.populationSize = populationSize;
        population = initPop(populationSize);
        ks = new Knapsack(stringLength, 500, 100);
        population = ks.getFitness(population);
        this.numEpochs = numEpochs;
        this.mutationProb = mutationProb;
    }

    public void printFitness(List<Chromosome> lc){
        System.out.println("");
        for(Chromosome c : lc){
            System.out.print(c.getFitness() + " ");
        }
    }

    public void printPopulaion(List<Chromosome> lc){
        System.out.println("");
        for(Chromosome c : lc){
            System.out.print(c + " ");
        }
    }

    public void run(){
        for(int i = 0; i < 1; i++){
            population = (fps(population));
        }
    }

    private List<Chromosome> initPop(int size){
        List<Chromosome> pop = new ArrayList<Chromosome>();
        for(int i = 0; i < size; i++){pop.add(new Chromosome(stringLength));}
        return pop;
    }

    private List<Chromosome> fps(List<Chromosome> pop){
        Random random = new Random();

        List<Chromosome> scaledUp = scaleUp(pop);
        List<Chromosome> scaledFitness = scaleFitness(scaledUp);
        Collections.sort(scaledFitness, Collections.reverseOrder());
        List<Chromosome> ANF = accumulatedNormalizedFitness(scaledFitness);
        printPopulaion(ANF);
        printFitness(ANF);

        List<Chromosome> newPop = new ArrayList<Chromosome>();

        // linear search anf for next largest value after random value between 0 and 1
        for(int i = 0; i < populationSize/2; i++){
            System.out.println("");

            double parent1ANF = random.nextDouble();
            double parent2ANF = random.nextDouble();
            Chromosome parent1 = searchANF(ANF,parent1ANF);
            Chromosome parent2 = searchANF(ANF,parent2ANF);

            System.out.println(parent1);
            System.out.println(parent2);

            int crossoverPoint = random.nextInt((stringLength-1) + 1);

            System.out.println(crossoverPoint);

            Chromosome child1 = parent1.crossover(parent2,0,crossoverPoint);
            Chromosome child2 = parent2.crossover(parent1,crossoverPoint,stringLength-1);

            System.out.println(child1);
            System.out.println(child2);

            child1 = child1.mutate(mutationProb);
            child2 = child2.mutate(mutationProb);

            newPop.add(child1);
            newPop.add(child2);
        }

        return newPop;
    }

    private Chromosome searchANF (List<Chromosome> ANFList, double ANFTarget){
        for(int i = 0; i < ANFList.size(); i ++){
            Chromosome c = ANFList.get(i);
            if(c.getFitness() > ANFTarget){
                return c;
            }
        }
        return ANFList.get(ANFList.size()-1);
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

        return newList;
    }

    public static void main(String[] args){
        GA ga = new GA(20, 20, 500, .05);
        ga.run();
    }
}
