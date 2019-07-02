package Bridge;

public class DellLaptop extends Laptop {
    public DellLaptop(Computer mCompter, String model) {
        super(mCompter, model);
    }

    @Override
    public void updateBIOS() {
        System.out.println("\nContacting Dell Servers ... \nFetching Latest Updates\n");
    }
}
