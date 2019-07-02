package Bridge;

public class MacBook extends Laptop {
    public MacBook(Computer mCompter, String model) {
        super(mCompter, model);
    }

    @Override
    public void updateBIOS() {
        System.out.println("\nValidating Mac OS integrity ... ");
        if(batteryLevel>25)
            System.out.println("Updating Mac OS\n");
        else
            System.out.println("Please Connect a charger to update OS");
    }
}
