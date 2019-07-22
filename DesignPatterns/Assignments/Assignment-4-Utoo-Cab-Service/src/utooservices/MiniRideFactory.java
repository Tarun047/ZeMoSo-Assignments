package utooservices;

public class MiniRideFactory extends RideFactory {
    @Override
    void setCurrentCat() {
        currentCat=CabCategory.MINI;
    }

    @Override
    void setRidePrice() {
        currentPrice=8;
    }

    @Override
    double calculateRidePrice() {
        return (currentPrice+Pricing.getPricing(rideOrigin,terminal))*1.2;
    }
}
