package lowleveldesign.parkinglot.design.singleThreaded.parkingStrategy;

import lowleveldesign.parkinglot.design.singleThreaded.ParkingFloor;

public class MaxHeightParkingStrategy implements ParkingStrategy {

    @Override
    public String park(ParkingFloor[] parkingFloors, int vehicleType) {
        int maxParKingSpace = Integer.MIN_VALUE;
        int floor = -1;
        for (int i = 0; i < parkingFloors.length; i++) {
            int freeSpace = parkingFloors[i].getFreeSpace(vehicleType);
            if (maxParKingSpace == freeSpace) {
                break;
            } else if (maxParKingSpace < freeSpace) {
                maxParKingSpace = freeSpace;
                floor = i;
            }
        }
        if (floor != -1) {
            String park = parkingFloors[floor].park(vehicleType);
            if (!park.isEmpty()) {
                return floor + "-" + park;
            }
        }
        return "";
    }
}
