public class RunCGA {
    public static void main(String[] args){
        int numThreads = 16;
        for(int i = 1; i <= numThreads; i++) {
            String name = "GA" + i;
            ConcurrentGA ga = new ConcurrentGA(name, 1000, 20, 500, .001);
            ga.start();
        }
    }
}