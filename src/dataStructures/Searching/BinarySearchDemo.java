package dataStructures.Searching;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Binary search implementation
 */

public class BinarySearchDemo {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < arr.length; i++) arr[i] = s.nextInt();
        Arrays.sort(arr);
        System.out.println("sorted array " + Arrays.toString(arr));
        for(int i = arr.length - 1; i >= 0; i--){
            System.out.println("key = " + arr[i] + " index location = " + binarySearch(arr, arr[i]));
        }
        System.out.println("key = " + (int)1e9 + " index location = " + binarySearch(arr, (int)1e9));
    }
    private static int binarySearch(int[] arr, int key){
        int low = 0;
        int high = arr.length - 1;
        while(low <= high){
            int mid = low + ((high - low) >> 1);
            if(arr[mid] == key){
                return mid;
            }
            else if(arr[mid] < key) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }
}
