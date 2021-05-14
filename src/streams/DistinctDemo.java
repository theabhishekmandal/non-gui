package streams;

import java.util.Arrays;
import java.util.stream.Collectors;
/**
 * Collects the distinct elements from the list
 */

public class DistinctDemo {
    public static void main(String[] args) {
        var intList = Arrays.asList(2, 5, 3, 2, 4, 3);
        var distinctIntList = intList.stream()
                                        .distinct()
                                        .collect(Collectors.toList());

        System.out.println(distinctIntList);
    }
}
