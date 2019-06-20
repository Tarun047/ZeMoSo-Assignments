package tarun.assignment.main;
import tarun.assignment.data.MyClass;
import tarun.assignment.singleton.MySingleton;
class Driver
{
  //The commented lines won't work, please see their classes for explanations
  public static void main(String args[])
 {
   MyClass demo = new MyClass();
   demo.printMyFields();
   //demo.printLocalFields();
   MySingleton sample = MySingleton.getInstance("Hello Java");
   //sample.printString(); 
 }
}
