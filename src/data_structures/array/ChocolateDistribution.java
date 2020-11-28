package data_structures.array;

import java.util.Arrays;
import java.util.List;

/**
 * Given an array of n integers where each value represents the number of chocolates in a packet.
 * Each packet can have a variable number of chocolates.
 * There are m students, the task is to distribute chocolate packets such that:
 *    - Each student gets one packet
 *    - The difference between the number of chocolates in the packet with maximum chocolates and packet with minimum
 *      chocolates given to the students is minimum.
 *      Basically, the packet with maximum chocolates and a packet with minimum chocolates should be
 *      minimum.
 *
 *  eg: arr = [3, 4, 1, 9, 56, 7, 9, 12] and m = 5, so the 5 packets are [3, 4, 7, 9, 9] and diff is
 *  9 - 3 = 6
 *
 *  Approach:
 *      - For the given array first sort the elements in ascending order
 *      - then for every element i, check the diff between arr[i + m - 1] - arr[i]
 *      - out of all the differences return the minimum one.
 */
public class ChocolateDistribution {
    public static void main(String[] args) {
        List<Pair<long[], int[]>> inputList = Arrays.asList(
                Pair.of(new long[]{3, 4, 1, 9, 56, 7, 9, 12}, new int[]{5}),
                Pair.of(new long[]{7, 3, 2, 4, 9, 12, 56}, new int[]{3})
        );
        for(var pair : inputList) {
            System.out.println("Input array " + Arrays.toString(pair.getFirst()) + " m = " + pair.getSecond()[0]);
            System.out.println("diff between maxValue and minValue which should be minimum is = " +
                    getDiffFast(pair.getFirst(), pair.getSecond()[0]));
            System.out.println();
        }
    }

    private static long getDiffFast(long[] arr, int m) {
        if(m == 0 || arr == null || arr.length == 0) {
            return 0;
        }
        Arrays.sort(arr);
        if(arr.length < m) {
            return -1;
        }

        long minDiff = Long.MAX_VALUE;
        for(int i = 0; i + m - 1 < arr.length; i++) {
            minDiff = Math.min(minDiff, arr[i + m - 1] - arr[i]);
        }
        return minDiff;
    }
}
