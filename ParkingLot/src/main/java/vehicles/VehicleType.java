package vehicles;

public enum VehicleType {

    ExtraLarge(500),
    Large(200),
    Medium(100),
    Compact(50),
    Bike(10);

    private final int size;

    VehicleType(int size) {
        this.size = size;
    }
    public int getSize() {
        return size;
    }
}
