package data_structures.sorting;

import java.util.Arrays;
import java.util.Random;

public class HeapSort {
    public static void main(String[] args) {
        var random = new Random();
        int n = 10;
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++)
            arr[i] = 1 + random.nextInt(100);
        System.out.println("Arrays Before sorting " + Arrays.toString(arr));
        heapsort(arr);
        System.out.println("Arrays after sorting " + Arrays.toString(arr));
    }

    private static void heapsort(int[] arr) {
        buildmaxheap(arr, arr.length - 1);
        for (int i = arr.length - 1; i >= 1; i--) {
            swap(arr, 0, i);
            heapify(arr, 0, i);     // the point from where you want to start making max heap and to the length.
        }
    }

    private static void buildmaxheap(int[] arr, int length) {
        for (int i = length / 2; i >= 0; i--)
            heapify(arr, i, length);
    }

    private static void heapify(int[] arr, int i, int length) {
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        int largest;
        if (l < length && arr[l] > arr[i]) {
            largest = l;
        } else {
            largest = i;
        }
        if (r < length && arr[r] > arr[largest]) {
            largest = r;
        }
        if (largest != i) {
            swap(arr, largest, i);
            heapify(arr, largest, length);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
