package data_structures.sorting;

import java.util.Arrays;
import java.util.Random;

public class Quicksort {
    public static void main(String[] args) {
        Random random = new Random();
        int n = 10;
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++)
            arr[i] = 1 + random.nextInt(100);

        System.out.println("Arrays before sorting " + Arrays.toString(arr));
        quicksort(arr, 0, arr.length - 1);
        System.out.println("Arrays after sorting " + Arrays.toString(arr));
    }

    private static void quicksort(int[] arr, int p, int r) {
        if (p < r) {
            int q = partition(arr, p, r);
            quicksort(arr, p, q - 1);
            quicksort(arr, q + 1, r);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pindex = low;
        int pivot = arr[high];
        for (int i = low; i < high; i++) {
            if (arr[i] <= pivot) {
                int temp = arr[i];
                arr[i] = arr[pindex];
                arr[pindex] = temp;
                pindex++;
            }
        }
        int temp = arr[pindex];
        arr[pindex] = arr[high];
        arr[high] = temp;
        return pindex;
    }
}
