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
