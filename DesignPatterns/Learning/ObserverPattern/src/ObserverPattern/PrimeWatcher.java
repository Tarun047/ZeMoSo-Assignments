package ObserverPattern;

public class PrimeWatcher implements Monitor {


    @Override
    public void update(int n) {
        if(n==1)
            return;
        for(int i=2;i<=Math.sqrt(n);i++)
            if(n%i==0)
                return;
        System.out.println("Subscriber found a prime number: "+n);
    }
}
