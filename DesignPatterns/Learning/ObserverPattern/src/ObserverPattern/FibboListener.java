package ObserverPattern;

public class FibboListener implements Monitor
{
    int x,y,toFind;
    public FibboListener()
    {
        x=0;
        y=1;
        toFind = x+y;

    }

    @Override
    public void update(int newValue) {
        //System.out.println(x+" "+y+" "+toFind);
        if(toFind<newValue)
        {
            while(newValue>toFind)
            {
                toFind=x+y;
                x=y;
                y=toFind;
            }
        }

        else if(x==0 || x==1 || toFind==newValue)
            System.out.println("Subscriber Found a fibonacci number: "+newValue);
    }
}
