package Decorator;

public class BasePizza implements Pizza {
    @Override
    public String getDescription() {
        return "Thin Dough , Normal Pizza";
    }

    @Override
    public int getCost() {
        return 15;
    }
}
