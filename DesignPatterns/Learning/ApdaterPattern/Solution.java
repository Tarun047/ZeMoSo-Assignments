import Adapter.*;
class Solution
{
    public static void main(String[] args) {
        /*
        *Adapter Pattern simply converts an incompatible interface to the needed one
        * It does this by calling appropriate methods of the required implementation
        * By composition and by implementing the incompatible interface.
         */
        Bird mPegion = new Pegion();
        WaterAnimals mDuck = new Duck();

        //Adapter
        WaterAnimals spDuck = new BirdAdapter(mPegion);

        System.out.println("Calling actual Pegion methods");
        mPegion.fly();
        mPegion.makeSound();

        System.out.println("Calling actual Duck methods");
        mDuck.sail();
        mDuck.signal();

        System.out.println("Calling adapter methods");
        spDuck.sail();
        spDuck.signal();


    }
}