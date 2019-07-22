package utooservices;

public class MicroRideFactory extends RideFactory {
    @Override
    void setCurrentCat() {
        currentCat = CabCategory.MICRO;
    }

    @Override
    void setRidePrice() {
        currentPrice = 7;
    }

    @Override
    double calculateRidePrice() {
        return (currentPrice+Pricing.getPricing(rideOrigin,terminal))*1.9;
    }
}
