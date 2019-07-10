package Decorator;

public class Marghereta  extends ToppingDecorator{
    public Marghereta(Pizza pizza) {
        super(pizza);
    }
    public String getDescription()
    {
        return myPizza.getDescription()+", Marghereta";
    }
    public int getCost()
    {
        return myPizza.getCost()+10;
    }
}
