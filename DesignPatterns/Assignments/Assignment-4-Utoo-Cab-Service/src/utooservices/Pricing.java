package utooservices;

public class Pricing {
    public static double getPricing(String source,String destination)
    {
        //Some dummy price calculation logic
        double price = 0;
        for(int i=0;i<Math.min(source.length(),destination.length());i++)
            price+=Math.abs(source.charAt(i)-destination.charAt(i));
        return price;
    }
}
