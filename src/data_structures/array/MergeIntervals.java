package data_structures.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 *
 * Example 1:
 *
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * Example 2:
 *
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 *
 * Approach
 *  -   sort the interval on the basis of lower interval
 *  -   now as the array is sorted we only have to check whether the lower value of
 *      current element is greater than the higher value of previous element i.e
 *          -   lowPrevious < lowCurrent < highPrevious -> we are inside the interval calculate new interval
 *          -   Otherwise the intervals are disjoint and the current element to list
 */
public class MergeIntervals {
    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {1, 3},
                {2, 6},
                {8, 10},
                {15, 18}
        };
        System.out.println("Input array " + Arrays.deepToString(arr));
        System.out.println("Output Array after merging intervals " + Arrays.deepToString(merge(arr)));
    }

    private static int[][] merge(int[][] arr) {
        if(arr.length == 0) return new int[][]{};
        Arrays.sort(arr, Comparator.comparingInt(x -> x[0]));
        int low = arr[0][0];
        int high = arr[0][1];
        List<int[]> list = new ArrayList<>();
        for(int i = 1; i < arr.length; i++) {
            int first = arr[i][0];
            int second = arr[i][1];
            if(first <= high) {
                high = Math.max(high, second);
            }
            else {
                list.add(new int[]{low, high});
                low = first;
                high = second;
            }
        }
        list.add(new int[]{low, high});
        return list.toArray(new int[list.size()][]);
    }
}
