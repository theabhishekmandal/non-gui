package data_structures.array;

import java.util.Arrays;
import java.util.List;

/**
 * given arr = [1,8,6,2,5,4,8,3,7], which describes the height of each bar, find the rectangle which covers the max area
 * ans = [8, 7] because minimum height is 7 and distance between them is (8 - 1) = 7, so 7 * 7 =  49
 *
 * Approach
 *  -   The rectangle area is affected by mostly by the smaller height rectangle, so:
 *  -   assign  i = 0 and j = array.length - 1, calculate the area covered by i and j
 *  -   next value of i or j is decided on the basis of which is minimum, if arr[i] < arr[j] then i++ else j--
 */
public class ContainerWithMostWater {
    public static void main(String[] args) {
        System.out.println(solve(Arrays.asList(1, 8, 6, 2, 5, 4, 8, 3, 7)));
    }

    private static int solve(List<Integer> arr) {
        int i = 0;
        int j = arr.size() - 1;
        int max = 0;
        while( i < j) {
            int abs = Math.abs(j - i);
            int min;
            if(arr.get(i) > arr.get(j)) {
               min = arr.get(j);
               j--;
            }
            else {
                min = arr.get(i);
                i++;
            }
            max = Math.max(max, min * abs);
        }
        return max;
    }
}
