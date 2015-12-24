import java.util.Arrays;
import java.util.Random;

/**
 * Created by muhsinking on 12/21/15.
 */
public class Chromosome implements Comparable<Chromosome> {
    private boolean[] bits;
    private double fitness;

    public Chromosome(int length){
        bits = initRandom(length);
        fitness = 0.0;
    }

    // nonrandom initialization
    public Chromosome(boolean[] bits){
        this.bits = new boolean[bits.length];
        System.arraycopy(bits, 0, this.bits, 0, bits.length );
        fitness = 0.0;
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

    @Override
    public String toString(){
        String str = "";
        for(int i = 0; i < bits.length; i++){
            if(bits[i]) str += "1";
            else str += "0";
        }
        return str;
    }

    @Override
    public int compareTo(Chromosome c) {
        if(c.fitness < this.fitness) return 1;
        else if (c. fitness > this.fitness) return -1;
        return 0;
    }

    public void setFitness(double f){fitness = f;}

    public double getFitness(){return fitness;}

    public Chromosome crossover(Chromosome c, int start, int end){
        Chromosome child = new Chromosome(bits);
        for(int i = start; i < end; i++){
            child.setBit(i,c.getBit(i));
        }
        return child;
    }

    public void setBit(int i, boolean s){
        bits[i] = s;
    }

    public boolean getBit(int i){return bits[i];}

    public void flipBit(int i){
        if(bits[i]) bits[i] = false;
        else bits[i] = true;
    }

    public Chromosome mutate(double probability){
        Random random = new Random();
        for(int i = 0; i < bits.length; i++){
            if(random.nextDouble() < probability) flipBit(i);
        }
        return this;
    }
}
