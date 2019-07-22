package utooservices;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

abstract public class RideMaker {

    abstract List<Ride> getPossibleRides(City currentCity);
    String rideOrigin;
    String terminal;


    public static Ride findMyRide(String currentCity, String source, String destination,CabCategory category)
    {
        RideFactory factory;
        if(category == CabCategory.MINI)
            factory = new MiniRideFactory();
        else if(category==CabCategory.MICRO)
            factory = new MicroRideFactory();
        else if(category==CabCategory.SEDAN)
            factory = new SedanRideFactory();
        else
            factory = new SedanRideFactory();
        return factory.searchForRides(currentCity,  source,  destination, category);
    }

    Ride searchForRides(String currentCity, String source, String destination,CabCategory category)
    {
        System.out.println("\n\nRequesting "+category+" ride from"+source+" to "+destination);
        rideOrigin = source;
        terminal = destination;

        try
        {
            City city = new City(currentCity);
            List<Ride> matches = getPossibleRides(city);
            if (matches.size() == 0)
                return new EmptyRide();
            System.out.println("Based on your Requirement we found following rides: ");
            int i = 1;
            for (Ride match : matches) {
                System.out.println(i + "\n" + match);
                i++;
            }
            System.out.println("Please select one or more rides to continue booking");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int x = Integer.parseInt(br.readLine());

            if (x <= matches.size() && x > 0) {
                System.out.println("\nBooking Successful!");
                System.out.println(matches.get(x-1));
                return matches.get(x - 1);
            }
            else
            {
                System.out.println("Sorry, no match found!");
            }
            return new EmptyRide();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
