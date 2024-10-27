package lowleveldesign.parkinglot.design.singleThreaded.parkingStrategy;

import lowleveldesign.parkinglot.design.singleThreaded.ParkingFloor;

public class LowestHeightParkingStrategy implements ParkingStrategy {

    @Override
    public String park(ParkingFloor[] parkingFloors, int vehicleType) {
        for (int i = 0; i < parkingFloors.length; i++) {
            String park = parkingFloors[i].park(vehicleType);
            if (!park.isEmpty()) {
                return i + "-" + park;
            }
        }
        return "";
    }
}
