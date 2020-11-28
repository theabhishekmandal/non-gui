package data_structures.strings;

import java.util.Arrays;

/**
 * Given a string reverse it's elements
 */

public class ReverseString {
    public static void main(String[] args) {
        char[] ans = "hello world".toCharArray();
        System.out.println("string = " + Arrays.toString(ans));
        reverse(ans);
        System.out.println("reversed string = " + Arrays.toString(ans));
    }

    private static void reverse(char[] arr) {
        for(int i = 0, j = arr.length - 1; i < j; i++, j--) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
