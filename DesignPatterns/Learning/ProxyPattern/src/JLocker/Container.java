package JLocker;

public class Container implements Vault {

    @Override
    public String[] listFiles() {
          Guard myGuard = new Guard();
          return myGuard.listFiles();
    }
}
