package lowleveldesign.parkinglot.design.multiThreaded.parkingStrategy;


import lowleveldesign.parkinglot.design.multiThreaded.ParkingFloor;

public interface ParkingStrategy {
    String park(ParkingFloor[] parkingFloors, int vehicleType);
}
