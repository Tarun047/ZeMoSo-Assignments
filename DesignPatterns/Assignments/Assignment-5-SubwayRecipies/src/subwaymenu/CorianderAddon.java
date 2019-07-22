package subwaymenu;

public class CorianderAddon extends  Addons{
    public CorianderAddon(SubItem item) {
        super(item);
    }

    @Override
    double getAddonCost() {
        return 1.2;
    }

    @Override
    String getAddonDescription() {
        return "Coriander tipping";
    }
}
