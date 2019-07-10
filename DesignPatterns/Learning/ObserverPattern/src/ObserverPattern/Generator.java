package ObserverPattern;

public interface Generator {
     void addListener(Monitor m);
     void removeListener(Monitor m);
     void generate();
     void notifyMonitors();
}
