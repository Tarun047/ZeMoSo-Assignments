/*
Question:
Write a java function that will ping any host ( given as input ) and computes the median of the time taken to ping.
Use the system ping utility, do not use any deprecated methods.
*/
/*
usage java Ping [host] [number_of_requests_to_be_made]
*/
import java.io.*;
import java.util.Arrays;
class Ping
{
  static BufferedReader br;
  static int NUM_PINGS = 10;
  static double pingTimes[];
  public static void makePing(String host)throws Exception
  {
    System.out.println("Working with ping");
    String cmd[] = new String[]{
      "ping",
      host,
      "-n",
      "-c",
      Integer.toString(NUM_PINGS),
    };
    ProcessBuilder pb = new ProcessBuilder(cmd);
    //pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
    Process p = pb.start();
    //p.waitFor();
    br = new BufferedReader(new InputStreamReader(p.getInputStream()));
    br.readLine(); // Neglect first output as it's just ping header
    populatePingTimes();
  }

  public static void populatePingTimes()throws Exception
  {
    pingTimes = new double[NUM_PINGS];
    for(int i=0;i<NUM_PINGS;i++)
    {
      String info[]=br.readLine().split(" ");
      pingTimes[i] = Double.parseDouble(info[6].substring(5));
    }
    Arrays.sort(pingTimes);
  }

  public static double medianTime()
  {
    int mid = NUM_PINGS/2;

    if(NUM_PINGS%2==0)
      return (pingTimes[mid]+pingTimes[mid+1])/2;
    else
      return pingTimes[mid];
  }

  public static void main(String args[])throws Exception
  {
    makePing(args[0]);
    if(args.length==2)
      NUM_PINGS=Integer.parseInt(args[1]);
    System.out.println("The median time to ping host "+args[0]+" is "+medianTime());
  }
}
