/*
Question 4:
Create a Cycle interface, with implementations Unicycle, Bicycle and Tricycle.
Create factories for each type of Cycle, and code that uses these factories.


*/
interface Cycle
{
  void printWheels();
}

class UniCycle implements Cycle
{
  private int numWheels;
  UniCycle()
  {
    numWheels=1;
  }
  public void printWheels()
  {
    System.out.println("UniCycle has "+numWheels+" wheels");
  }
}

class BiCycle implements Cycle
{
   private int numWheels;
   BiCycle()
   {
     numWheels=2;
   }
   public void printWheels()
   {
     System.out.println("BiCycle has "+numWheels+" wheels");
   }
}

class TriCycle implements Cycle
{
   private int numWheels;
   TriCycle()
   {
     numWheels=3;
   }
   public void printWheels()
   {
     System.out.println("TriCycle has "+numWheels+" wheels");
   }
}

class CycleFactory
{
  public static Cycle getUniCycle(){ return new UniCycle(); }
  public static Cycle getBiCycle(){ return new BiCycle(); }
  public static Cycle getTriCycle(){ return new TriCycle(); }
  public static Cycle getCycle(Class<? extends Cycle> cls)
  {
    try{
      return cls.getDeclaredConstructor().newInstance();
    }
    catch(Exception e){ return null; }
  }
}

class Code4
{
  public static void main(String []args)
  {
    Cycle cycle1 = CycleFactory.getUniCycle();
    Cycle cycle2 = CycleFactory.getBiCycle();
    Cycle cycle3 = CycleFactory.getTriCycle();

    Cycle myCycle = CycleFactory.getCycle(BiCycle.class);

    cycle1.printWheels();
    cycle2.printWheels();
    cycle3.printWheels();

    myCycle.printWheels();
  }
}
