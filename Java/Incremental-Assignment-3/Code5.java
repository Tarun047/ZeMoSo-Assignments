/*
 Create a class with an inner class that has a non-default constructor (one that takes arguments).
 Create a second class with an inner class that inherits from the first inner class.
*/
class DC
{
  class SuperMan
  {
    String city;
    SuperMan(String city)
    {
      this.city=city;
      System.out.println("This is Super man aka Clark Kent aka Kal'al");
    }
    void punch()
    {
      System.out.println("SuperPunch");
    }
  }
}

class DCTV
{
  DCTV()
  {
    Flash f = new Flash();
    f.lightningStrike();
    f.punch();
    System.out.println("City is: "+f.city);
  }
  class Flash extends DC.SuperMan
  {
    Flash()
    {
      new DC().super("New York");
      System.out.println("This is Flash aka Barry Allen");
    }

    void lightningStrike()
    {
      System.out.println("Lightnining Strike");
    }
  }
}

class Code5
{
  public static void main(String args[])
  {
    new DCTV();
  }
}
