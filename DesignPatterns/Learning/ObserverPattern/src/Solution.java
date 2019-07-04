import ObserverPattern.*;

public class Solution {
    public static void main(String[] args) {
        Generator g = new NaturalNumbers(1);
        Monitor p = new PrimeWatcher();
        Monitor f = new FibboListener();
        g.addListener(p);
        g.addListener(f);
        new Thread(() -> {
            while(true) {
                try {
                    g.generate();
                    //Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
