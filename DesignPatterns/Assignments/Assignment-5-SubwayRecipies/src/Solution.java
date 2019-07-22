import subwaymenu.*;

public class Solution {
    public static void main(String[] args) {
        Bread garlicBread = new GarlicBread();
        SubWrapper wrapper = new CucumberSalad(garlicBread);
        System.out.println(wrapper.getDescription()+" "+wrapper.getCost());
        wrapper = new TomatoSauce(wrapper);
        System.out.println(wrapper.getDescription()+" "+wrapper.getCost());
        wrapper = new CorianderAddon(wrapper);
        System.out.println(wrapper.getDescription()+" "+wrapper.getCost());
    }
}
