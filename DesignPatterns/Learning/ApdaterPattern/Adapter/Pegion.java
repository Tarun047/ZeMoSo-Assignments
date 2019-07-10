package Adapter;

public class Pegion implements Bird{


    @Override
    public void makeSound() {
        System.out.println("Pigeon makes Sound");
    }

    @Override
    public void fly() {
        System.out.println("Pigeon flies");

    }
}
