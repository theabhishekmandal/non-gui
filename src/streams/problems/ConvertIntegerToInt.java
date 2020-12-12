package streams.problems;

import java.util.Arrays;
import java.util.List;

public class ConvertIntegerToInt {
    public static void main(String[] args) {
        List<Integer> arr = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        int[] ints = arr.stream().mapToInt(Integer::intValue).toArray();
        System.out.println(Arrays.toString(ints));
    }
}
