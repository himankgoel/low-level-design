package vehicles;

public class LargeVehicle implements Vehicle {

    final String registrationNumber;
    public LargeVehicle(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    @Override
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    @Override
    public VehicleType getType() {
        return VehicleType.Large;
    }
}
