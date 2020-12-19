package data_structures.sorting;
import java.util.*;

public class Quicksort {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("enter the size of the array and the elements in it");
        int n = s.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < arr.length; i++)
            arr[i] = s.nextInt();

        quicksort(arr,0,arr.length - 1);
        StringBuilder br = new StringBuilder();
        for(int i : arr) {
            br.append(i).append(' ');
        }
        System.out.println(br);
    }

    private static void quicksort(int[] arr, int p, int r) {
        if(p < r) {
            int q = partition(arr, p, r);
            quicksort(arr, p, q - 1);
            quicksort(arr,q + 1, r);
        }
    }
    private static int partition(int[] arr, int low, int high) {
        int pindex = low;
        int pivot = arr[high];
        for(int i = low; i < high; i++) {
            if(arr[i] <= pivot) {
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
