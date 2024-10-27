package lowleveldesign.parkinglot.design.singleThreaded.parkingStrategy;

import lowleveldesign.parkinglot.design.singleThreaded.ParkingFloor;

public interface ParkingStrategy {
    String park(ParkingFloor[] parkingFloors, int vehicleType);
}
