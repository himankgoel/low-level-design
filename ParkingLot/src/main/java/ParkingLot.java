import feestrategy.FlatFeeStrategy;

import parkingspot.ParkingSpot;
import vehicles.Vehicle;
import vehicles.VehicleType;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {

    private final List<ParkingLevel> parkingLevels;
    private final Map<String, ParkingTicket> registrationToTicketMap = new ConcurrentHashMap<>();
    private final Map<VehicleType, Integer> availableParkingSpots = new ConcurrentHashMap<>();

    public ParkingLot(List<ParkingLevel> parkingLevels) {
        this.parkingLevels = parkingLevels;
        computeAvailableSpots();
    }

    private void computeAvailableSpots() {
        for(final ParkingLevel level: this.parkingLevels) {
            for(final ParkingSpot spot: level.getSpots()) {
                final VehicleType key = spot.getAccomodatedVehicleType();
                if(!availableParkingSpots.containsKey(key)) {
                    availableParkingSpots.put(key, 0);
                }
                availableParkingSpots.computeIfPresent(key, (_, v) -> v+1);
            }
        }
    }

    public ParkingTicket parkVehicle(Vehicle vehicle) {
        for(final ParkingLevel level: parkingLevels) {
            try {
                ParkingSpot spot = level.parkVehicle(vehicle);
                if(spot != null) {
                    ParkingTicket ticket = new ParkingTicket(new FlatFeeStrategy(25), spot, vehicle);
                    registrationToTicketMap.put(vehicle.getRegistrationNumber(), ticket);
                    availableParkingSpots.computeIfPresent(vehicle.getType(), (_, v) -> v-1);
                    return ticket;
                }
            } catch (Exception ex) {
                System.out.println("Failed to park vehicle at this level due to " + ex);
            }
        }
        throw new RuntimeException("No available spots.");
    }

    public Map<VehicleType, Integer> getAvailableSpots() {
        return availableParkingSpots;
    }

    private ParkingTicket unparkVehicle(Vehicle vehicle) {
        ParkingTicket ticket = registrationToTicketMap.get(vehicle.getRegistrationNumber());
        ticket.parkingSpot.unparkVehicle();
        registrationToTicketMap.remove(vehicle.getRegistrationNumber());
        availableParkingSpots.computeIfPresent(vehicle.getType(), (_, v) -> v+1);
        return ticket;
    }

    public double unparkVehicleAndCalculateFees(Vehicle vehicle) {
        ParkingTicket parkingTicket = unparkVehicle(vehicle);
        parkingTicket.close();
        return parkingTicket.calculateParkingFees();
    }
}
