import lombok.Getter;
import parkingspot.ParkingSpot;
import vehicles.Vehicle;

import java.util.List;

@Getter
public class ParkingLevel {

    private final List<ParkingSpot> spots;

    public ParkingLevel(List<ParkingSpot> spots) {
        this.spots = spots;
    }

    public ParkingSpot parkVehicle(Vehicle vehicle) {
        for(final ParkingSpot spot: spots) {
            if(spot.canFitVehicle(vehicle) && spot.tryPark(vehicle)) {
                return spot;
            }
        }
        throw new RuntimeException("NoParkingSpotAvailableException");
    }
}
