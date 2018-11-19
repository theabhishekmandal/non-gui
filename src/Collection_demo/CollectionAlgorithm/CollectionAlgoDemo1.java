package Collection_demo.CollectionAlgorithm;
import java.util.*;
import static java.lang.System.*;

/**
 *  Below is the implementation of several methods in collections:
 *
 *  1 static <T> boolean addAll(Collection
 *    <? super T> c, T... elements)                 :   inserts the elements specified by elements into the collection
 *                                                      specified by c. Returns true if the elements were added and
 *                                                      false otherwise.
 *
 *  2 static <T> Queue<T> asLifoQueue(Deque<T> c)   :   returns a last in first out view of c
 *
 *  3 static <T> int binarySearch(List<? extends T>
 *    list, T value, Comparator<? super T> c)       :   searches for  value in list ordered according to c. Returns the
 *                                                      position of vlaue in list, or negative value if value is not
 *                                                      found.
 *
 *  4 static <T> int binarySearch(List<? extends
 *    Comparable <? super T>> list, T value)        :   searches for value in list. The list must be sorted. Returns the
 *                                                      position of value in list, or a negative value if value is not
 *                                                      found.
 *
 *  5 static <T> void copy(List<? super T> list1,
 *    List<? extends T> list2)                      :   copies the elements of list2 to list1. The added elements should
 *                                                      have a type which should be the subclass of the list1 type
 */
public class CollectionAlgoDemo1 {
    public static void main(String[] args) {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("one");
        arr.add("two");

        // implementing addAll method
        Collections.addAll(arr, "three", "four", "five");
        out.println(arr);

        // implementing binarySearch  first method
        // you have to give a comparator because the list must be sorted before binary search
        int position = Collections.binarySearch(arr, "five",Comparator.reverseOrder());
        out.println(position + " " + arr.get(position));

        // implementing copy method
        ArrayList<Integer> arr1 = new ArrayList<>();
        Collections.copy(arr1,Arrays.asList(1, 2, 3, 4));
        arr1.sort(Comparator.naturalOrder());
    }
}

