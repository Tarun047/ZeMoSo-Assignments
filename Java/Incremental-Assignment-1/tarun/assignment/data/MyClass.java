package tarun.assignment.data;
public class MyClass
{
 int myIntValue;
 char myCharValue;

/*This method works because there the class fields will be initialized to 0 or its equivalent in the respective type if they are not initialized automatically and hence we are not accessing any garbage but we are accessing the defaulted value*/
 public void printMyFields()
 {
   System.out.println("myIntValue = "+myIntValue);
   System.out.println("myCharValue = "+myCharValue);
 }

 /*
 This method will not work because we haven't initialized local variables of    types integer and character and we are trying to access them, which means we are trying to access some garbage value and java will not allow us to do this
 public void printLocalFields()
 { 
   int localInteger;
   char localCharacter;
   System.out.println("localInteger = "+localInteger);
   System.out.println("localCharacter = "+localCharacter); 
 }*/
}
