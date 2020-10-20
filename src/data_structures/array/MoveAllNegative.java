package data_structures.array;

import java.util.Arrays;
import java.util.Random;

/**
 * Given an array of positive and negative numbers. Move all the negatives to the left and all the positives to
 * the right
 */
public class MoveAllNegative {
    public static void main(String[] args) {
        var random = new Random();
        int n = 1 + random.nextInt(10);
        var arr = new int[n];
        for(int i = 0; i < arr.length; i++) arr[i] = -n + random.nextInt(2 * n);
        System.out.println("array before separating = " + Arrays.toString(arr));
        System.out.println("array after separating = " + Arrays.toString(separateNegativeAndPositive(arr)));
    }

    private static int[] separateNegativeAndPositive(int[] arr) {
       if(arr == null || arr.length == 0) return new int[]{};
       int pos = arr.length - 1;
       int neg = 0;
       while(neg < pos) {
           if(arr[neg] > 0 && arr[pos] < 0) {
               int temp = arr[neg];
               arr[neg] = arr[pos];
               arr[pos] = temp;
               neg++;
               pos--;
           }
           else if(arr[neg] < 0) neg++;
           else pos--;
       }
       return arr;
    }
}
