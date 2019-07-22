package subwaymenu;


public class BrownBread extends Bread {

    @Override
    String getBreadType() {
        return "Classic, normal bread";
    }

    @Override
    double getBreadCost() {
        return 25;
    }
}
