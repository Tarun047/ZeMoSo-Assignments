package subwaymenu;

public abstract class Sauce extends SubWrapper {
    Sauce(SubItem item) {
        super(item);
    }

    @Override
    public String getDescription() {
        return super.getDescription()+getSauceType();
    }

    @Override
    public double getCost() {
        return super.getCost()+getSauceCost();
    }

    abstract String getSauceType();
    abstract double getSauceCost();
}
