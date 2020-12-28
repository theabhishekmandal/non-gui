package data_structures.sorting;

import java.util.Arrays;
import java.util.Random;
/*
This program is an example of bubble sorting example let's check for values on how this algorithm works
let array a contains following elements

Bubble sorting is based on swapping the adjacent values if they differ in their value
   a[]={9,8,7,6,5}
   outer:0
         inner 0:
                  8,9,7,6,5
         inner 1:
                  8,7,9,6,5
         inner 2:
                  8,7,6,9,5
         inner 3:
                  8,7,6,5,9
    outer:1
          inner 0:
                  7,8,6,5,9
         inner 1:
                  7,6,8,5,9
         inner 2:
                  7,6,5,8,9
    outer:2
          inner 0:
                  6,7,5,8,9
          inner 1:
                  6,5,7,8,9
    outer:3
            inner 2:
                   5,6,7,8,9


    so we can see that the outer loop runs for about n-1 times where n is the size of the array
    and the inner loop runs n-i-1 times where i the iteration number of the outer loop


 */

public class BubbleSort {
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

    private static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
