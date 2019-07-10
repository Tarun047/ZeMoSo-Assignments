package Adapter;

public class BirdAdapter implements WaterAnimals {
    Bird bird;
    public BirdAdapter(Bird mBird)
    {
        bird = mBird;
    }
    @Override
    public void sail() {
        bird.fly();
    }

    @Override
    public void signal() {
        bird.makeSound();
    }
}
