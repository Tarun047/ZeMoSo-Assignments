package subwaymenu;

abstract public class Salad extends SubWrapper
{

    Salad(SubItem item) {
        super(item);
    }

    @Override
    public double getCost() {
        return super.getCost()+getSaladCost();
    }

    @Override
    public String getDescription() {
        return super.getDescription()+getSaladType();
    }

    abstract String getSaladType();
    abstract double getSaladCost();
}
