package CollectionImplementation.CollectionAlgorithm;

import java.util.*;

import static java.lang.System.out;

/**
 * Below is the implementation of several methods in Collections for List:
 *
 *  1 static <T> int binarySearch(List<? extends T>
 *    list, T value, Comparator<? super T> c)       :   searches for  value in list ordered according to c. Returns the
 *                                                      position of vlaue in list, or negative value if value is not
 *                                                      found.
 *
 *  2 static <T> int binarySearch(List<? extends
 *    Comparable <? super T>> list, T value)        :   searches for value in list. The list must be sorted. Returns the
 *                                                      position of value in list, or a negative value if value is not
 *                                                      found.
 *
 *  3 static <T> void copy(List<? super T> list1,
 *    List<? extends T> list2)                      :   copies the elements of list2 to list1. The added elements should
 *                                                      have a type which should be the subclass of the list1 type
 *                                                      Destination list should be large enough
 *                                                      that it can contain all the elements of the source list otherwise
 *                                                      it will throw indexOutOfBoundException.
 *                                                      Also for copy that number of elements should be present in the
 *                                                      list. Also, copy method replaces the previous value from the list
 *
 *  4 static <E> List<E>
 *     checkedList(List<E> c, Class<E> t)           :   returns a run-time type-safe view of a List. An attempt to insert
 *                                                      an incompatible element will cause a ClassCastException.
 *
 */
public class CollectionAlgoDemo2 {
    public static void main(String[] args) {

        ArrayList<String> arr = new ArrayList<>();

        // adding elements
        arr.add("one");
        arr.add("two");
        arr.add("three");
        arr.add("four");
        arr.add("five");

        // implementing binarySearch  first method
        // you have to give a comparator because the list must be sorted before binary search
        out.println("implementing binarySearch method");
        int position = Collections.binarySearch(arr, "five", Comparator.reverseOrder());
        out.println(position + " " + arr.get(position));
        out.println();

        // implementing copy method
        // increasing the size of the destination list so as to avoid indexOutOfBound
        out.println("implementing copy method");
        List<Integer> arr2 = Arrays.asList(1, 2, 3, 4);
        ArrayList<Integer> arr1 = new ArrayList<>();
        for(int i = 0; i < 100; i++) arr1.add(5 + i);
        Collections.copy(arr1,arr2);
        arr1.sort(Comparator.naturalOrder());
        out.println(arr2);
        out.println();
    }
}
