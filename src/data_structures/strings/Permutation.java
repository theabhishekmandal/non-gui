package data_structures.strings;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.function.ObjIntConsumer;

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
 *
 * Approach 2:
 *
 *  -   This approach is not based on recursion
 *  -   We start with lowest permutation i.e all the elements are in increasing order to highest permutation i.e decreasing order
 *  -   Do the below approach from lowest to highest.
 *
 *  -   To find the next permutation of a given number there are two scenarios that we have to handle
 *      -   if the elements are in decreasing order, then their next permutation is not possible and we terminate
 *
 *  -   Now, for the other scenario
 *      -   first start from the last element and check if there exists a number such that arr[i] > arr[i - 1].
 *          Element at i - 1, is needed to be replaced with its immediate increased value that can be found. (We do this because
 *          to find next increased value we change the values at, rightmost/least significant side. For eg: 1234 we change it to 1243)
 *
 *      -   the next immediate increased value can be found in range [i to arr.length - 1]. To find the next immediate
 *          increased value start from arr.length - 1 to i, if any arr[index] > arr[i - 1], then break
 *          eg: [1, 3, 2], now arr[i - 1] = 1 and next immediate value will be 2 and not 3.
 *
 *      -   replace i - 1 element with immediate increase value
 *
 *      -   In the end we need to rearrange the elements from i to arr.length, as they are not in correct permutation
 *          either you can sort that or just reverse the elements from i to arr.length. This is needed as we have changed the
 *          index through which we can find the next immediate increased value, but from that index to arr.length - 1, all
 *          elements should be in non-decreasing order.
 *          eg:
 *          arr = [1, 5, 8, 4, 7, 6, 5, 3, 1]
 *          -   start from end, we find index = 4
 *          -   for next immediate increase value start from end, we find index2 = 5
 *          -   swap index and index2 arr = [1, 5, 8, 5, 7, 6, 4, 3, 1]
 *          -   now sort or reverse from index + 1 to end arr = [1, 5, 8, 5, 1, 3, 4, 6, 7] -> this is next increased
 *              permutation value.
 */
public class Permutation {
    private static final PrintWriter out =  new PrintWriter(System.out);
    public static void main(String[] args) {
        char[] a = "abc".toCharArray();
        permute(0, a);
        out.flush();
        System.out.println(permute2("abc"));
        System.out.println(permute2("4213"));
    }

    private static void permute(int i, char[] a) {
        if (i == a.length - 1) {
            out.println(new String(a));
        }
        else {
            for (int j = i; j < a.length; j++) {
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

    // this finds the permutation of a given string in lexiographical order
    private static String permute2(String string) {
        if (string == null || string.length() == 0) {
            return "";
        }

        char[] arr = string.toCharArray();

        StringJoiner joiner = new StringJoiner("\n");
        Arrays.sort(arr);
        do {
           joiner.add(new String(arr));
        } while(nextPermutation(arr));
        return joiner.toString();
    }

    private static boolean nextPermutation(char[] arr) {
        int j = arr.length - 1;
        while (j > 0 && arr[j - 1] >= arr[j]) {
            j--;
        }

        // if it is in decreasing sequence, then next permutation is not possible
        if (j == 0) {
            return false;
        }

        int i = arr.length - 1;
        // find the index which is strictly greater than pivot from the last
        while (i >= j && arr[j - 1] >= arr[i]) {
            i--;
        }

        ObjIntConsumer<Integer> swap = (x, y) -> {
            char c = arr[x];
            arr[x] = arr[y];
            arr[y] = c;
        };

        // swap j - 1 and i
        swap.accept(j - 1, i);

        // now reverse from j to arr.length - 1
        for (int k = arr.length - 1; k > j; k--, j++) {
            swap.accept(k, j);
        }
        return true;
    }
}
