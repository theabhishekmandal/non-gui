package CollectionImplementation.Set;

import java.util.HashSet;

import static java.lang.System.out;

/**
 *  The main purpose of HashSet is to store objects such that no duplicates are allowed.
 *  It is fastest among TreeSet and LinkedHashSet.
 *  It does not guarantee the order of elements in which they are inserted.
 *  It allows null values to be stored.
 *  It can have different type of values
 *
 *  HashSet extends AbstractSet and implements the Set interface.
 */
public class HashSetDemo {
    public static void main(String[] args) {
        HashSet<Object> arr = new HashSet<>();
        arr.add("Beta");
        arr.add("Alpha");
        arr.add("Eta");
        arr.add("Gamma");
        arr.add("Epsilon");
        arr.add("Omega");
        arr.add("Beta");
        arr.add(1);
        arr.add(null);

        // Since no duplicates are allowed only unique elements are present but order is not preserved.
        out.println(arr);
    }
}
