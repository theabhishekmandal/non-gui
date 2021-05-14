package data_structures.array.dutch_national_flag;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * Given an array which have only values as 0, 1 and 2. Sort them without using any sorting algorithm
 * There are three ways to solve this, use the last one, can be done in single iteration
 */
public class Sort012 {
    public static void main(String[] args) {
        var random = new Random();
        int n = 1 + random.nextInt(10);
        var arr = new int[n];
        for(int i = 0; i < arr.length; i++) arr[i] = random.nextInt(3);
        int[] brr = Arrays.copyOf(arr, arr.length);
        int[] crr = Arrays.copyOf(arr, arr.length);

        String string = "Arrays after sorting ";
        System.out.println("Arrays before sorting " + Arrays.toString(arr));
        System.out.println(string + Arrays.toString(sort012(arr)));
        System.out.println(string + Arrays.toString(sort012New(brr)));
        System.out.println(string + Arrays.toString(sort012New2(crr)));
    }

    // map can be used to count
    private static int[] sort012(int[] arr) {
        if(arr == null || arr.length == 0) return new int[]{};
        Map<Integer, Integer> map = new TreeMap<>();
        for (int j : arr) {
            map.merge(j, 1, Integer::sum);
        }
        int counter = 0;
        for(var entry : map.entrySet()) {
            for(int i = 0; i < entry.getValue(); i++) arr[counter++] = entry.getKey();
        }
        return arr;
    }

    // using three counts instead
    private static int[] sort012New(int[] arr) {
        if(arr == null || arr.length == 0) return new int[]{};
        int[] count = new int[]{0, 0, 0};
        for(int i : arr) {
            count[i]++;
        }
        int i = 0;
        for(int p = 0; p < count.length; p++) {
           for(int k = 0; k < count[p]; k++) arr[i++] = p;
        }
        return arr;
    }

    // using single iteration, approach based on dutch national flag algorithm
    private static int[] sort012New2(int[] arr) {
        if(arr == null || arr.length == 0) return new int[]{};
        int one = 0;
        int two = 0;
        int three = arr.length - 1;
        while(two <= three) {
            if(arr[two] == 0) {
                swap(arr, two, one);
                two++;
                one++;
            }
            else if(arr[two] == 1) {
                two++;
            }
            else {
                swap(arr, two, three);
                three--;
            }
        }
        return arr;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
