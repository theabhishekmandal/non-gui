package collection_implementation.Set;

import java.util.Comparator;
import java.util.TreeSet;

import static java.lang.System.out;

/**
 *  TreeSet implements Navigable Set interface so you can it's methods
 *  TreeSet is also another type of set which does not allow duplicates.
 *  It is the slowest among HashSet and LinkedHashSet because after every insertion it sorts the elements
 *  order is preserved in non-decreasing order.
 *  TreeSet does not allow null values because sorting operation is performed so adding a null will throw
 *  NullPointerException
 *  TreeSet cannot have different types of elements, all the elements passed should be of same type because
 *  the comparison is being done. And for comparison all the objects should be of same type.
 *  TreeSet extends AbstractList and implements the NavigableSet interface.
 *
 *  The following constructors are defined :
 *
 *  1 TreeSet()                         :   constructs an empty tree set that will be sorted in ascending
 *                                          order.
 *
 *  2 TreeSet(Collection<? extends E>
 *     c)                               :   constructs a tree set that contains the element of c.
 *
 *  3 TreeSet(Comparator<? super E>
 *     comp)                            :   constructs an empty tree set that will be sorted according
 *                                          to the comparator specified by comp.
 *
 *  4 TreeSet(SortedSet<E> ss)          :   builds a tree set that contains the elements of ss.
 */
public class TreeSetDemo {
    public static void main(String[] args) {
        TreeSet<String> arr = new TreeSet<>();
        arr.add("A");
        arr.add("Abhishek");
        arr.add("B");
        arr.add("C");
        arr.add("D");
        arr.add("F");

        // printing the values in ascending order
        out.println(arr);

        // deciding the order of elements in descending order
        TreeSet<String> arr1 = new TreeSet<>(Comparator.reverseOrder());

        arr1.add("A");
        arr1.add("Abhishek");
        arr1.add("B");
        arr1.add("C");
        arr1.add("D");
        arr1.add("F");

        out.println(arr1);


        /*
         *  methods of SortedSet Interface
         *
         */

        // comparator returns null if natural order is present
        Comparator c = arr.comparator();
        out.println("printing the comparator value of arr \n" + c);
        out.println("printing the comparator value of arr1 \n" + arr1.comparator());


        // returning the first element of the invoking sorted set
        out.println("first element in the set \n" + arr1.first());


        // returning a sorted set containing those elements which are smaller than the passed value
        out.println("a sorted set smaller than given value \n" + arr1.headSet("D"));


        // returning the last element of the invoking sorted set
        out.println("last element of the set \n" + arr1.last());


        // returns a subset which is between start and end - 1, incorrect value causes exception
        out.println("a subset which is between two given values \n" + arr1.subSet("E", "A"));


        // returns a sortedSet that contains those elements which are greater than or equal to passed value
        out.println("a sorted set greater than or equal to a given value \n" + arr1.tailSet("D"));


        /*
         *  methods of NavigableSet interface
         */

        // returns the smallest element e such that e >= passed value.
        out.println("smallest value which is greater than passed value\n" + arr1.ceiling("B"));


        // returns the largest value e such that e > passed value
        out.println("largest value which is greater than passed value\n" + arr1.higher("B"));


        // returns the reverse of the invoking set
        out.println("reverse set \n" + arr1.descendingSet());


        // returns the largest element e such that e <= passed value.
        out.println("largest value which is smaller than passed value\n" + arr1.floor("E"));


        // returns the largest value e such that e < passed value.
        out.println("largest value which is smaller than passed value\n" + arr1.lower("E"));


        // returns a navigable set that includes all elements from the invoking set that are less than passed value
        // if boolean value is true, then an element equal to passed value is also included.
        out.println("a navigable set which is smaller than passed value \n" + arr1.headSet("D", true));


        // returns a navigable set that includes all elements from the invoking set that are greater than passed value
        // if boolean value is true, then an element equal to passed value is also included.
        out.println("a navigable set which is greater than passed value \n" + arr1.tailSet("D", true));


        // returns a subSet between lower bound and upper bound, both boolean values true then lower bound value and
        // upper bound value are included if present.
        out.println("subset between upper bound and lower bound\n" + arr1.subSet("E", true, "A", true));


        // returns the first element, removing the element in the process.
        out.println("polling the first value\n" + arr1.pollFirst());


        // returns the last element, removing the element in the process.
        out.println("polling the last value\n" + arr1.pollLast());
    }
}
