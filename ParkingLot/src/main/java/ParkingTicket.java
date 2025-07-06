import feestrategy.FeeStrategy;
import parkingspot.ParkingSpot;
import vehicles.Vehicle;

import java.util.UUID;

public class ParkingTicket {

    final String ticketId;
    final FeeStrategy feeStrategy;
    final ParkingSpot parkingSpot;
    final long timeCreated;
    long timeClosed;
    final String vehicleRegistrationNumber;

    public ParkingTicket(FeeStrategy feeStrategy, ParkingSpot spot, Vehicle vehicle) {
        this.ticketId = UUID.randomUUID().toString();
        this.timeCreated = System.currentTimeMillis();
        this.feeStrategy = feeStrategy;
        this.parkingSpot = spot;
        this.vehicleRegistrationNumber = vehicle.getRegistrationNumber();
    }

    public void close() {
        this.timeClosed = System.currentTimeMillis();
    }

    public double calculateParkingFees() {
        return feeStrategy.calculateFee(timeCreated, timeClosed == 0 ? System.currentTimeMillis() : timeClosed);
    }
}
