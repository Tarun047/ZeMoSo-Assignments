package Decorator;

abstract class ToppingDecorator implements Pizza {
    protected Pizza myPizza;

    ToppingDecorator(Pizza pizza)
    {
        myPizza = pizza;
    }

    public String getDescription()
    {
        return myPizza.getDescription();
    }

    public int getCost()
    {
        return myPizza.getCost();
    }
}