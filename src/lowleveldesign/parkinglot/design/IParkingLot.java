package lowleveldesign.parkinglot.design;

import java.io.PrintStream;

public interface IParkingLot {
    void init(PrintStream printStream, int[][][] parking);
    String park(int vehicleType, String vehicleNumber, String ticketId, int parkingStrategy);
    boolean removeVehicle(String spotId);
    String searchVehicle(String query);
    int getFreeSpotsCount(int floor, int vehicleType);
}


/*
    IParkingLot
        -   initialize the ParkingLot
            -   how many floor, and how many valid parking spots it has

        -   park the vehicle using
            -   2 or 4 wheeler vehicle type
            -   vehicleNumber
            -   ticketId
            -   parkingStrategy
                -   0
                    -   lowest floor where parking is available.
                -   1
                    -   lowest floor where max number of parking is available
        -   returns the spotId

        -   remove the vehicle using spotId.

        -   search for vehicle using ticketNumber or vehicleNumber

        -   get number of free space per floor



Data Models
    -   ParkingLot does not require any other ParkingBuilding.
    -   ParkingFloor
        -   int[][] parkingSpots;
        -   boolean[][] reserved;
        -   count per vehicle

        -   methods
            -   initialize every floor and reserved will be false
            -   park using row and column, reduce the count of free space
            -   remove from parking area, increase the count of free space
            -   get number of free spaces.

Service classes
    -   ParkingStrategy
        -   uses different parking strategy to park vehicles.
        -   LowFloorStrategy
            -   check which floor is free and assign a spot and return spotId.
        -   MaxFreeSpaceStrategy
            -   check which floor is having max free space and park it there and return spotId.
        -   park(Parking[] floor, int vehicleType)

    -   SearchManger
            -   stores/removes ticketId, vehicleNumber to spotId.
            -   finds using spotId
            -   spotId -> ticketId and vehicleNumber

    -   ParkingManager
        -   [LowFloorStrategy, MaxFreeSpaceStrategy]
    -  ParkingLot implements IParkingLot
        -   ParkingFloor[] parkingFloors;


        -   init(int[][][] parkingFloors)
            -   initialize every Parking Floor
        -   park(vehicleNumber, ticketNumber, vehicleType, strategy)
            -   park on the correct floor based on strategy
                -   based on strategy
                    -   spotId = LowParkingStrategy/MaxFreeParkingStrategy.park(parkingFloors, vehicleType)
            -   spotId needs to be saved along with ticketNumber and vehicleNumber

        -   removeVehicle(spotId)
            -   removes from SearchManger data store and removes entry from Parking floor
                -   split into floor, row, column and search for in parking floors
                -   un reserve the spot
                -   remove entry from SearchManager index

        -   search(ticketNumber/vehicleNumber)
            -   search for vehicle using either of the two and return spotId.

        -   get number of free space
            -   parkingfloors can be used for this.




 */