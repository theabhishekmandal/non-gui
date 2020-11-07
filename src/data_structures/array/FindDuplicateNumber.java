package data_structures.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
 *
 * There is only one duplicate number in nums, return this duplicate number.
 *
 * Follow-ups:
 *  -    How can we prove that at least one duplicate number must exist in nums?
 *  -    Can you solve the problem without modifying the array nums?
 *  -    Can you solve the problem using only constant, O(1) extra space?
 *  -    Can you solve the problem with runtime complexity less than O(n2)?
 *
 *  Approach:
 *      -   First approach in finding the duplicate number is to use a set, but it is not using constant space
 *      -   Second approach would be to use the property of cycle detection algorithm in linked list
 *      -   As it is known that there are numbers that are in range of array length, so if we traverse through
 *          index, we would never get index out of bounds
 *      -   So, In this first we would take a slow pointer and fast pointer,
 *          -   slow pointer will move one step at a time and fast pointer will move two steps at a time
 *          -   if we reach the same index this means we have detected cycle
 *          -   now to find the link which is equal to a duplicate number, we keep the fast pointer at
 *              the current position, and move the slow pointer to the start index
 *          -   now we move one step at a time for both pointers, and when then point the same value this means
 *              that index is the duplicate index.
 */
public class FindDuplicateNumber {
    private static final Random random = new Random();
    public static void main(String[] args) {
        var random = new Random();
        int t = 1 + random.nextInt(5);
        while(t-- > 0) {
            int[] arr = getInput();
            int[] brr = arr.clone();
            System.out.println("Input array arr = " + Arrays.toString(arr) + "\n" + "duplicate element is = " + findDuplicate(arr));
            System.out.println("Input array brr = " + Arrays.toString(arr) + "\n" + "duplicate element is = " + findDuplicate2(brr) + "\n");
        }
    }

    // using set to find the duplicate number
    private static int findDuplicate(int[] arr) {
        Set<Integer> set = new HashSet<>();
        int k = -1;
        for(int i : arr) {
            if(set.contains(i)) {
                k = i;
                break;
            }
            set.add(i);
        }
        return k;
    }

    // using Floyd's cycle detection algorithm
    private static int findDuplicate2(int[] arr) {
        int hare = 0;
        int tortoise = 0;
        do {
            tortoise = arr[tortoise];
            hare = arr[arr[hare]];
        } while(tortoise != hare);

        tortoise = 0;
        while(tortoise != hare) {
            hare = arr[hare];
            tortoise = arr[tortoise];
        }
        return tortoise;
    }

    private static int[] getInput() {
        int[] arr = new int[2 + random.nextInt(10)];
        int repeat = 1 + random.nextInt(arr.length - 1);
        int counter = 0;
        if(random.nextBoolean()) {
            int times = 1 + random.nextInt(arr.length);
            while(counter < arr.length && counter < times) {
                arr[counter] = repeat;
                counter++;
            }
        }
        else {
            arr[counter] = repeat;
            counter++;
        }
        int num = 1;
        while(counter < arr.length) {
            arr[counter] = num;
            num++;
            counter++;
        }
        return arr;
    }
}
