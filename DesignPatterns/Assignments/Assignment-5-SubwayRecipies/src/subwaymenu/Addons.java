package subwaymenu;

public abstract class Addons extends SubWrapper{
    Addons(SubItem item) {
        super(item);
    }

    @Override
    public double getCost() {
        return super.getCost()+getAddonCost();
    }

    @Override
    public String getDescription() {
        return super.getDescription()+getAddonDescription();
    }

    abstract double getAddonCost();
    abstract String getAddonDescription();
}
