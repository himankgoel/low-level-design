package parkingspot;

import lombok.Getter;
import parkingspot.exceptions.InvalidParkingSpotException;
import parkingspot.exceptions.ParkingSpotAlreadyFreeException;
import parkingspot.exceptions.ParkingSpotAlreadyOccupiedException;
import vehicles.Vehicle;
import vehicles.VehicleType;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ParkingSpot {

    @Getter
    private final String spotId;
    @Getter
    private final VehicleType accomodatedVehicleType;
    private Vehicle vehicle;
    @Getter
    private volatile boolean isAvailable;
    private final Lock lock = new ReentrantLock();

    public ParkingSpot(final String spotId, final VehicleType accomodatedVehicleType) {
        this.spotId = spotId;
        this.accomodatedVehicleType = accomodatedVehicleType;
        this.isAvailable = true;
    }

    public boolean tryPark(Vehicle vehicle) {
        if(!canFitVehicle(vehicle)) {
            throw new InvalidParkingSpotException("Cannot fit vehicle here.");
        }
        if(lock.tryLock()) {
            try {
                lock.lock();
                if (!isAvailable) {
                    throw new ParkingSpotAlreadyOccupiedException("Spot already occupied.");
                }
                isAvailable = false;
                this.vehicle = vehicle;
                return true;
            } finally {
                lock.unlock();
            }
        }
        return false;
    }

    public void unparkVehicle() {
        if(isAvailable) {
            throw new ParkingSpotAlreadyFreeException("Already free");
        }
        lock.lock();
        try {
            this.vehicle = null;
            isAvailable = true;
        } finally {
            lock.unlock();
        }
    }

    public boolean canFitVehicle(Vehicle vehicle) {
        return vehicle.getType().equals(accomodatedVehicleType);
    }
}
