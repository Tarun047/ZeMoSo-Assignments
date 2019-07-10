import Decorator.*;
class Solution
{
    public static void main(String[] args) {
        //Execution Hierarchy
        /*
        * First Base Pizza Object is created and sent over to Marghereta's Constructor
        * Marghereta recieves this object and stores it in myPizza variable defined in it's super class Topping Decorator
        * After storing the base pizza object this class adds extra description and cost to whatever is there in Base
        * And returns this modified object instance, this modified instance is sent over to Tomato Sauce constructor
        * and this constructor calls the super class Topping Decorator with this modified object and it stores this modified
        * object. This class also adds all the needed features and extra cost and returns this ultimate modified object.
        * */
        Pizza suPizza = new TomatoSauce(new Marghereta(new BasePizza()));
        System.out.println(suPizza.getDescription()+" "+suPizza.getCost());
        /*suPizza.getDescription() = TomatoSauceInstance.getDesciption() -> (TomatoSauceInstance's myPizza = Marghereta).getDescription()
        * +"Tomato Sauce" -> Marghereta.getDesription() = (MargheretaInstance's myPizza = BasePizzaInstance).getDescription()+"Marghereta"
        * -> BasePizza.getDescription()
        * */
    }
}