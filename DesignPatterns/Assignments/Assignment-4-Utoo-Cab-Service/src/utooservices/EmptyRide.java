package utooservices;

public class EmptyRide extends Ride {

    EmptyRide()
    {
        super();
    }

    EmptyRide(String source, String destination) {
        super(source, destination);
    }
    @Override
    public String toString() {
        return "Sorry, no match found";
    }
}
