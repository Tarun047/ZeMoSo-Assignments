package subwaymenu;

public class CuminSalad extends Salad{

    CuminSalad(SubItem item) {
        super(item);
    }

    @Override
    String getSaladType() {
        return "Cumin can boost health, ";
    }

    @Override
    double getSaladCost() {
        return 7.5;
    }
}
