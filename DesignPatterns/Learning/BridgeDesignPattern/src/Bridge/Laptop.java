package Bridge;

public abstract class Laptop {
    int batteryLevel=100;
    Computer mCompter;
    String model;

    Laptop(Computer mCompter,String model)
    {
        this.mCompter = mCompter;
        this.model = model;
    }

    public void loadKernel()
    {
        if(batteryLevel>0)
            mCompter.loadKernel();
    }

    public void loadOS()
    {
        if(batteryLevel>0)
            mCompter.loadOS();
    }

    public void post()
    {
        mCompter.post();
    }
    public abstract void updateBIOS();
}
