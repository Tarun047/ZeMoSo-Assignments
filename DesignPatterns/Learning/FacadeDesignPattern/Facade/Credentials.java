package Facade;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.regex.*;
class Credentials {
    private String id;
    private String password;
    private Pattern idPattern = Pattern.compile(".*@zemosolabs\\.com$");
    private boolean isLogged;
    HashMap<String,String> hm = new HashMap<>();
    Credentials(String mId,String mPass)
    {
        id=mId;
        password = mPass;
        loadDataBase();
    }

    String getId() {
        return id;
    }

    private void loadDataBase()
    {
        try(ObjectInputStream obi = new ObjectInputStream(new FileInputStream("registry.dat")))
        {
            hm = (HashMap<String,String>) obi.readObject();
        }
        catch (Exception e)
        {
            System.out.println("Couldn't load Database: "+e.getMessage());
        }
    }

    private boolean validateId()
    {
        return idPattern.matcher(id).matches();
    }

    private boolean validatePassword()
    {
        return hm.get(id).equals(password);
    }

    boolean tryLogin()
    {
        if(validateId()&&validatePassword())
        {
            System.out.println("Login Success!");
            isLogged=true;
            return true;
        }

        System.out.println("Login Failed! Check your email id and password");
        return false;
    }

    void logout()
    {
        isLogged=false;
    }

    public boolean isLogged() {
        return isLogged;
    }
}
