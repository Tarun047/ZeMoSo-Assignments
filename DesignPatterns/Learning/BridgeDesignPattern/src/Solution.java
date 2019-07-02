import Bridge.DellLaptop;
import Bridge.Laptop;
import Bridge.MacBook;
import Bridge.PC;

public class Solution {

    public static void main(String[] args) {
        Laptop model1 = new DellLaptop(new PC("Ubuntu","Linux",500,4),"3521");
        Laptop model2 = new MacBook(new PC("Mac OS","Mach",256,8),"Air 13");

        model1.post();
        model1.loadKernel();
        model1.loadOS();

        model2.post();
        model2.loadKernel();
        model2.loadOS();

        model1.updateBIOS();
        model2.updateBIOS();
    }
}
