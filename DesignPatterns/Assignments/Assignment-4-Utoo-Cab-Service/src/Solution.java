import utooservices.CabCategory;
import utooservices.RideFactory;
import utooservices.Ride;
import utooservices.RideMaker;

public class Solution {
    public static void main(String[] args) {

        RideMaker.findMyRide("Hyderabad","autonagar","patancheru",CabCategory.MINI);
        RideMaker.findMyRide("Chennai","hiteccity","patancheru",CabCategory.MICRO);
        RideMaker.findMyRide("Hyderabad","benzcircle","ameerpet",CabCategory.SEDAN);
        RideMaker.findMyRide("Banglore","autonagar","benzcircle",CabCategory.SUV);
    }
}
