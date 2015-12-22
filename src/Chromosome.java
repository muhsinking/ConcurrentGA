import java.util.Random;

/**
 * Created by muhsinking on 12/21/15.
 */
public class Chromosome{
    StringBuilder bits;

    public Chromosome(int length){
        bits = initChromosome(length);
    }

    public StringBuilder initChromosome(int length){
        Random random = new Random();
        for(int i = 0; i < length; i++){

        }
    }

    public String toString(){
        return bits.toString();
    }

    public void mutate(double probability){

    }
}
