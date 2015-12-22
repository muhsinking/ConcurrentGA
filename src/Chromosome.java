import java.util.Arrays;
import java.util.Random;

/**
 * Created by muhsinking on 12/21/15.
 */
public class Chromosome{
    boolean[] bits;

    public Chromosome(int length){
        bits = initRandom(length);
    }

    public boolean[] initRandom(int length){
        Random random = new Random();
        boolean[] c = new boolean[length];
        for(int i = 0; i < length; i++){
            if(random.nextDouble() > 0.5) c[i] = true;
            else c[i] = false;
        }
        return c;
    }

    public String toString(){
        return bits.toString();
    }

    public void crossover(Chromosome c, int start, int end){
        for(int i = start; i < end; i++){
            bits[i] = c.getBit(i);
        }
    }

    public boolean getBit(int i){
        return bits[i];
    }

    public void flipBit(int i){
        if(bits[i]) bits[i] = false;
        else bits[i] = true;
    }

    public void mutate(double probability){
        Random random = new Random();
        for(int i = 0; i < bits.length; i++){
            if(random.nextDouble() < probability) flipBit(i);
        }
    }
}
