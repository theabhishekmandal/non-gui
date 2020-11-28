package data_structures.array;

/**
 * Given an array of elements, find the longest consecutive length such that elements in the subsequence is
 * are consecutive integers. Do it in O(n) time and spaceComplexity
 *
 * Approach:
 *      -   find the max element in array
 *      -   Create new array of size max + 1
 *      -   store the count of each element in input array to the count array
 *      -   search for consecutive length and find the max length in it.
 */

public class LongestConsecutiveSubsequence {
    public static void main(String[] args) {
//        System.out.println(getLongestConsecutiveSubSequence(new int[]{2,6,1,9,4,5,3}));
        System.out.println(getLongestConsecutiveSubSequence(new int[]{1, 2, 3, 4, 5}));
    }

    private static int getLongestConsecutiveSubSequence(int[] arr) {
       int max = arr[0];
       for(int i : arr) {
           max = Math.max(i, max);
       }
       int[] brr = new int[max + 1];
       for(int i : arr) {
           brr[i]++;
       }
       int count = 0;
       max = 0;
       for(int i : brr) {
           if(i == 0) {
               count = 0;
           }
           else {
               count++;
           }
           max = Math.max(count, max);
       }
       return max;
    }
}
