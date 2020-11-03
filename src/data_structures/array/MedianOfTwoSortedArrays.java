package data_structures.array;

import java.util.Scanner;

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
 */
public class MedianOfTwoSortedArrays {
    private static float getMedianOfTwoSortedArrays(int[] first, int[] second) throws Exception{
        if(first.length > second.length) return getMedianOfTwoSortedArrays(second, first);
        int x = first.length;
        int y = second.length;
        int low = 0;
        int high = x;
        while(low <= high){
            int partitionx = low + ((high - low) >> 1);
            int partitiony = ((x + y + 1) >> 1) - partitionx;

            int maxLeftx = (partitionx == 0)? (int)-1e9 : first[partitionx - 1];
            int minRightx = (partitionx == x)? (int)1e9 : first[partitionx];

            int maxLefty = (partitiony == 0)? (int)-1e9 : second[partitiony - 1];
            int minRighty = (partitiony == y)? (int)1e9 : second[partitiony];

            if(maxLeftx <= minRighty && maxLefty <= minRightx){
                if(((x + y) & 1) == 0){
                    return (Math.max(maxLeftx, maxLefty) + Math.min(minRightx, minRighty)) / (float)2;
                }
                else
                    return (float)Math.max(maxLeftx, maxLefty);
            }
            else if(maxLeftx > minRighty){
                high = partitionx - 1;
            }
            else if(maxLefty > minRightx){
                low = partitionx + 1;
            }
        }
        throw new Exception("arrays are not sorted");
    }
    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] first = new int[n];
        for(int i = 0; i < first.length; i++) first[i] = s.nextInt();
        int m = s.nextInt();
        int[] second = new int[m];
        for(int i = 0; i < second.length; i++) second[i] = s.nextInt();
        System.out.println(getMedianOfTwoSortedArrays(first, second));
    }
}
