package CollectionImplementation.CollectionAlgorithm;
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
 *  2 static <E> Collection<E>
 *    checkedCollection(Collection<E> c,Class<E> t) :   returns a run-time type safe view of the  collections. An
 *                                                      attempt to add a incompatible element will cause a
 *                                                      ClassCastException.
 *  3 static boolean disjoint(Collection<?> a,
 *      Collection<?> b)                            :   compares the elements in a to elements in b.
 *                                                      returns true if the two collections contain no common elements.
 *                                                      Otherwise, returns false.
 *
 *  4 static int frequency(Collection<?> c, Object
 *      obj)                                        :   counts the number of occurrences of obj in c and returns the
 *                                                      result.
 *
 *  5 static <T> T max(Collection<? extends T>c,
 *      Comparator<? super T> comp)                 :   returns the maximum element in c as determined by comp.
 *
 *  6 static <T extends Object & Comparable<? super
 *      T>> T max(Collection<? extends T> c)        :   returns the maximum element in c as determined by natural
 *                                                      ordering. The collection need not be sorted.
 *
 *  7 static <T> T min(Collection<? extends T>c,
 *      Comparator<? super T> comp)                 :   returns the minimum element in c as determined by comp.
 *
 *  8 static <T extends Object & Comparable<? super
 *      T>> T min(Collection<? extends T> c)        :   returns the minimum element in c as determined by natural
 *                                                      ordering. The collection need not be sorted.
 *
 *  9 static <T> Collection<T>
 *      synchronizedCollection(Collection<T> c)     :   returns a thread-safe collection backend by c.
 *
 *  10 static <T> Collection<T>
 *      unmodifiableCollection(Collection <? extends
 *       T> c)                                      :   returns an unmodifiable collection backed by c.
 *
 */
public class CollectionAlgoDemo1 {
    public static void main(String[] args) {
        Collection<String> arr = new ArrayList<>();
        arr.add("one");
        arr.add("two");

        out.println("implementing addAll method");
        Collections.addAll(arr, "three", "four", "five");
        out.println(arr);
        out.println();

        out.println("implementing checkedCollection method");
        Collection<String> obj = Collections.checkedCollection(arr, String.class);
        out.println(obj);
        out.println();

        out.println("implementing disjoint method");
        Collection<Integer> one = new ArrayList<>();
        Collection<Integer> two = new ArrayList<>();
        one.add(1);
        one.add(2);
        two.add(3);
        two.add(4);
        // this will return true
        out.println(Collections.disjoint(one, two));
        // this will return false
        one.add(3);
        out.println(Collections.disjoint(one, two));
        out.println();

        out.println("implementing the frequency method");
        String name = "Abhishek Mandal";
        // converting char array to Character array
        Character[] charArray = name.chars().mapToObj(c -> (char)c).toArray(Character[]::new);
        out.println(Collections.frequency(Arrays.asList(charArray), 'h'));
        out.println();

        Collection<Integer> IntegerCollection = new ArrayList<>();
        Collections.addAll(IntegerCollection, 1, 3, 9, 5, 4, 7, 6, 8, 2);

        out.println("implementing max method with comparator");
        out.println(Collections.max(IntegerCollection, Comparator.reverseOrder()));
        out.println();

        out.println("implementing max method without comparator");
        out.println(Collections.max(IntegerCollection));
        out.println();

        out.println("implementing min method with comparator");
        out.println(Collections.min(IntegerCollection, Comparator.reverseOrder()));
        out.println();

        out.println("implementing min method without comparator");
        out.println(Collections.min(IntegerCollection));
        out.println();

        out.println("implementing synchronizedCollection method");
        Collection<Integer> synchronizedCollection = Collections.synchronizedCollection(IntegerCollection);
        out.println(synchronizedCollection);
        out.println();

        out.println("implementing unmodifiableCollection");
        Collection<Integer> unmodifiableCollection = Collections.unmodifiableCollection(IntegerCollection);
        // this line will throw error if any modification is done
        //unmodifiableCollection.add(5);
        out.println(unmodifiableCollection);
        out.println();
    }
}

