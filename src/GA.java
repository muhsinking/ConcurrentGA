import java.util.List;

/**
 * Created by muhsinking on 12/21/15.
 */
public class GA {
    String[] population;
    int[] fitness;
//    int populationSize;
    int stringLength;
    int numEpochs;
    double mutationProb;

    public GA(int populationSize, int stringLength, int numEpochs, double mutationProb){
        population = new String[populationSize];
        fitness = new int[populationSize];
        this.stringLength = stringLength;
        this.numEpochs = numEpochs;
        this.mutationProb = mutationProb;
    }

    public void run(){

    }

    private String[] crossover(){
        return population;
    }

    private String[] mutate(){

        return population;
    }

    public static void main(String[] args){
        GA ga = new GA(1000, 20, 500, .05);
        
    }

}
