/*
Question 2
2. Create a class with two (overloaded) constructors.
 Using this, call the second constructor inside the first one.
*/
class Overloading
{
  String message;
  int returnCode;
  Overloading()
  {
    this("Hello World");
    System.out.println("In default Constructor");
  }

  Overloading(String s)
  {
    this(s,0);
    this.message = s;
    System.out.println("In Single Parameterized Constructor");

  }

  Overloading(String s,int x)
  {
    System.out.println("In double Parameterized Constructor");
    this.message=s;
    this.returnCode=x;
  }

  public String toString()
  {
    return "Message: "+message+"Return Code: "+returnCode;
  }

  public static void main(String args[])
  {
    System.out.println(new Overloading());
    System.out.println(new Overloading("Hello World"));
    System.out.println(new Overloading("String",0));
  }
}
