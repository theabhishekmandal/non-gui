package lowleveldesign.parkinglot.design.multiThreaded.parkingStrategy;


import lowleveldesign.parkinglot.design.multiThreaded.ParkingFloor;

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
