package lowleveldesign.parkinglot.design.multiThreaded;

import lowleveldesign.parkinglot.design.IParkingLot;
import lowleveldesign.parkinglot.design.multiThreaded.parkingStrategy.LowestHeightParkingStrategy;
import lowleveldesign.parkinglot.design.multiThreaded.parkingStrategy.MaxHeightParkingStrategy;
import lowleveldesign.parkinglot.design.multiThreaded.parkingStrategy.ParkingStrategy;

import java.io.PrintStream;
import java.util.List;

public class ParkingLot implements IParkingLot {

    private ParkingFloor[] parkingFloors;
    private final SearchManager searchManager = new SearchManager();
    private final List<ParkingStrategy> parkingStrategyList =
            List.of(new LowestHeightParkingStrategy(), new MaxHeightParkingStrategy());

    @Override
    public void init(PrintStream printStream, int[][][] parking) {
        parkingFloors = new ParkingFloor[parking.length];
        for (int i = 0; i < parking.length; i++) {
            parkingFloors[i] = new ParkingFloor(i, parking[i]);
        }
    }

    @Override
    public String park(int vehicleType, String vehicleNumber, String ticketId, int parkingStrategy) {
        synchronized (this) {
            if (isSpaceAvailable(vehicleNumber, ticketId)) {
                String spotId = parkingStrategyList.get(parkingStrategy)
                        .park(this.parkingFloors, vehicleType);

                if (!spotId.isEmpty()) {
                    searchManager.save(vehicleNumber, ticketId, spotId);
                    return spotId;
                }
                return "cannot be parked";
            }
        }
        return "already parked";
    }

    public boolean isSpaceAvailable(String vehicleNumber, String ticketId) {
        return (searchManager.search(ticketId).isEmpty() || searchManager.search(vehicleNumber).isEmpty());
    }

    @Override
    public boolean removeVehicle(String spotId) {
        if (searchManager.remove(spotId)) {
            String[] strings = spotId.split("-");
            int floor = Integer.parseInt(strings[0]);
            int row = Integer.parseInt(strings[1]);
            int column = Integer.parseInt(strings[2]);
            parkingFloors[floor].remove(row, column);
            return true;
        }
        return false;
    }

    @Override
    public String searchVehicle(String query) {
        return searchManager.search(query);
    }

    @Override
    public int getFreeSpotsCount(int floor, int vehicleType) {
        return this.parkingFloors[floor].getFreeSpace(vehicleType);
    }
}
