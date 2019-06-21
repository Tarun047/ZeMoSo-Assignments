/*
 Create three interfaces, each with two methods.
 Inherit a new interface that combines the three, adding a new method.
 Create a class by implementing the new interface and also inheriting from a concrete class.
 Now write four methods, each of which takes one of the four interfaces as an argument.
 In main( ), create an object of your class and pass it to each of the methods
*/
import java.util.Arrays;
interface DBHandler
{
  void connectToDataBase(String username,String password);
  void executeQuery(String query);
}

interface Network extends DBHandler
{
  void requestConnection(String ip);
  void disconnect();
}

interface API extends Network
{
  void generateOutput();
  String generateKey();
}
interface REST extends API
{
  void runSequence();
}


class Code3
{
  public static void main(String args[])
  {
    Website myWebsite = new Website();
    myWebsite.setVersion(2);
    String tech[] = new String[2];
    tech[0]="React";
    tech[1]="Bootstrap";
    myWebsite.setTechStack(tech);
    dbInvoke(myWebsite);
    nwInvoke(myWebsite);
    apiInvoke(myWebsite);
    restInvoke(myWebsite);
  }

  public static void dbInvoke(DBHandler handler)
  {
    handler.connectToDataBase("ADMIN","SYS123");
    handler.executeQuery("SELECT * FROM TABLE");
  }

  public static void nwInvoke(Network mynet)
  {
    mynet.requestConnection("192.168.35.24");
    mynet.disconnect();
  }

  public static void apiInvoke(API myAPI)
  {
    myAPI.generateOutput();
    myAPI.generateKey();
  }

  public static void restInvoke(REST myRest)
  {
    myRest.runSequence();
  }

}



class FrontEnd
{
  private int version;
  private String[] techStack;

  public int getVersion()
  {
    return version;
  }
  void setVersion(int version)
  {
    this.version = version;
  }
  String[] getTechStack()
  {
    return this.techStack;
  }

  void setTechStack(String []s)
  {
    techStack = new String[s.length];
    System.arraycopy(s,0,techStack,0,s.length);
  }
}

class Website extends FrontEnd implements REST
{
  public void connectToDataBase(String uname,String pwd)
  {
    System.out.println("Trying to connect to the Database with "+uname);
    //Some connection logic
  }

  public void executeQuery(String query)
  {
    System.out.println("Executing query: "+query);
    //Some execution logic
  }

  public void requestConnection(String ip)
  {
    System.out.println("Attempting to reach ip: "+ip);
    //Some Connection Logic
  }
  public void disconnect()
  {
    System.out.println("Disconnected Successfully");
  }
  public void generateOutput()
  {
    System.out.printf("[{Version: %d, Tech Stack:%s }]\n",getVersion(),Arrays.toString(getTechStack()));
  }
  public String generateKey()
  {
    return Long.toString(System.nanoTime());
  }
  public void runSequence()
  {
    System.out.println("Some random run sequence");
  }
}
