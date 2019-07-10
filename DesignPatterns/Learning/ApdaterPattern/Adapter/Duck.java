package Adapter;

public class Duck implements WaterAnimals {


    @Override
    public void sail() {
        System.out.println("Duck Sails!");
    }

    @Override
    public void signal() {
        System.out.println("Duck Quacks!");
    }
}
