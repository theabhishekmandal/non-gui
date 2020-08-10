package collection_implementation.set;

import java.util.LinkedHashSet;

import static java.lang.System.out;

/**
 *  LinkedHashSet is faster than TreeSet but slower than HashSet.
 *  It also does not allow duplicates.
 *  It also allows null values
 *  It preserves the order in which the elements are inserted
 *  LinkedHashSet extends HashSet and adds no members of its own.
 *
 *  The following constructors are defined :
 *
 *  1 LinkedHashSet()                   :   constructs a default linked hash set.
 *
 *  2 LinkedHashSet(Collections<? extends
 *  E> c)                               :   initializes the  linked hash set by  using the elements of c.
 *
 *  3 LinkedHashSet(int capacity)       :   initializes the capacity of the linked hash set to capacity.
 *
 *  4 LinkedHashSet(int capacity, float
 *      fillRation)                     :   initializes both the capacity and fill ratio. The fill
 *                                          ratio must be between 0.0 and 1.0 and it determines how full
 *                                          a linked hashSet can be before it is resized upward. Specifically
 *                                          when the number of elements is greater than the capacity of
 *                                          LinkedHashSet multiplied by its fill ratio, the linked hash set is
 *                                          expanded. For constructors that do not take a fill ratio, 0.75 is used.
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
