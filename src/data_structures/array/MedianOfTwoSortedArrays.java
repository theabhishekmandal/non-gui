package data_structures.array;

import java.util.Arrays;
import java.util.Random;
import java.util.function.IntFunction;

/**
 * The problem statement is that given two arrays of different sizes which are sorted in ascending order.
 * Find the median of two sorted arrays.
 * If
 *      m + n is odd then median is the middle element
 *      m + n is even then median is (n / 2 + n - 1 / 2) / 2
 * The first approach would be creating array of size m + n and arranging the elements in ascending order.
 * In that case the time taken would be O(m + n).
 *
 * Next approach would be to partitioning the two arrays such that, the total elements of x and y on left is
 * equal to total elements of x and y on the right. For eg:
 *
 *     x -> x1, x2, x3 | x4, x5, x6
 *     y -> y1, y2, y3, y4 | y5, y6, y7, y8
 *     x3 <= y5
 *     y4 <= x4
 *     if the above condition satisfies then we are at median of the lists,
 *     now
 *          if length of the list is odd then median would be max(x3, y4)
 *          if length of the list is even then median  would be avg(max(x3, y4), min(y5, x4))
 *
 *     if the above condition does not satisfies then check if x3 > y5, if yes
 *     then we are too much on the right side of x and will shift to left side by reducing the partitionx by 1
 *
 *     else if y4 > x4 then we are too much on the left side of x and will shift to right side by increasing the
 *     partitionx by 1
 *
 *     there may be a condition when the partitionx and partitiony might be at a position where there might be
 *     no elements then at that case we can use +INF and -INF.
 *     https://www.geeksforgeeks.org/median-two-sorted-arrays-different-sizes-ologminn-m/
 *
 *     Important points to note
 *     -    smaller length array should be used
 *     -    Here total length is calculated as (x + y + 1) as it works well in both even and odd lengths
 *     -    low = 0 and high = arr.length
 *     -    high and low values are calculated using partitionx only
 */
public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        Random random = new Random();
        IntFunction<int[]> randomSortedSup = size -> random.ints(1, 1000).limit(size).sorted().toArray();
        int n = 11;
        int[] first = randomSortedSup.apply(n);
        int m = 11;
        int[] second = randomSortedSup.apply(m);
        System.out.println("Array first = " + Arrays.toString(first) + "\nArray second = " + Arrays.toString(second));
        System.out.println(getMedianOfTwoSortedArrays2(first, second));
        System.out.println(getMedianOfTwoSortedArrays(first, second));
    }

    // complex time complexity is O(log(min(m, n))) space complexity O(1)
    private static float getMedianOfTwoSortedArrays(int[] first, int[] second) {
        if (first.length > second.length) {
            int[] temp = first;
            first = second;
            second = temp;
        }
        int x = first.length;
        int y = second.length;
        int low = 0;
        int high = x;
        int negInf = Integer.MIN_VALUE;
        int posInf = Integer.MAX_VALUE;
        while (low <= high) {
            int partitionx = low + ((high - low) >> 1);
            int partitiony = ((x + y + 1) >> 1) - partitionx;

            int maxLeftx = (partitionx == 0)? negInf : first[partitionx - 1];
            int minRightx = (partitionx == x)? posInf : first[partitionx];

            int maxLefty = (partitiony == 0)? negInf : second[partitiony - 1];
            int minRighty = (partitiony == y)? posInf : second[partitiony];

            if (maxLeftx <= minRighty && maxLefty <= minRightx) {
                if (((x + y) & 1) == 0) {
                    return (Math.max(maxLeftx, maxLefty) + Math.min(minRightx, minRighty)) / (float)2;
                } else {
                    return (float)Math.max(maxLeftx, maxLefty);
                }
            } else if (maxLeftx > minRighty) {
                high = partitionx - 1;
            } else if (maxLefty > minRightx) {
                low = partitionx + 1;
            }
        }
        throw new AssertionError("arrays are not sorted");
    }

    // simple time complexity O(m + n) and space Complexity is O(m + n)
    private static float getMedianOfTwoSortedArrays2(int[] arr, int[] brr) {
        int[] crr = new int[arr.length + brr.length];
        int i = 0;
        int k = 0;
        int j = 0;
        while (i < arr.length && j < brr.length) {
            if (arr[i] < brr[j]) {
                crr[k++] = arr[i++];
            } else {
                crr[k++] = brr[j++];
            }
        }
        while (i < arr.length) {
            crr[k++] = arr[i++];
        }

        while (j < brr.length) {
            crr[k++] = brr[j++];
        }

        System.out.println(Arrays.toString(crr));
        if (crr.length % 2 == 0) {
            return (crr[crr.length / 2] + crr[crr.length / 2 - 1]) / (float)2;
        } else {
            return (float)crr[crr.length / 2];
        }
    }
}
