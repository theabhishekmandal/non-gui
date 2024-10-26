package lowleveldesign.parkinglot.design;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ParkingFloor {
    private final int[][] parkingSpots;
    private final boolean[][] reserved;
    private final Map<Integer, Integer> freeSpaceCount;
    private static final int TWO_WHEELER = 2;
    private static final int FOUR_WHEELER = 4;

    public ParkingFloor(int[][] parkingFloor) {
        this.parkingSpots = parkingFloor;
        this.freeSpaceCount = new HashMap<>();
        this.reserved = new boolean[parkingFloor.length][parkingFloor[0].length];
        for (int i = 0; i < parkingSpots.length; i++) {
            for (int j = 0; j < parkingSpots[i].length; j++) {
                int value = parkingSpots[i][j];
                if (value == TWO_WHEELER || value == FOUR_WHEELER) {
                    reserved[i][j] = false;
                    freeSpaceCount.compute(value, (k, oldValue) -> oldValue == null ? 1 : oldValue + 1);
                }
            }
        }
        print();
    }

    public void remove(int row, int column) {
        if (reserved[row][column]) {
            reserved[row][column] = false;
            freeSpaceCount.compute(parkingSpots[row][column],
                    (k, oldValue) -> oldValue == null ? 1 : oldValue + 1);
            print();
        }
    }

    public String park(int vehicleType) {
        Integer freeSpaceCount = this.freeSpaceCount.get(vehicleType);

        if (freeSpaceCount != null && freeSpaceCount > 0) {

            this.freeSpaceCount.compute(vehicleType, (k, oldValue) -> oldValue == null ? 0 : oldValue - 1);

            for (int i = 0; i < parkingSpots.length; i++) {
                for (int j = 0; j < parkingSpots[i].length; j++) {

                    int vehicleType2 = parkingSpots[i][j];

                    if (vehicleType2 == vehicleType) {
                        reserved[i][j] = true;
                        print();
                        return i + "-" + j;
                    }
                }
            }
        }
        return "";
    }

    public int getFreeSpace(int vehicleType) {
        Integer i = freeSpaceCount.get(vehicleType);
        return i == null ? 0 : i;
    }

    private void print() {
        System.out.println("parking area = " + Arrays.deepToString(parkingSpots));
        System.out.println("freeSpace count = " + freeSpaceCount);
        System.out.println("reservedArray = " + Arrays.deepToString(reserved));
    }
}
