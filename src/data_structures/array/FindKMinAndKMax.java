package data_structures.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Given an array and value k which is smaller than the length of array, find the kth maximum and kth minmum
 * element in the array
 */


public class FindKMinAndKMax {
    public static void main(String[] args) {
        var random = new Random();
        int n = 1 + random.nextInt(20);
        var arr = new int[n];
        for(int i = 0; i < arr.length; i++) arr[i] = -5 + random.nextInt(10);
        int k = random.nextInt(arr.length) + 1;
        System.out.println("array = " + Arrays.toString(arr));
        System.out.println("kth="  + k + " min and max are  = " + Arrays.toString(getKthMaxAndKthMin(arr, k)));
        System.out.println("kth="  + k + " min and max are  = " + Arrays.toString(getKthMaxAndKthMin2(arr, k)));
    }

    // using sorting
    private static int[] getKthMaxAndKthMin(int[] arr, int k) {
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        return new int[]{arr[k - 1], arr[arr.length - k]};
    }

    /*
        Using priority queue, we can create heap,
        To find kth Max element - we use Min heap, keep adding elements, if size increases more than k then poll,
                                  after that the first element will contain the kth max element

        To find kth Min element - we use Max heap, keep adding elements, if size increases more than k then poll,
                                  after that the first element will contain the kth min element
     */
    private static int[] getKthMaxAndKthMin2(int[] arr, int k) {
        var pQueueMax = new PriorityQueue<Integer>(k, Comparator.naturalOrder());
        var pQueueMin = new PriorityQueue<Integer>(k, Comparator.reverseOrder());
        for (int j : arr) {
            pQueueMax.add(j);
            pQueueMin.add(j);
            if (pQueueMax.size() > k) pQueueMax.poll();
            if (pQueueMin.size() > k) pQueueMin.poll();
        }
        if(!pQueueMax.isEmpty() && !pQueueMin.isEmpty()) {
            return new int[]{pQueueMin.poll(), pQueueMax.poll()};
        }
        return new int[]{};
    }
}
