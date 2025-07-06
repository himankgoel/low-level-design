package parkingspot.exceptions;

public class ParkingSpotAlreadyOccupiedException extends RuntimeException {
    public ParkingSpotAlreadyOccupiedException(String message) {
        super(message);
    }
}
