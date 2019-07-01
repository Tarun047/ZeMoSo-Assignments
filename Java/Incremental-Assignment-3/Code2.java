/*
Question 2:
. Create a Cycle class, with subclasses Unicycle, Bicycle and Tricycle.
Add a balance( ) method to Unicycle and Bicycle, but not to Tricycle.
Create instances of all three types and upcast them to an array of Cycle.
Try to call balance( ) on each element of the array and observe the results.
Downcast and call balance( ) and observe what happens.
*/
class Code2
{
  public static void main(String []args)
  {
    Cycle cycles[] = new Cycle[3];

    cycles[0] = new UniCycle();
    cycles[1] = new BiCycle();
    cycles[2] = new TriCycle();

    /*These calls won't work as the base class doesn't know about balance method
    in it's children
    cycles[0].balance();
    cycles[1].balance();
    cycles[2].balance();
    */

    //To make the methods work we need to downgrade and then call
    ((UniCycle)cycles[0]).balance();
    ((BiCycle)cycles[1]).balance();
    //((TriCycle)cycles[2]).balance() won't work as there is no balance method defined


  }
}
class Cycle
{
  int numWheels;
  Cycle()
  {
    System.out.println("This is a cycle");
  }

  int getNumWheels(){ return this.numWheels; }
  void setNumWheels(int numWheels) { this.numWheels = numWheels; }
}

class UniCycle extends Cycle
{
  UniCycle()
  {
    super();
    System.out.println("This is UniCycle");
  }
  void setNumWheels(int numWheels){ this.numWheels = 1; }
  void balance()
  {
    System.out.println("Balance method of unicycle");
  }
}

class BiCycle extends Cycle
{
  BiCycle()
  {
    super();
    System.out.println("This is BiCycle");
  }
  void setNumWheels(int numWheels){ this.numWheels = 2; }
  void balance()
  {
    System.out.println("Balance method of BiCycle");
  }
}

class TriCycle extends Cycle
{
  TriCycle()
  {
    super();
    System.out.println("This is TriCycle");
  }
  void setNumWheels(int numWheels){ this.numWheels = 3; }
}
