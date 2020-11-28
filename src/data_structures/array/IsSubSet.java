package data_structures.array;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Given two arrays check whether if one array is a subset of the other one.
 */
public class IsSubSet {
    public static void main(String[] args) {
        List<Pair<int[], int[]>> list = Arrays.asList(
                Pair.of(new int[]{11, 1, 13, 21, 3, 7}, new int[]{11, 3, 7, 1}),
                Pair.of(new int[]{1, 2, 3, 4, 5, 6}, new int[]{1, 2, 4}),
                Pair.of(new int[]{10, 5, 2, 23, 19}, new int[]{19, 5, 3})
        );
        for(var pair : list) {
            System.out.println("Array one = " + Arrays.toString(pair.getFirst()) + "\nArray second = " + Arrays.toString(pair.getSecond())
            + "\nArray second is " + (isSubSet(pair.getFirst(), pair.getSecond())? "a" : "not a") + " subset of Array one\n");
        }
    }

    private static boolean isSubSet(int[] arr, int[] brr) {
        Set<Integer> set = Arrays.stream(arr).boxed().collect(Collectors.toSet());
        return Arrays.stream(brr).allMatch(set::contains);
    }
}
