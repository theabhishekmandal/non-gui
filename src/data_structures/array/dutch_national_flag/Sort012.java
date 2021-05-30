package data_structures.array.dutch_national_flag;

import java.util.Arrays;
import java.util.Random;
import java.util.TreeMap;

/**
 * Given an array which have only values as 0, 1 and 2. Sort them without using any sorting algorithm
 * There are three ways to solve this, use the last one, can be done in single iteration
 */
public class Sort012 {
    public static void main(String[] args) {
        var random = new Random();
        var arr = random.ints(0, 3)
                .limit((long) 1 + random.nextInt(10)).toArray();

        var brr = arr.clone();
        var crr = arr.clone();


        var string = "Arrays after sorting ";
        System.out.println("Arrays before sorting " + Arrays.toString(arr));
        System.out.println(string + Arrays.toString(sort012(arr)));
        System.out.println(string + Arrays.toString(sort012New(brr)));
        System.out.println(string + Arrays.toString(sort012New2(crr)));
    }

    // map can be used to count
    private static int[] sort012(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[]{};
        }
        var map = new TreeMap<Integer, Integer>();
        for (var j : arr) {
            map.merge(j, 1, Integer::sum);
        }
        var counter = 0;
        for (var entry : map.entrySet()) {
            for (var i = 0; i < entry.getValue(); i++) {
                arr[counter++] = entry.getKey();
            }
        }
        return arr;
    }

    // using three counts instead
    private static int[] sort012New(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[]{};
        }
        var count = new int[]{0, 0, 0};
        for (var i : arr) {
            count[i]++;
        }
        var i = 0;
        for (var p = 0; p < count.length; p++) {
            for (var k = 0; k < count[p]; k++) {
                arr[i++] = p;
            }
        }
        return arr;
    }

    // using single iteration, approach based on dutch national flag algorithm
    private static int[] sort012New2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[]{};
        }
        var one = 0;
        var two = 0;
        var three = arr.length - 1;
        while (two <= three) {
            if (arr[two] == 0) {
                swap(arr, two, one);
                two++;
                one++;
            } else if (arr[two] == 1) {
                two++;
            } else {
                swap(arr, two, three);
                three--;
            }
        }
        return arr;
    }

    private static void swap(int[] arr, int i, int j) {
        var temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
