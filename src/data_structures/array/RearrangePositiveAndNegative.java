package data_structures.array;

import java.util.Arrays;
import java.util.Random;

/**
 * Given an array of positive and negative elements rearrange them in alternate positive and negative order.
 * Input :
 * arr[] = {-2, 3, 4, -1}
 * Output :
 * arr[] = {-2, 3, -1, 4} OR {-1, 3, -2, 4}
 *
 * Approach:
 *  -   As positive number are followed by negative numbers, then we can say that negative numbers should
 *      be present in even position and positive numbers on the odd position.
 *  -   for a given index i check if it's even and if there is a positive number then replace it with negative
 *      number. Do the same with odd numbers.
 */

public class RearrangePositiveAndNegative {
    public static final Random random = new Random();
    public static void main(String[] args) {
        int size = 1 + random.nextInt(100);
        int[] arr = new int[size];
        for(int i = 0; i < arr.length; i++) {
            int k  = 1 + random.nextInt(arr.length);
            k = random.nextBoolean() ? -k : k;
            arr[i] = k;
        }
//        arr = new int[]{-5, 3, 4, 5, -6, -2, 8, 9, -1, -4};
        System.out.println("Arrays before rearranging = " + Arrays.toString(arr));
        rearrange(arr);
        System.out.println("Arrays after rearranging = " + Arrays.toString(arr));
    }

    private static void rearrange(int[] arr) {
        if(arr == null || arr.length <= 1) return;
        int i = 0; // neg
        while(i < arr.length) {
            if(i % 2 == 0 && arr[i] > 0) {
                int k = i + 1;
                while(k < arr.length && arr[k] > 0) k++;
                swap(arr, i, k);
            }
            else if(i % 2 == 1 && arr[i] < 0){
                int k = i + 1;
                while(k < arr.length && arr[k] < 0) k++;
                swap(arr, i, k);
            }
            i++;
        }
    }

    private static void swap(int[] arr, int i, int k) {
        if(i < arr.length && k < arr.length) {
            int temp = arr[i];
            arr[i] = arr[k];
            arr[k] = temp;
        }
    }
}
