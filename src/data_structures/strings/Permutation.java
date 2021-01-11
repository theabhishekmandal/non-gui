package data_structures.strings;
import java.io.PrintWriter;
import java.util.*;

/**
 * This is program to generate all types of permutations of string.
 * The basic principle of this is as follows:
 * For method permute
 * 1. starting with index equal to 0, we swap all the elements with the given index even with it's own index,
 * 2. with each swapping we increment the index by 1 and call the recursion method.
 * 3. when the index reaches to end of the string we print the string.
 * 4. we swap back the current indexes.
 *
 * Note: Remember to swap back because, we are not creating new string or new array for each permutations,
 * we would be requiring the original string to create new permutations when we backtrace the recursion tree.
 *
 * There is another way to stop using the swap back as discussed in above method,
 * For method permute1
 * 1. we create a new array by coping the contents of original array.
 * 2. Then rather than swapping the original value, we swap the newly created array.
 * 3. Then we call the recursion tree using the new array.
 *
 * Note: Just remember in recursion, if you wan't to preserve the original value, then don't do operations on
 * the original value, just create a new value.
 *
 * For eg: Instead of doing this
 * count = 5,
 * private static void hello(int val){
 *     val++;
 *     hello(val);
 * }
 *
 * Use this
 * count = 5;
 * private static void hello(int val){
 *     hello(val + 1);
 * }
 *
 * by following the below one you can preserve the original value.
 */
public class Permutation {
    private static final PrintWriter out =  new PrintWriter(System.out);
    public static void main(String[] args) {
        char[] a = "abc".toCharArray();
        permute(0, a);
        out.flush();
    }

    private static void permute(int i, char[] a) {
        if(i == a.length - 1) {
            out.println(new String(a));
        }
        else {
            for(int j = i; j < a.length; j++) {
                swap(a, i, j);
                permute(i + 1, a);
                swap(a, i, j);
            }
        }
    }

    private static void swap(char[] a, int i, int j) {
        char temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
