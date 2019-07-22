package utooservices;

import java.util.ArrayList;
import java.util.List;

abstract public class RideFactory extends RideMaker {

    CabCategory currentCat;
    double currentPrice;

    boolean isValidRide(City city,CabCategory cabCategory)
    {
        if(cabCategory==CabCategory.SEDAN)
            return city.getName().toLowerCase().equals("chennai") || city.getName().toLowerCase().equals("bengaluru");
        else if(cabCategory==CabCategory.SUV)
            return city.getName().toLowerCase().equals("bengaluru") || city.getName().toLowerCase().equals("hyderabad");
        return true;
    }

    RideFactory()
    {
        setCurrentCat();
        setRidePrice();
    }

    public static void fillDummyDrivers(City cityToFill)
    {
        for(Driver d:Driver.getDummyDrivers())
            cityToFill.addDriver(d);
    }

    abstract void setCurrentCat();

    abstract void setRidePrice();

    abstract double calculateRidePrice();

    @Override
    List<Ride> getPossibleRides(City currentCity) {
        //Adding dummy drivers for test purpose
        fillDummyDrivers(currentCity);
        List<Ride> possibilities = new ArrayList<>();
        for(Driver d:currentCity.getDrivers())
        {
            if(d.getCab().getCategory()==currentCat && isValidRide(currentCity,d.getCab().getCategory()) && d.getLocation().equals(rideOrigin))
            {
                Ride r = new Ride(rideOrigin,terminal);
                r.setFare(calculateRidePrice());
                r.setDriver(d);
                possibilities.add(r);
            }
        }
        return possibilities;
    }

}
