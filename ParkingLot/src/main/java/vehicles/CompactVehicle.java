package vehicles;

public class CompactVehicle implements Vehicle {

    final String registrationNumber;
    public CompactVehicle(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }
    @Override
    public VehicleType getType() {
        return VehicleType.Compact;
    }
}
