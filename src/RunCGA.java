/**
 * Created by muhsinking on 12/24/15.
 */
public class RunCGA {
    public static void main(String[] args){
        ConcurrentGA ga = new ConcurrentGA("GA1", 1000, 20, 500, .001);
        ga.run();
    }
}
