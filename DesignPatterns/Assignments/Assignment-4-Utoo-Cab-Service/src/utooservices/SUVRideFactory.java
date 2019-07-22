package utooservices;

public class SUVRideFactory extends RideFactory {
    @Override
    void setCurrentCat() {
        currentCat=CabCategory.SUV;
    }

    @Override
    void setRidePrice() {
        currentPrice = 15;
    }

    @Override
    double calculateRidePrice() {
        return (Pricing.getPricing(rideOrigin,terminal)+1.2*currentPrice)*1.3;
    }
}
