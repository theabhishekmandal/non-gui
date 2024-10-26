package lowleveldesign.parkinglot.design.parkingStrategy;

import lowleveldesign.parkinglot.design.ParkingFloor;

public interface ParkingStrategy {
    String park(ParkingFloor[] parkingFloors, int vehicleType);
}
