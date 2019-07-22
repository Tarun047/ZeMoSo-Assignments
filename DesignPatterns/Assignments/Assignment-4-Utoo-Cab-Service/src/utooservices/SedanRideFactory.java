package utooservices;

public class SedanRideFactory extends RideFactory{

    @Override
    void setCurrentCat() {
        currentCat=CabCategory.SEDAN;
    }

    @Override
    void setRidePrice() {
        currentPrice=8;
    }

    @Override
    double calculateRidePrice() {
        return (currentPrice+Pricing.getPricing(rideOrigin,terminal))*2.5;
    }
}
