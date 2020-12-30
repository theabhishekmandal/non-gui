package data_structures.array.dutch_national_flag;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;

/**
 * Given an array and a range[a, b], partition the array around the range such that array is divided in three parts.
 *  -   All the elements smaller than a comes first.
 *  -   All elements in range a to b come next.
 *  -   All elements greater than b appears in the end
 *
 *  Approach:
 *      -   assign two indices low and high, low pointing the first index and high pointing the end index
 *      -   now for each index check two conditions i.e
 *          -   if arr[i] < a; then swap and increment i and start
 *          -   if arr[i] > b; then swap and decrement end, don't increment i
 *          -   otherwise just increment i as arr[i] will be between a and b
 *
 *      -   Do this till i <= end
 */
public class ThreeWayPartitioning {
    public static void main(String[] args) {
        var list = Arrays.asList(
                new SimpleEntry<>(new int[]{1, 14, 5, 20, 4, 2, 54, 20, 87, 98, 3, 1, 32}, new SimpleEntry<>(10, 20)),
                new SimpleEntry<>(new int[]{76, 8, 75, 22, 59, 96, 30, 38, 36}, new SimpleEntry<>(44, 62))
        );
        for (var entry : list) {
            int[] arr = entry.getKey();
            int a = entry.getValue().getKey();
            int b = entry.getValue().getValue();
            System.out.println("a = " + a + " b = " + b + "\narray before partitioning  " + Arrays.toString(arr));
            partitionArray(arr, a, b);
            System.out.println("array after partitioning " + Arrays.toString(arr));
            System.out.println();
        }
    }

    private static void partitionArray(int[] arr, int a, int b) {
        int start = 0;
        int end = arr.length - 1;
        for (int i = 0; i <= end;) {
            if (arr[i] < a) {
                swap(arr, i, start);
                i++;
                start++;
            } else if (arr[i] > b) {
                swap(arr, i, end);
                end--;
            } else {
                i++;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
