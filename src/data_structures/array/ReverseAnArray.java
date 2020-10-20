package data_structures.array;

import java.util.Arrays;
import java.util.Random;

/**
 * Given an array, reverse the array
 */
public class ReverseAnArray {
    public static void main(String[] args) {
        var random = new Random();
        int n = 1 + random.nextInt(5);
        var arr = new int[n];
        for(int i = 0; i < arr.length; i++) arr[i] = random.nextInt(10);
        System.out.println("arrays before reversing " + Arrays.toString(arr));
        System.out.println("arrays after reversing " + Arrays.toString(reverse(arr)));
    }

    private static int[] reverse(int[] arr) {
        if(arr == null || arr.length == 0) return arr;
        for(int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
        return arr;
    }
}
