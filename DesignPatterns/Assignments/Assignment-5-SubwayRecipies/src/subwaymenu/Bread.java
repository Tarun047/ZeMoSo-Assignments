package subwaymenu;

abstract public class Bread implements SubItem{

    @Override
    public double getCost() {
        return getBreadCost();
    }

    @Override
    public String getDescription() {
        return getBreadType();
    }

    abstract String getBreadType();
    abstract double getBreadCost();
}
