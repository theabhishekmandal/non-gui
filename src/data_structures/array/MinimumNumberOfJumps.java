package data_structures.array;

/**
 * Given an array of integer elements, where each element represents the max number of steps that can be made
 * forward from that element. The task is to find the minimum number of jumps to reach the end of array. If an
 * element is zero, then cannot move through that element
 *
 * Approach
 *  -   In this we have three variables:
 *      -   maxReach    -   It tells the current maximum index where we can reach
 *      -   jump        -   the number of jumps required to reach the end of array. This jump will store the answer
 *      -   steps       -   number of steps that we can take from the current index
 *
 *  -   Initially, maxReach and steps will be arr[0], as it tells that the maximum index we can reach and
 *      number of steps that we can take from 0th index. Eg: if arr[0] = 2, then max index = 2 and number of steps = 2,
 *      and jump will be 1, as we can make a jump from 0th index
 *
 *  -   Now starting from index 1 we do the following
 *      -   First check if we reached the end of array or not, if reached then return jump
 *      -   Otherwise, take the max of maxReach and arr[i] + i. We are doing this because
 *          we want to find the maximum index that we can reach from a given set of indices.
 *
 *          Eg: if arr = [1, 3, 5, 8, 2, 9, 6, 7, 6, 8, 9] and i = 1. arr[1] = 3, this shows
 *          that we can take three steps from index 1, and out of those three indices, we have
 *          to find the maximum reachable index.
 *      -   This is done as by assigning steps = 3, maxReach will be calculated among arr[2], arr[3] and
 *          arr[4]
 *      -   So when the steps becomes 0, then we take a jump by incrementing it. now we check
 *          if the current index is greater than max reach, so as to verify that we can reach
 *          the end of array. if current index is larger than maxReach it means we will never reach
 *          the end of array
 *      -   also we find the number of steps that we can take jump from current index
 *
 */
public class MinimumNumberOfJumps {
    public static void main(String[] args) {
        System.out.println("\n" + getMinJumps(new int[]{1, 3, 5, 8, 2, 9, 6, 7, 6, 8, 9}));
    }

    private static int getMinJumps(int[] arr) {
       if(arr == null) return -1;
       if(arr.length <= 1) return 0;
       if(arr[0] == 0) return -1;
       int maxReach = arr[0];
       int steps = arr[0];
       int jump = 1;
       for(int i = 1; i < arr.length; i++) {
           if(i == arr.length - 1) {
               return jump;
           }
           maxReach = Math.max(maxReach, arr[i] + i);
           steps--;
           if(steps == 0) {
               jump++;
               if(i >= maxReach) {
                   return -1;
               }
               steps = maxReach - i;
           }
       }
       return -1;
    }
}
