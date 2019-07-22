package utooservices;

import java.util.ArrayList;
import java.util.List;

public class City
{
    String name;
    List<Driver> drivers;


    public City(String name) {
        this.name=name;
        drivers = new ArrayList<>();
    }


    public void addDriver(Driver d)
    {
        drivers.add(d);
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public String getName() {
        return name;
    }
}
