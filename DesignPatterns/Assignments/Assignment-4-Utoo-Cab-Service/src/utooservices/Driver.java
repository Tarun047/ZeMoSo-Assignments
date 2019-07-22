package utooservices;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Driver {
    private Cab cab;
    private String name;
    private String phoneNumber;
    private int rating;

    Driver(Cab cab)
    {
        this.cab=cab;
    }

    public void setLocation(String updatedLocation)
    {
        cab.setLocationCode(updatedLocation);
    }

    public String getLocation()
    {
        return cab.getLocationCode();
    }

    @Override
    public String toString()
    {
        return String.format("Driver Details are:\n\t Name: %s\n\t Mobile number: %s\n\tRating: %d\n%s",
                name,
                phoneNumber,
                rating,
                cab);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Cab getCab() {
        return cab;
    }

    public static List<Driver> getDummyDrivers()
    {
        List<Cab> cabs = Cab.getDummyCab();
        List<Driver> drivers = new ArrayList<>();
        String[] names = new String[]{
                "Ram",
                "Suresh",
                "Shyam",
                "Kiran",
                "Saleem"

        };

        String[] locations = new String[]
                {
                 "autonagar",
                 "patancheru",
                 "hiteccity",
                 "benzcircle",
                 "ameerpet"
                };
        //Static cabs at least 1 per location
        Random gen = new Random();
        for(String start:locations)
        {
            Driver d = new Driver(cabs.get(gen.nextInt(cabs.size())));
            d.setName(names[gen.nextInt(5)]);
            d.setPhoneNumber(Cab.getRandNumber(10));
            d.setLocation(start);
            d.setRating(gen.nextInt(6));
            drivers.add(d);
        }

        //Random cab allocation
        cabs = Cab.getDummyCab();
        for(int i=0;i<cabs.size();i++)
        {
            Driver d = new Driver(cabs.get(i));
            d.setName(names[gen.nextInt(5)]);
            d.setPhoneNumber(Cab.getRandNumber(10));
            d.setRating(gen.nextInt(6));
            d.setLocation(locations[gen.nextInt(5)]);
            drivers.add(d);
        }
        return drivers;
    }

}
