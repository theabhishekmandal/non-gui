package data_structures.sorting;

import java.util.Arrays;
import java.util.Random;

public class SelectionSort {
    public static void main(String[] args) {
        Random random = new Random();
        int[] array = random.ints(10, 0, 100).toArray();
        System.out.println("Array before sort:" + Arrays.toString(array));
        sort(array);
        System.out.println("Array after sort:" + Arrays.toString(array));
    }


    private static void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            // Assume the current index is the minimum
            int minIndex = i;

            // Find the index of the minimum element in the unsorted subarray [i..end]
            for (int j = i + 1; j < array.length; j++) {
                if (array[minIndex] > array[j]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
    }
}
