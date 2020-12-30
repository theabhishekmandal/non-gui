package data_structures.array;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Given an array of elements, where each element is at most k away from its target position, devise an algorithm that sorts in
 * O(n log k) time.
 * Approach
 *  -   The problem states that an array is almost sorted i.e all the elements are off their positions by k distance. So if we
 *      look at each k partition they will be in sorted order than other k partitions. We just have to sort the inside values
 *      of these k partitions.
 *  -   The best way to use heap which will have size k + 1. k + 1 is important as the distance of each element is away by k distance.
 *      For eg if an element is at index 0 in sorted array, the same element can be at distance plus k i.e [0, 4]
 *  -   So first add the first k + 1 elements in the min heap
 *  -   Then starting from index k + 1 to total length, first add the polled elements to start of the array and keep adding the rest
 *      elements into the queue.
 *  -   In the end add all the remaining of the elements to the array from the heap.
 */
public class SortAlreadySortedArray {
    public static void main(String[] args) {
        int[] arr = {6, 5, 3, 2, 8, 10, 9};
        int k = 3;
        System.out.println(Arrays.toString(arr));
        sortAlmostSorted(arr, k);
        System.out.println(Arrays.toString(arr));
    }

    private static void sortAlmostSorted(int[] arr, int k) {
        var queue = new PriorityQueue<Integer>();
        for (int i = 0; i < k + 1; i++) {
            queue.add(arr[i]);
        }
        int index = 0;
        for (int i = k + 1; i < arr.length; i++) {
            if (!queue.isEmpty()) {
                arr[index++] = queue.poll();
            }
            queue.add(arr[i]);
        }

        var iter = queue.iterator();
        while (iter.hasNext()) {
            if (!queue.isEmpty()) {
                arr[index++] = queue.poll();
            }
        }
    }
}