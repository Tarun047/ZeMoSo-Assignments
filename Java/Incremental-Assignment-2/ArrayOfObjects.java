/*
Question:
3. Create a class with a constructor that takes a String argument.
During construction, print the argument.
Create an array of object references to this class, but don’t actually create objects to assign into the array.
When you run the program, notice whether the initialization messages from the constructor calls are printed.
4. Complete the previous exercise by creating objects to attach to the array of references.
*/
class MyObject
{
  String myString;
  MyObject(String s)
  {
    myString = s;
    System.out.println(s);
  }
}
public class ArrayOfObjects
{

  public static void main(String []args)
  {
    MyObject list[] = new MyObject[10];
    //Nothing is printed before actually initializing the references in
    //the array to the corresponding objects

    //initializing Objects
    for(int i=0;i<10;i++)
      list[i]=new MyObject(Character.toString('a'+i));
  }
}
