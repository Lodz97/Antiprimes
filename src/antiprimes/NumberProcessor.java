package antiprimes;

import java.util.ArrayList;
import java.util.List;

public class NumberProcessor implements Runnable {
    private List<Number> sequence;
    public NumberProcessor(List<Number> sequence)
    {
        this.sequence = sequence;
    }

    @Override
    public void run() {
        while(true)
            acceptRequests();
    }

    synchronized public void acceptRequests()
    {

        try
        {
            wait();
            nextAntiPrime();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public synchronized void nextAntiPrime()
    {
        sequence.add(AntiPrimes.nextAntiPrimeAfter(getNextoProcess()));
    }

    public Number getNextoProcess()
    {
        int n = sequence.size();
        return sequence.get(n - 1);
    }

    public synchronized void wakeUp()
    {
        notifyAll();
    }
}
