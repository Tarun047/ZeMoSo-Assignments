package Bridge;

abstract class Computer {
    String os;
    String kernel;
    int hddSize;
    int ramSize;


    abstract void loadKernel();

    abstract void loadOS();

    public void post()
    {

        System.out.println(String.format("\nTrying to detect Hardware ... \nPrimary Memory: %s GB\n Secondary Memory: %s GB"
                ,hddSize,ramSize));
    }
}
