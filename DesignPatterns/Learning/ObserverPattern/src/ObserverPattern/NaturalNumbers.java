package ObserverPattern;

import java.util.HashSet;


public class NaturalNumbers implements Generator {
    private static int number;

    private HashSet<Monitor> monitors;
    public NaturalNumbers(int seed)
    {
        number=seed;
        monitors = new HashSet<>();
    }

    public void generate()
    {
        number++;
        notifyMonitors();
    }


    @Override
    public void addListener(Monitor m) {
        monitors.add(m);
    }

    @Override
    public void removeListener(Monitor m) {
        monitors.remove(m);
    }

    @Override
    public void notifyMonitors() {
        for (Monitor m : monitors)
            m.update(number);
    }
}
