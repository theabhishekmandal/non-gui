package lowleveldesign.parkinglot.design.singleThreaded;

import lowleveldesign.parkinglot.design.IParkingLot;

public class Main {
    public static void main(String[] args) {
        int[][][] parkingFloors = {
                {
                        {4, 4, 2, 2},
                        {2, 4, 2, 0},
                        {0, 2, 2, 2},
                        {4, 4, 4, 0}
                }
        };
        IParkingLot parkingLot = new ParkingLot();
        parkingLot.init(System.out, parkingFloors);
        print(parkingLot);

        IParkingLot parkingLot1 = new ParkingLot2();
        parkingLot1.init(System.out, parkingFloors);
        print(parkingLot1);


    }
    static void print(IParkingLot parkingLot) {
        String spotId = parkingLot.park(2, "bh234", "tkt4534", 0);
        System.out.println(parkingLot.searchVehicle("tkt4534"));
        System.out.println(parkingLot.searchVehicle("bh234"));
        System.out.println(parkingLot.getFreeSpotsCount(0, 2));
        System.out.println(parkingLot.getFreeSpotsCount(0, 4));
        System.out.println(parkingLot.removeVehicle(spotId));
        spotId = parkingLot.park(2, "bh234", "tkt4534", 1);
        System.out.println(parkingLot.getFreeSpotsCount(0, 2));
        System.out.println(parkingLot.getFreeSpotsCount(0, 4));
    }
}
