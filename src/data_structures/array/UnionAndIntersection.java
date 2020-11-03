package data_structures.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Given two input arrays find their union and intersection, with space complexity as O(m + n) and time complexity as
 * O(m + n)
 *
 * Approach
 *  -   To find the union and intersection in linear time, first we have to sort both the arrays if they are not sorted
 *  -   Then take two pointers i and j, i for first array and j for second array
 *  =   if arr[i] < brr[j] then add to union list and increment i
 *  -   if brr[j] < arr[i] then add to union list and increment j
 *  -   if both are same then add to both union and intersection list and increment both
 *  -   after that check whether elements are remaining or not, if remaining then add to the union list
 */

public class UnionAndIntersection {
    private static final Random random = new Random();
    public static void main(String[] args) {
        var getInputArray = getInputArray();
        var first = getInputArray.getFirst();
        var second = getInputArray.getSecond();
        System.out.println("first array = " + Arrays.toString(first) + "\nsecond array = " + Arrays.toString(second));
        var unionAndIntersection = getUnionAndIntersectionForUnsortedArray(first, second);
        System.out.println("union = " + Arrays.toString(unionAndIntersection.getFirst())
                + "\nintersection array = " + Arrays.toString(unionAndIntersection.getSecond()));
    }

    // call this if working on unsorted array
    private static Pair<int[], int[]> getUnionAndIntersectionForUnsortedArray(int[] arr, int[] brr) {
        int[] empty = new int[]{};
        if(arr == null && brr == null) return Pair.of(empty, empty);
        if(arr == null) return Pair.of(brr, empty);
        if(brr == null) return Pair.of(arr, empty);
        Arrays.sort(arr);
        Arrays.sort(brr);
        return getUnionAndIntersectionForSortedArray(arr, brr);
    }

    // call this if working on sorted array
    private static Pair<int[], int[]> getUnionAndIntersectionForSortedArray(int[] arr, int[] brr) {
        int[] empty = new int[]{};
        if(arr == null && brr == null) return Pair.of(empty, empty);
        if(arr == null) return Pair.of(brr, empty);
        if(brr == null) return Pair.of(arr, empty);

        List<Integer> union = new ArrayList<>();
        List<Integer> intersection = new ArrayList<>();

        int i = 0;
        int j = 0;
        while((i = skipDuplicate(i, arr)) < arr.length &&
                (j = skipDuplicate(j, brr)) < brr.length) {
            if(arr[i] < brr[j]) {
                union.add(arr[i]);
                i++;
            }
            else if(brr[j] < arr[i]) {
                union.add(brr[j]);
                j++;
            }
            else {
                union.add(arr[i]);
                intersection.add(arr[i]);
                i++;
                j++;
            }
        }
        while((i = skipDuplicate(i, arr)) < arr.length) {
            union.add(arr[i]);
            i++;
        }
        while((j = skipDuplicate(j, brr)) < brr.length) {
            union.add(brr[j]);
            j++;
        }

        int[] un = new int[union.size()];
        int[] in = new int[intersection.size()];
        for(int k = 0; k < un.length; k++) un[k] = union.get(k);
        for(int k = 0; k < in.length; k++) in[k] = intersection.get(k);
        return Pair.of(un, in);
    }

    private static int skipDuplicate(int i, int[] arr) {
       while(i != 0 && i < arr.length && arr[i] == arr[i - 1]) i++;
       return i;
    }

    // this is for generating random input
    private static Pair<int[], int[]> getInputArray() {
        int range = 10;
        int t = random.nextInt(4);
        int[] arr = new int[10];
        int[] brr = new int[arr.length];
        // if both have all the common elements
        if(t == 0) {
            for(int i = 0; i < arr.length; i++) {
                arr[i] = random.nextInt(range);
                brr[i] = arr[i];
            }
        }
        // when both have different elements
        else if(t == 1) {
            for(int i = 0; i < arr.length; i++) {
                arr[i] = random.nextInt(range);
                brr[i] = arr[i] + random.nextInt(range);
            }
        }
        // when first partially overlaps the second
        else {
            for(int i = 0; i < arr.length; i++) {
                arr[i] = random.nextInt(range);
            }
            int temp = 0;
            for(int i = arr.length / 2; i < arr.length; i++) {
                brr[temp++] = arr[i];
            }
            for(; temp < brr.length; temp++) {
                brr[temp] = random.nextInt(range);
            }
            if(t == 3) {
                int[] tempArray = arr;
                arr = brr;
                brr = tempArray;
            }
        }
        return Pair.of(arr, brr);
    }
}

class Pair<T, U> {
    private final T first;
    private final U second;
    Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }
    public static <T, U> Pair<T, U> of(T first, U second) {
        return new Pair<>(first, second);
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }
}
