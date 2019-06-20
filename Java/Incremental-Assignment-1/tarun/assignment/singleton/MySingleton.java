package tarun.assignment.singleton;
public class MySingleton
{
 String refString;
 public static MySingleton getInstance(String refString)
 { 
   MySingleton obj  = new MySingleton();
   obj.refString = refString;
   return obj;
 }

 public void printString()
 {
   System.out.println(refString);
 }
}
