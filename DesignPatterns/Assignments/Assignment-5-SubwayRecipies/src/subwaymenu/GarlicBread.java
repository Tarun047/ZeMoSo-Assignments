package subwaymenu;
public class GarlicBread extends Bread
{


    @Override
    String getBreadType() {
        return "Farm fresh, garlic bread, ";
    }

    @Override
    double getBreadCost() {
        return 10;
    }
}