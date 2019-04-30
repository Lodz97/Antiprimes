package antiprimes;

import java.util.ArrayList;
import java.util.List;


/**
 * Represent the sequence of antiprimes found so far.
 */
public class AntiPrimesSequence {

    /**
     * The numbers in the sequence.
     */
    private List<Number> antiPrimes = new ArrayList<>();
    private Observer obs;
    private Thread t;
    private NumberProcessor n;

    /**
     * Create a new sequence containing only the first antiprime (the number '1').
     */
    public AntiPrimesSequence() {
        this.reset();
        n = new NumberProcessor(antiPrimes);
        t = new Thread(n);
        t.start();
    }

    /**
     * Clear the sequence so that it contains only the first antiprime (the number '1').
     */
    public void reset() {
        antiPrimes.clear();
        antiPrimes.add(new Number(1, 1));
    }

    /**
     * Find a new antiprime and add it to the sequence.
     */
    public synchronized void computeNext() {
        //n.wakeUp();
        obs.update();
    }

    /**
     * Return the last antiprime found.
     */
    public Number getLast() {
        int n = antiPrimes.size();
        return antiPrimes.get(n - 1);
    }

    /**
     * Return the last k antiprimes found.
     */
    public List<Number> getLastK(int k) {
        int n = antiPrimes.size();
        if (k > n)
            k = n;
        return antiPrimes.subList(n - k, n);
    }

    public void setObs(Observer obs) {
        this.obs = obs;
    }
}
