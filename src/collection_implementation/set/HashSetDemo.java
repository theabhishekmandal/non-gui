package collection_implementation.set;

import java.util.HashSet;

import static java.lang.System.out;

/**
 *  The main purpose of HashSet is to store objects such that no duplicates are allowed.
 *  It is fastest among TreeSet and LinkedHashSet.
 *  It does not guarantee the order of elements in which they are inserted.
 *  It allows null values to be stored.
 *  It can have different type of value.
 *  HashSet extends AbstractSet and implements the Set interface.
 *
 *  The following constructors are defined :
 *
 *  1 HashSet()                         :   constructs a default hash set.
 *
 *  2 HashSet(Collections<? extends E>
 *      c)                              :   initializes the ahs set by  using the elements of c.
 *
 *  3 HashSet(int capacity)             :   initializes the capacity of the hash set to capacity.
 *
 *  4 HashSet(int capacity, float
 *      fillRation)                     :   initializes both the capacity and fill ratio. The fill
 *                                          ratio must be between 0.0 and 1.0 and it determines how full
 *                                          a hashSet can be before it is resized upward. Specifically
 *                                          when the number of elements is greater than the capacity of
 *                                          hashSet multiplied by its fill ratio, the hash set is expanded.
 *                                          For constructors that do not take a fill ratio, 0.75 is used.
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
