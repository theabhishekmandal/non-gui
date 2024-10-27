package lowleveldesign.parkinglot.design.multiThreaded;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class ParkingFloor {
    private final int[][] parkingSpots;
    private final boolean[][] reserved;
    private final Map<Integer, Integer> freeSpaceCount;
    private static final int TWO_WHEELER = 2;
    private static final int FOUR_WHEELER = 4;
    private final int floorNumber;

    public ParkingFloor(int floorNumber, int[][] parkingFloor) {
        this.floorNumber = floorNumber;
        this.parkingSpots = parkingFloor;
        this.freeSpaceCount = new HashMap<>();
        this.reserved = new boolean[parkingFloor.length][parkingFloor[0].length];
        for (int i = 0; i < parkingSpots.length; i++) {
            for (int j = 0; j < parkingSpots[i].length; j++) {
                int value = parkingSpots[i][j];
                if (value == TWO_WHEELER || value == FOUR_WHEELER) {
                    reserved[i][j] = false;
                    freeSpaceCount.merge(value, 1, Integer::sum);
                }
            }
        }
        print();
    }

    public void remove(int row, int column) {
        synchronized (this) {
            if (reserved[row][column]) {
                reserved[row][column] = false;
                freeSpaceCount.merge(parkingSpots[row][column], 1, Integer::sum);
                print();
            }
        }
    }

    public String park(int vehicleType) {

        int freeSpaceCount;
        freeSpaceCount = this.getFreeSpace(vehicleType);
        if (freeSpaceCount == 0) {
            return "";
        }
        for (int i = 0; i < parkingSpots.length; i++) {
            for (int j = 0; j < parkingSpots[i].length; j++) {
                int vehicleType2 = parkingSpots[i][j];
                if (vehicleType2 == vehicleType && !reserved[i][j]) {
                    this.freeSpaceCount.merge(vehicleType, -1, Integer::sum);
                    reserved[i][j] = true;
                    print();
                    return i + "-" + j;
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
        String printString = """
                    floorNumber = %s,
                    currentThread = %s,
                    parkingSpots = %s,
                    freeSpaceCount = %s,
                    reservedArea = %s
                """.formatted(floorNumber,
                Thread.currentThread().getName(),
                Arrays.deepToString(parkingSpots), freeSpaceCount, Arrays.deepToString(reserved));

        System.out.println(printString);
    }
}
