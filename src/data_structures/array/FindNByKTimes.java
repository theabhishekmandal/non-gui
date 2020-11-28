package data_structures.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Given an array of length n and an integer k, find all the elements that have the count more than n / k.
 * Approach
 *      -   Using the map to store the count of elements, and return count where element's count >= n / k
 */
public class FindNByKTimes {
    public static void main(String[] args) {
        Random random = new Random();
        int[] arr = new int[10 + random.nextInt(10)];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(arr.length);
        }
        int k = 1 + random.nextInt(arr.length);
        System.out.println("n = " + arr.length + "\nk = " + k + "\nn/k = " + (arr.length / k) + "\nInput array is = " +
                Arrays.toString(arr));
        System.out.println(findCount(arr, k));
    }

    private static int findCount(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int nByK = arr.length / k;
        for(int i : arr) map.merge(i, 1, Integer::sum);
        return (int)map.entrySet().stream().filter(x -> x.getValue() >= nByK).count();
    }
}
