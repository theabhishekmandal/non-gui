package data_structures.sorting;
import java.util.*;
public class Mergesort {
    public static void main(String[] args) {
        Random random = new Random();
        int n = random.nextInt(20);
        int[] arr = new int[n];
        for(int i = 0; i < n; i++)
            arr[i] = random.nextInt(arr.length);
        System.out.println("Arrays Before sorting " + Arrays.toString(arr));
        mergeSort(arr,0,n - 1);
        System.out.println("Arrays after sorting " + Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr, int p, int r) {
        if(p < r) {
            int q = (p + (r - p) / 2);
            mergeSort(arr, p, q);
            mergeSort(arr,q + 1, r);
            merge(arr, p, q, r);
        }
    }

    private static void merge(int[] arr, int p, int q, int r) {
        int[] left = Arrays.copyOfRange(arr, p, q + 1);
        int[] right = Arrays.copyOfRange(arr, q + 1, r + 1);
        int i = 0;
        int j = 0;
        int k = p;
        while(i < left.length && j < right.length) {
            if(left[i] <= right[j]) {
                arr[k++] = left[i];
                i++;
            }
            else {
                arr[k++] = right[j];
                j++;
            }
        }
        while(i < left.length) {
            arr[k++] = left[i++];
        }
        while(j < right.length) {
            arr[k++] = right[j++];
        }
    }
}
