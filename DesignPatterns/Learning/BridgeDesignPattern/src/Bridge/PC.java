package Bridge;

public class PC extends Computer{

    public PC(String os, String kernel, int disksize, int ramsize)
    {
        super();
        this.os = os;
        this.kernel = kernel;
        this.hddSize = disksize;
        this.ramSize = ramsize;
    }
    @Override
    void loadKernel() {
        System.out.println("Awaking Kernel:"+kernel);
    }

    @Override
    void loadOS() {
        System.out.println("Starting OS: "+os);
    }
}
