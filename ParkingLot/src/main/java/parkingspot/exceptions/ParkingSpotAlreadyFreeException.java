package parkingspot.exceptions;

public class ParkingSpotAlreadyFreeException extends RuntimeException {
    public ParkingSpotAlreadyFreeException(String message) {
        super(message);
    }
}
