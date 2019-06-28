package Decorator;

public class TomatoSauce extends ToppingDecorator {
    public TomatoSauce(Pizza pizza) {
        super(pizza);
    }

    public String getDescription()
    {
        return myPizza.getDescription()+",Tomato Sauce";
    }
    public int getCost()
    {
        return myPizza.getCost()+10;
    }
}
