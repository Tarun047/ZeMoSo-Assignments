package utooservices;

public class Ride {
    private String source;
    private String destination;
    private double fare;
    private Driver driver;

    Ride()
    {

    }

    Ride(String source,String destination)
    {
        this.source=source;
        this.destination=destination;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return String.format("The Ride details are: \n\tPickup: %s \n\tDrop: %s\n\tFare:%s\n%s",source,destination,fare,driver);
    }
}
