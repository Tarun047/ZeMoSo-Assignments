package subwaymenu;

public class SubWrapper implements SubItem {

    SubItem curItem;

    SubWrapper(SubItem item)
    {
        curItem=item;
    }

    @Override
    public double getCost() {
        return curItem.getCost();
    }

    @Override
    public String getDescription() {
        return curItem.getDescription();
    }
}
