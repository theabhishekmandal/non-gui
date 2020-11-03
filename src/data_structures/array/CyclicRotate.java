package data_structures.array;

import java.util.Arrays;
import java.util.Random;

/**
 * Given an array, cyclically rotate an array by one.
 * Input
 * 1 2 3 4 5
 * Output
 * 5 1 2 3 4
 */
public class CyclicRotate {
    public static void main(String[] args) {
        var random = new Random();
        int[] arr = new int[5];
        for(int i = 0; i < arr.length; i++) arr[i] = i;
        int k = random.nextInt(20);
        System.out.println("Arrays before rotation = " + Arrays.toString(arr));
        System.out.println("rotating by k = " + k);
        cyclicRotate(arr, k);
        System.out.println("Arrays after rotation " + Arrays.toString(arr));
    }
    private static void cyclicRotate(int[] arr, int k) {
        k = k % arr.length;
        if(k == 0) return;
        int[] swapArray = new int[k];
        int length = arr.length;
        int counter = 0;
        for(int i = length - k; i < length; i++) {
            swapArray[counter++] = arr[i];
        }
        counter = length - 1;
        for(int i = length - k - 1; i >= 0; i--) {
            arr[counter--] = arr[i];
        }
        for(int i = 0; i < k; i++) {
            arr[i] = swapArray[i];
        }
    }
}
