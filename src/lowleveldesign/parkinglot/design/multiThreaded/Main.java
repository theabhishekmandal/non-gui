package lowleveldesign.parkinglot.design.multiThreaded;

import utility.StopWatch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Initialize ParkingLot
        ParkingLot parkingLot = new ParkingLot();
        int[][][] parkingStructure = {
                { // Floor 0
                        {4, 4, 2, 2},  // Row 0
                        {2, 4, 2, 0},  // Row 1
                        {0, 2, 2, 2},  // Row 2
                        {4, 4, 4, 0}
//                }// Row 3
                }
//                ,
//                { // Floor 1
//                        {0, 0, 4, 4},  // Row 0
//                        {4, 0, 2, 2},  // Row 1
//                        {2, 0, 0, 0},  // Row 2
//                        {4, 4, 0, 4}   // Row 3
//                },
//                { // Floor 2
//                        {2, 2, 2, 0},  // Row 0
//                        {0, 0, 0, 0},  // Row 1 (Empty)
//                        {2, 4, 4, 0},  // Row 2
//                        {4, 4, 4, 2}   // Row 3
//                }
        };
        parkingLot.init(System.out, parkingStructure);

        // Test multithreading scenarios
//        testConcurrentParking(parkingLot);
//        testConcurrentRemove(parkingLot);
        testMixedOperations(parkingLot);
    }

    private static void testConcurrentParking(ParkingLot parkingLot) throws InterruptedException {
        System.out.println("\n--- Test Concurrent Parking ---");

        ExecutorService executor = Executors.newFixedThreadPool(10);

        StopWatch stopWatch = new StopWatch();
        stopWatch.startTime();

        for (int i = 1; i <= 10; i++) {
            final int vehicleId = i;
            executor.execute(() -> {
                String ticketId = "T" + vehicleId;
                String spotId = parkingLot.park(2,
                        "V" + vehicleId, ticketId, 0);
                System.out.println("Parked Vehicle " + vehicleId + " at spot " + spotId);

            });
        }
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
        stopWatch.stopTime();
        System.out.println(stopWatch.getTimeInMillis());
    }

    private static void testConcurrentRemove(ParkingLot parkingLot) throws InterruptedException {
        System.out.println("\n--- Test Concurrent Remove ---");

        // Add vehicles first
        String ticket1 = parkingLot.park(4, "V11", "T11", 0);
        String ticket2 = parkingLot.park(4, "V12", "T12", 0);
        String ticket3 = parkingLot.park(4, "V12", "T12", 0);

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(() -> {
            boolean removed = parkingLot.removeVehicle(ticket1);
            System.out.println("Removed Vehicle at " + ticket1 + ": " + removed);
        });
        executor.execute(() -> {
            boolean removed = parkingLot.removeVehicle(ticket2);
            System.out.println("Removed Vehicle at " + ticket2 + ": " + removed);
        });

        executor.execute(() -> {
            boolean removed = parkingLot.removeVehicle(ticket3);
            System.out.println("Removed Vehicle at " + ticket3 + ": " + removed);
        });
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
    }

    private static void testMixedOperations(ParkingLot parkingLot) throws InterruptedException {
        System.out.println("\n--- Test Mixed Operations ---");

        ExecutorService executor = Executors.newFixedThreadPool(6);

        // Simulate parking
        for (int i = 21; i <= 25; i++) {
            final int vehicleId = i;
            executor.execute(() -> {
                String ticketId = "T" + vehicleId;
                String spotId = parkingLot.park(4, "V" + vehicleId, ticketId, 1);
                System.out.println("Parked Vehicle " + vehicleId + " at spot " + spotId);
                boolean removed = parkingLot.removeVehicle("0-0-1");
                System.out.println("Removed Vehicle at 0-0-1: " + removed);
            });
        }

//        // Simulate removal
//        executor.execute(() -> {
//            boolean removed = parkingLot.removeVehicle("0-0-1");
//            System.out.println("Removed Vehicle at 0-0-1: " + removed);
//            removed = parkingLot.removeVehicle("0-0-1");
//            System.out.println("Removed Vehicle at 0-0-1: " + removed);
//            removed = parkingLot.removeVehicle("0-0-1");
//            System.out.println("Removed Vehicle at 0-0-1: " + removed);
//            removed = parkingLot.removeVehicle("0-0-1");
//            System.out.println("Removed Vehicle at 0-0-1: " + removed);
//            removed = parkingLot.removeVehicle("0-0-1");
//            System.out.println("Removed Vehicle at 0-0-1: " + removed);
//            removed = parkingLot.removeVehicle("0-0-1");
//            System.out.println("Removed Vehicle at 0-0-1: " + removed);
//        });

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
    }
}