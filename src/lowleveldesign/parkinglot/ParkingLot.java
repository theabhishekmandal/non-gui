package lowleveldesign.parkinglot;

import lowleveldesign.parkinglot.design.IParkingLot;

import java.io.PrintStream;
import java.util.*;

public class ParkingLot implements IParkingLot {
    private int[][][] parking;
    private int[][] countPerFloor;
    private PrintStream out;
    private final Map<String, List<String>> parkingLocationToVehicleNumberAndTicketNumber = new HashMap<>();
    private final Map<String, String> bookedTicketId = new HashMap<>();
    private final Map<String, String> bookedVehicleNumber = new HashMap<>();
    private static final int TWO_WHEELER = 2;
    private static final int FOUR_WHEELER = 4;


    private void initialize() {
        for (int i = 0; i < parking.length; i++) {
            for (int j = 0; j < parking[i].length; j++) {
                for (int k = 0; k < parking[i][j].length; k++) {
                    int parkingLocation = parking[i][j][k];
                    // a parking location can be zero meaning, no one can park
                    if (parkingLocation == TWO_WHEELER) {
                        countPerFloor[i][0]++;
                    } else if (parkingLocation == FOUR_WHEELER) {
                        countPerFloor[i][1]++;
                    }
                }
            }
        }
    }

    @Override
    public void init(PrintStream printStream, int[][][] parking) {
        this.parking = parking;
        this.out = printStream;
        this.countPerFloor = new int[this.parking.length][2];
        initialize();
        out.println(Arrays.deepToString(parking));
        out.println(Arrays.deepToString(countPerFloor));
    }

    public String park(int vehicleType, String vehicleNumber, String ticketId, int parkingStrategy) {
        // search for vehicleType
        // check which strategy to use
        // save ticketId
        String spotId = "";
        if (parkingStrategy == 0) {

            for (int i = 0; i < parking.length; i++) {
                int countPerFloor_ = countPerFloor[i][(vehicleType >> 1) - 1];
                if (countPerFloor_ == 0) {
                    continue;
                }
                for (int j = 0; j < parking[i].length; j++) {
                    for (int k = 0; k < parking[i][j].length; k++) {
                        String parkingLot = i + "-" + j + "-" + k;
                        if (parking[i][j][k] == vehicleType &&
                                !parkingLocationToVehicleNumberAndTicketNumber.containsKey(parkingLot)) {
                            spotId = parkingLot;

                            updateAll(vehicleType, vehicleNumber, ticketId, parkingLot, spotId, i);
                            return spotId;
                        }
                    }
                }
            }
        } else if (parkingStrategy == 1) {
            int floor = -1;
            int max = Integer.MIN_VALUE;
            int vehicleIndex = (vehicleType >> 1) - 1;
            for (int i = 0; i < countPerFloor.length; i++) {
                int count = countPerFloor[i][vehicleIndex];
                if (count > max) {
                    max = count;
                    floor = i;
                } else if (count == max) {
                    break;
                }
            }
            if (floor != -1) {
                for (int j = 0; j < parking[floor].length; j++) {
                    for (int k = 0; k < parking[floor][j].length; k++) {
                        String parkingLot = floor + "-" + j + "-" + k;
                        if (parking[floor][j][k] == vehicleType &&
                                !parkingLocationToVehicleNumberAndTicketNumber.containsKey(parkingLot)) {
                            spotId = parkingLot;
                            updateAll(vehicleType, vehicleNumber, ticketId, parkingLot, spotId, floor);
                            return spotId;
                        }
                    }
                }
            }
        }

        return spotId;
    }

    private void updateAll(int vehicleType, String vehicleNumber, String ticketId, String parkingLot, String spotId, int floor) {
        parkingLocationToVehicleNumberAndTicketNumber.put(parkingLot, List.of(ticketId, vehicleNumber));
        bookedVehicleNumber.put(vehicleNumber, spotId);
        bookedTicketId.put(ticketId, spotId);
        countPerFloor[floor][(vehicleType >> 1) - 1]--;
        out.println(parkingLocationToVehicleNumberAndTicketNumber);
        out.println(bookedVehicleNumber);
        out.println(bookedTicketId);
        out.println(Arrays.deepToString(countPerFloor));
    }

    public boolean removeVehicle(String spotId) {
        if (parkingLocationToVehicleNumberAndTicketNumber.containsKey(spotId)) {
            List<String> remove = parkingLocationToVehicleNumberAndTicketNumber.remove(spotId);
            bookedVehicleNumber.remove(remove.get(1));
            bookedTicketId.remove(remove.get(0));

            String[] spotInfo = spotId.split("-");
            int floor = Integer.parseInt(spotInfo[0]);
            int row = Integer.parseInt(spotInfo[1]);
            int column = Integer.parseInt(spotInfo[2]);
            int vehicleType = parking[floor][row][column];
            countPerFloor[floor][(vehicleType >> 1) - 1]++;

            out.println(parkingLocationToVehicleNumberAndTicketNumber);
            out.println(bookedVehicleNumber);
            out.println(bookedTicketId);
            out.println(Arrays.deepToString(countPerFloor));
            return true;
        }
        return false;
    }

    public String searchVehicle(String query) {
        String result = bookedTicketId.get(query);
        if (result == null) {
            return bookedVehicleNumber.get(query);
        }
        return result;
    }

    public int getFreeSpotsCount(int floor, int vehicleType) {
        return countPerFloor[floor][(vehicleType >> 1) - 1];
    }
}
