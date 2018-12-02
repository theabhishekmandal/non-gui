package CollectionImplementation.Set;

import java.util.LinkedHashSet;

import static java.lang.System.out;

/**
 *  LinkedHashSet is faster than TreeSet but slower than HashSet.
 *  It also does not allow duplicates.
 *  It also allows null values
 *  It preserves the order in which the elements are inserted
 *
 *  LinkedHashSet extends HashSet and adds no members of its own.
 */
public class LinkedHashSetDemo {
    public static void main(String[] args) {
        LinkedHashSet<String> arr = new LinkedHashSet<>();
        arr.add("Beta");
        arr.add("Alpha");
        arr.add("Eta");
        arr.add("Gamma");
        arr.add("Epsilon");
        arr.add("Omega");
        arr.add("Beta");
        arr.add(null);

        // Since no duplicates are allowed only unique elements are present and order is preserved.
        out.println(arr);
    }
}
