package data_structures.sorting;

import java.util.Arrays;
import java.util.Random;

/*
this class is example of insertion sorting
here we start with the second index of the array
we can understand it with the help of the following example
let array a[]={9,8,7,6,5}
outer:1
     key=8
     after the while loop:  9,9,7,6,5
     8,9,7,6,5
outer:2
      key=7
      after the while loop:8,8,9,6,5
      7,8,9,6,5
 outer:3
       key=6
       after the while loop:7,7,8,9,5
       6,7,8,9,5
 outer:4
       key=5
       after the while loop:6,6,7,8,9
       5,6,7,8,9


    at last the array is sorted
 */
public class InsertionSort {
    public static void main(String[] args) {
        Random random = new Random();
        int n = 10;
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1 + random.nextInt(100);
        }
        System.out.println("Arrays Before sorting " + Arrays.toString(arr));
        sort(arr);
        System.out.println("Arrays after sorting " + Arrays.toString(arr));
    }

    /*
        Insertion sort is based on, find the position where the key should be added.
        start with last element and compare, if key is smaller then copy the large value to end of array.
        doing like this find the proper position for key.

        For Sorting linked list, insertion sort is better.
     */
    private static void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i - 1;
            int key = arr[i];
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}
