package subwaymenu;

public class GingerSauce extends Sauce {

    GingerSauce(SubItem item) {
        super(item);
    }

    @Override
    String getSauceType() {
        return "A taste of spicy ginger to improve digestion";
    }

    @Override
    double getSauceCost() {
        return 7.5;
    }
}
