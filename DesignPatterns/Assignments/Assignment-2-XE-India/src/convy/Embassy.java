package convy;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;


public class Embassy implements Subscriber {

    private Map<String,Double> interests;
    private static DecimalFormat df;
    private int currentID;
    private static int subscriberID =0;
    Publisher pb;
    public Embassy(Publisher pb)
    {
        currentID = ++subscriberID;
        df = new DecimalFormat("##.##");
        interests =new HashMap<>();
        pb.register(this);
    }

    private String showChange(String country,double oldPrice,double newPrice)
    {

        oldPrice = Double.valueOf(df.format(oldPrice));
        newPrice = Double.valueOf(df.format(newPrice));
        double change = ((newPrice-oldPrice)/oldPrice)*100;
        if(change!=0)
         return country+" "+oldPrice+" : "+newPrice+" : "+df.format(change);
        return null;
    }

    public void addInterest(String country)
    {
        interests.put(country,currency.getCurrency(country));
    }

    @Override
    public void update() {
        for(String interest: interests.keySet())
        {
            String res = showChange(interest, interests.get(interest),currency.getCurrency(interest));
            if(res!=null)
                System.out.println("Update from Object with subscriber ID: "+currentID+"\n"+res);
            interests.put(interest,currency.getCurrency(interest));
        }
    }


}
