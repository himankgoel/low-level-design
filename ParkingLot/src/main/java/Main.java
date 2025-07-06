import parkingspot.ParkingSpot;
import vehicles.VehicleType;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create multiple levels, each with 5 compact spots
        int numLevels = 2;
        int spotsPerLevel = 5;
        List<ParkingLevel> levels = new ArrayList<>();
        for (int l = 0; l < numLevels; l++) {
            List<ParkingSpot> spots = new ArrayList<>();
            for (int i = 0; i < spotsPerLevel; i++) {
                spots.add(new ParkingSpot("L" + l + "-C-" + i, VehicleType.Compact));
            }
            levels.add(new ParkingLevel(spots));
        }
        // Initialize the parking lot with the levels
        ParkingLot lot = new ParkingLot(levels);
        System.out.println(lot.getAvailableSpots());
        int numThreads = 100;
        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            final int vehicleId = i;
            threads[i] = new Thread(() -> {
                try {
                    // Create a vehicle with unique id
                    vehicles.Vehicle vehicle = new vehicles.CompactVehicle("CAR-" + vehicleId);
                    // Try to park
                    ParkingTicket ticket = lot.parkVehicle(vehicle);
                    System.out.println(Thread.currentThread().getName() + " parked: " + vehicle.getRegistrationNumber());
                    // Simulate some parking duration
                    Thread.sleep((long) (Math.random() * 1000));
                    System.out.println(Thread.currentThread().getName() + lot.getAvailableSpots());
                    // Try to unpark
                    System.out.println("Pay up:" + lot.unparkVehicleAndCalculateFees(vehicle) + " Duration:" + (ticket.timeClosed - ticket.timeCreated));
                    System.out.println(Thread.currentThread().getName() + " unparked: " + vehicle.getRegistrationNumber());
                    System.out.println(lot.getAvailableSpots());
                } catch (Exception e) {
                    System.out.println(Thread.currentThread().getName() + " error: " + e.getMessage());
                }
            }, "VehicleThread-" + i);
        }

        // Start all threads
        for (Thread t : threads) {
            t.start();
        }
        // Wait for all threads to finish
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("All parking operations completed.");
    }
}
