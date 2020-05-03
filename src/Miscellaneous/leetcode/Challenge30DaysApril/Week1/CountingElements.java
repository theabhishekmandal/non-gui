package Miscellaneous.leetcode.Challenge30DaysApril.Week1;

import java.util.*;

/**
 * Week 1 day 7
 *
 * Given an integer array arr, count element x such that x + 1 is also in arr.
 *
 * If there're duplicates in arr, count them seperately.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [1,2,3]
 * Output: 2
 * Explanation: 1 and 2 are counted cause 2 and 3 are in arr.
 * Example 2:
 *
 * Input: arr = [1,1,3,3,5,5,7,7]
 * Output: 0
 * Explanation: No numbers are counted, cause there's no 2, 4, 6, or 8 in arr.
 * Example 3:
 *
 * Input: arr = [1,3,2,3,5,0]
 * Output: 3
 * Explanation: 0, 1 and 2 are counted cause 1, 2 and 3 are in arr.
 * Example 4:
 *
 * Input: arr = [1,1,2,2]
 * Output: 2
 * Explanation: Two 1s are counted cause 2 is in arr.
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 1000
 * 0 <= arr[i] <= 1000
 *
 * Approach
 * Store all the numbers with there counts in TreeMap in ascending order.
 *
 * now compare current element with the previous, if previous + 1 == current
 * then add the counts of previous value.
 *
 */
public class CountingElements {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < arr.length; i++) 
            arr[i] = s.nextInt();
        solve(arr);
    }

    private static void solve(int[] arr) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int i : arr){
            if(!map.containsKey(i)) map.put(i, 0);
            map.put(i, map.get(i) + 1);
        }
        Map.Entry<Integer, Integer> previous = null;
        int finalCounter = 0;
        for(Map.Entry<Integer, Integer> curr : map.entrySet()){
            if(previous != null && previous.getKey() + 1 == curr.getKey())
                    finalCounter += previous.getValue();
            previous = curr;
        }
        System.out.println(finalCounter);
    }
}

