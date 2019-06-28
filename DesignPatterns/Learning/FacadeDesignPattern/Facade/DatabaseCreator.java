package Facade;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

class DatabaseCreator {
    static String ids[] = new String[]{
            "tarun@zemosolabs.com",
            "nikhil@zemosolabs.com",
    };
    static String psswds[] = new String[]{
            "1234",
            "4567"
    };
    public static void main(String[] args) {
        try(ObjectOutputStream obo = new ObjectOutputStream(new FileOutputStream("registry.dat"))){

            HashMap<String,String> hm = new HashMap<>();
            for(int i=0;i<ids.length;i++)
                hm.put(ids[i],psswds[i]);
            obo.writeObject(hm);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
