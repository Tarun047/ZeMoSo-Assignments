package convy;

public interface Publisher {
    void register(Subscriber mListener);
    void unregister(Subscriber mListener);
    void notifyListeners();
}
