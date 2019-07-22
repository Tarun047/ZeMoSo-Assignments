package subwaymenu;

public class TomatoSauce extends Sauce {
    public TomatoSauce(SubItem item) {
        super(item);
    }

    @Override
    String getSauceType() {
        return "Home grown tomatoes and a tasty sauce.";
    }

    @Override
    double getSauceCost() {
        return 2.5;
    }
}
