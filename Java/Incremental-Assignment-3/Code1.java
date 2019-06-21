/*
Question 1:
1. Create an inheritance hierarchy of Rodent: Mouse, Gerbil, Hamster,etc.
In the base class, provide methods that are common to all Rodents,
and override these in the derived classes to perform different behaviors depending on the specific type of Rodent. 
Create an array of Rodent, fill it with different specific types of Rodents,
and call your base-class methods to see what happens.
Make the methods of Rodent abstract whenever possible
and all classes should have default constructors that print a message about that class.
*/

class Code1
{

  public static void main(String []args)
  {
    Rodent rodents[] = new Rodent[3];
    rodents[0] = new Mouse();
    rodents[1] = new Gebril();
    rodents[2] = new Hamster();

    //Base class methods are inherited to respective subclass objects
    rodents[0].setName("Jerry");
    rodents[1].setName("Bob");
    rodents[2].setName("Harry");

    for(int i=0;i<3;i++)
    {
      System.out.print("Meet "+rodents[i].getName()+ " It is a ");
      rodents[i].eat();
      rodents[i].move();
    }

    rodents[0].setSpeed(100);
    rodents[1].setSpeed(75);
    rodents[2].setSpeed(1);

    // To call a subclass method we must cast it to the required class and then make the call
    ((Mouse)rodents[0]).chased();
  }

}


abstract class Rodent
{

  private String name;
  private int movingSpeed;
  private static int numberOfTeeth;
  private static int numberOfLegs;

  static
  {
    numberOfLegs = 4;
    numberOfTeeth = 22;
  }

  Rodent()
  {
    System.out.println("This is a rodent");
    System.out.println("All rodents have "+numberOfLegs+" legs and "+numberOfTeeth+" teeth");
  }

  String getName() { return name; }
  void setName(String name) { this.name = name; }
  int getSpeed() { return movingSpeed; }
  void setSpeed(int speed) { this.movingSpeed = speed; }


  public int getLegs() { return numberOfLegs; }
  public int getTeeth() { return numberOfTeeth; }


  abstract void eat();
  abstract void move();

}

class Mouse extends Rodent
{
  int tailSize;

  Mouse()
  {
    super();
    System.out.println("This is a mice and it's a type of a rodent which moves with a minimum speed of 50 ");
    setSpeed(50);
  }

  void setSpeed(int speed)
  {
     if(speed<50 || speed>75)
        System.out.println("Sure that this is not a mouse! Speed must be between 50 and 75");
     else
        super.setSpeed(speed);
  }

  void eat()
  {
    System.out.println("Mouse, eats worms");
  }

  void move()
  {
    System.out.println("moves moderately");
  }

  public int getTailSize() { return tailSize; }
  public void setTailSize(int size) { this.tailSize = size; }

  public void chased()
  {
    System.out.println("Mouse is generally chased by cat");
  }


}

class Hamster extends Rodent
{
   Hamster()
   {
     super();
     System.out.println("This is a hamster and it generally runs fast at a minmum speed of 100, It can be used as a mini generator");
     setSpeed(100);
   }

  void setSpeed(int speed)
   {
     if(speed<100){
       System.out.println("Sure that this is not a hamster! It must have a minimum speed of 100");
       return;
     }
     super.setSpeed(speed);
   }

   void eat()
   {
     System.out.println("Hamster, eats grass");
   }

   void move()
   {
     System.out.println("moves fast");
   }
}

class Gebril extends Rodent
{

  Gebril()
  {
    super();
    System.out.println("This is a Gabril it runs very slowly and it's maximum speed is 50");
  }

  void setSpeed(int speed)
  {
    if(speed>50)
    {
      System.out.println("Sure this is not a Gebril! Speed must be between 0 and 50");
      return;
    }
    super.setSpeed(speed);
  }

  void eat()
  {
    System.out.println("Gabril, eats corn");
  }

  void move()
  {
    System.out.println("moves slowly");
  }

}
