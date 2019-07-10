package JLocker;

import java.io.IOException;

public class Container implements Vault {

    //Method to show that create file works
    public static void demoCreate()throws IOException
    {
        Guard myGuard = new Guard();
        myGuard.addFile("Sample.txt");
    }

    //Method to show that delete works
    public static void demoDelete()
    {
        Guard myGuard = new Guard();
        myGuard.removeFile("Sample.txt");
    }

    @Override
    public String[] listFiles() {
          Guard myGuard = new Guard();
          return myGuard.listFiles();
    }
}
