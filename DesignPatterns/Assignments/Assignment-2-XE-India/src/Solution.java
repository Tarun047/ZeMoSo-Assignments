import convy.Embassy;
import convy.Publisher;
import convy.WorldBank;

import java.util.Random;


public class Solution {
    private static void setInitialRates(WorldBank worldBank)
    {
        worldBank.updateExchanges("USD",67);
        worldBank.updateExchanges("GBP",80);
        worldBank.updateExchanges("EUR",69);
    }

    private static void changeRates(WorldBank worldBank)
    {
        Random gen = new Random();
        String[] countries = new String[]{"USD","EUR","GBP"};
        double ranVal = (Math.random()*0.9) - 0.45;
        String choice = countries[gen.nextInt(3)];
        worldBank.updateExchanges(choice,worldBank.getPrice(choice,1)+ranVal);
    }


    public static void main(String[] args) {
        WorldBank worldBank = new WorldBank();
        Embassy embassy = new Embassy(worldBank);

        setInitialRates(worldBank);
        embassy.addInterest("USD");
        embassy.addInterest("GBP");
        embassy.addInterest("EUR");

        Embassy nEmbassy = new Embassy(worldBank);
        nEmbassy.addInterest("GBP");

        Embassy pEmbassy = new Embassy(worldBank);
        pEmbassy.addInterest("EUR");

        //Test thread to simulate change
        new Thread(){
            @Override
            public void run() {
                super.run();
                for(int i=0;i<50;i++) {
                    changeRates(worldBank);
                    try{
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        //Actual thread that retrieves data from api
        worldBank.unregister(pEmbassy);
        new Thread()
        {
            @Override
            public void run() {
                super.run();
                for(int i=0;i<20;i++)
                {
                    worldBank.autoUpdateExchanges();
                    try
                    {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        worldBank.unregister(pEmbassy);

    }
}
