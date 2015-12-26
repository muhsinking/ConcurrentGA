import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GA {
    List<Chromosome> population;
    Knapsack ks;
    int populationSize;
    int stringLength;
    int numEpochs;
    double mutationProb;

    public GA(int populationSize, int stringLength, int numEpochs, double mutationProb){
        this.stringLength = stringLength;
        this.populationSize = populationSize;
        population = initPop(populationSize);
        ks = new Knapsack(stringLength, 500, 400);
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

    public double run(){
        int convergenceCount = 0;
        double max = 0;

        float start = System.nanoTime();

        for(int i = 0; i < numEpochs; i++){
            List<Chromosome> newPop = new ArrayList<Chromosome>(population);
            newPop = fps(newPop);
            newPop = ks.getFitness(newPop);
            double newMax = Collections.max(newPop).getFitness();
            if(Math.abs(newMax-max) <= .000001) convergenceCount ++;
            else convergenceCount = 0;
//            System.out.println(newMax);

            if(convergenceCount >= 20) {
                System.out.println("converged");
                break;
            }
            max = newMax;

            population = newPop;
        }
        float end = System.nanoTime();
        float total = end-start;
        return total;
    }

    private List<Chromosome> initPop(int size){
        List<Chromosome> pop = new ArrayList<Chromosome>();
        for(int i = 0; i < size; i++){pop.add(new Chromosome(stringLength));}
        return pop;
    }

    // performs roulette wheel selection, also known as fitness proportional selection
    private List<Chromosome> fps(List<Chromosome> pop){
        Random random = new Random();

        List<Chromosome> scaledFitness = scaleFitness(pop);
        Collections.sort(scaledFitness, Collections.reverseOrder());
        List<Chromosome> ANF = accumulatedNormalizedFitness(scaledFitness);

        List<Chromosome> newPop = new ArrayList<Chromosome>();

        for(int i = 0; i < populationSize/2; i++){
            double parent1ANF = random.nextDouble();
            double parent2ANF = random.nextDouble();

            Chromosome parent1 = searchANF(ANF,parent1ANF);
            Chromosome parent2 = searchANF(ANF,parent2ANF);

            int crossoverPoint = random.nextInt((stringLength) + 1);

            Chromosome child1 = crossover(parent1, parent2, crossoverPoint);
            Chromosome child2 = crossover(parent2, parent1, crossoverPoint);

            child1 = child1.mutate(mutationProb);
            child2 = child2.mutate(mutationProb);

            newPop.add(child1);
            newPop.add(child2);
        }

        Collections.shuffle(newPop);

        return newPop;
    }

    // computes the crossover between two chromosomes
    private Chromosome crossover (Chromosome parent1, Chromosome parent2, int crossoverPoint){
        boolean[] bits = new boolean[stringLength];

        for(int i = 0; i < crossoverPoint; i++){
            if(parent1.getBit(i)) bits[i] = true;
            else bits[i] = false;
        }

        for(int i = crossoverPoint; i < stringLength; i++){
            if(parent2.getBit(i)) bits[i] = true;
            else bits[i] = false;
        }

        return new Chromosome(bits);
    }

    // linearly searches the accumulated normalized fitnesses for the first value greater than the target
    private Chromosome searchANF (List<Chromosome> ANFList, double ANFTarget){
        for(int i = 0; i < ANFList.size(); i ++){
            Chromosome c = ANFList.get(i);
            if(c.getFitness() > ANFTarget){
                return c;
            }
        }
        return ANFList.get(ANFList.size()-1);
    }

    // scales fitness levels such that all sum to 1
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

    // adds a running sum of accumulated fitness to each scaled fitness value
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
        double sum = 0;
        for(int i = 0; i < 100; i++){
            GA ga = new GA(1000, 20, 1000, .001);
            sum += ga.run();
        }

        double avg = sum/100;

        System.out.println(avg/1000000 + " miliseconds");

    }
}