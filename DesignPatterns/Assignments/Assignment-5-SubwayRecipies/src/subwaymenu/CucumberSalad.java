package subwaymenu;

public class CucumberSalad extends Salad {
    public CucumberSalad(SubItem item) {
        super(item);
    }

    @Override
    String getSaladType() {
        return "cool cucumber, that energizes, ";
    }

    @Override
    double getSaladCost() {
        return 5;
    }
}
