package miscellaneous;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * To Reverse a list just use Comparator.reverseOrder() to reverse the list
 */
public class ReverseArrayListUsingComparator {
    public static void main(String[] args) {
        var list = List.of(1, 2, 3, 4, 5);
        var newList = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println(newList);
    }
}
