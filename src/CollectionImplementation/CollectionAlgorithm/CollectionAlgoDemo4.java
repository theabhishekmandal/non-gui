package CollectionImplementation.CollectionAlgorithm;
import java.util.*;
import static java.lang.System.*;

/**
 * Below is the implementation of several methods in Collections for Set:
 *
 *  1 static <E> NavigableSet<E>
 *     checkedNavigableSet(NavigableSet<E>
 *     ns, Class<E> t)                          :   returns a run-time type-safe view of a NavigableSet.
 *                                                  An attempt to insert an incompatible element will
 *                                                  cause a ClassCastException.
 *
 *  2 static <E> List<E>
 *      checkedSet(Set<E> c, Class<E> t)        :   returns a run-time type-safe view of a Set. An attempt
 *                                                  to insert an incompatible element will cause a
 *                                                  ClassCastException.
 *
 *  3 static <E> SortedSet<E>
 *      checkedSortedSet(SortedSet<E>c,
 *      Class<E> t)                             :   returns a run-time type-safe view of a SortedSet.
 *                                                  An attempt to insert an incompatible element will cause
 *                                                  a ClassCastException.
 *
 *  4 static <E> NavigableSet<E>
 *      emptyNavigableSet()                     :   returns an immutable, empty NavigableSet object of
 *                                                  the inferred type.
 *
 *  5 static <T> Set<T> emptySet()              :   returns an immutable, emptySet object of the inferred
 *                                                  type.
 *
 *  6 static <E> SortedSet<E>
 *      emptySortedSet()                        :   returns an immutable, empty SortedSet object of the
 *                                                  inferred type.
 *
 *  7 static <T> Set<T> singleton(T obj)        :   returns obj as an immutable set. This is an easy way to
 *                                                  convert a single object into a set.
 *
 *  8 static <T> Set<T>
 *      synchronizedSet(Set<T> s)               :   returns a synchronized set backed by s.
 *
 *  9 static <T> NavigableSet<T>
 *      synchronizedNavigableSet(
 *      NavigableSet<T> ns)                     :   returns a synchronized navigable set backed by ns.
 *
 *  10 static <T> SortedSet<T>
 *      synchronizedSortedSet(
 *      SortedSet<T> ss)                        :   returns a synchronized sorted set backed by ss.
 *
 *  11 static <T> Set<T>
 *      unmodifiableSet(Set<T> s)               :   returns a unmodifiable set backed by s.
 *
 *  12 static <T> NavigableSet<T>
 *      unmodifiableNavigableSet(
 *      NavigableSet<T> ns)                     :   returns a unmodifiable navigable set backed by ns.
 *
 *  13 static <T> SortedSet<T>
 *      unmodifiableSortedSet(
 *      SortedSet<T>ns)                         :   returns a unmodifiable sorted set backed by ss.
 *
 */

public class CollectionAlgoDemo4 {
    public static void main(String[] args) {

        // without checkset methods set variable does not force non-generic set variable to be type safe
        out.println("without the different type of checkset methods");
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        Set nonGenericSet = new HashSet();
        nonGenericSet.add("hello");
        nonGenericSet.add(12.13);
        out.println("not type safe " + nonGenericSet);
        out.println("implementing checkedSet method");
        set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        Set autogen = Collections.checkedSet(set, Integer.class);
        autogen.add(4);
        // autogen.add(12.3); error
        out.println("type safe " + autogen);
        out.println();

        // here you cannot use the set variable and use the checkedNavigableSet method
        // because the set variable does not implement NavigableSet interface
        out.println("implementing checkedNavigableSet method");
        NavigableSet<Integer> navigableSet = new TreeSet<>();
        navigableSet.add(12);
        navigableSet.add(3);
        NavigableSet navigableautogen = Collections.checkedNavigableSet(navigableSet, Integer.class);
        navigableautogen.add(45);
        // navigableautogen("hello") error
        out.println("type safe " + navigableautogen);
        out.println();

        // here either use NavigableSet or SortedSet and not Set interface
        out.println("implementing checkedSortedSet method");
        SortedSet<Integer> sortedSet = new TreeSet<>();
        sortedSet.add(987);
        sortedSet.add(1);
        sortedSet.add(435);
        SortedSet sortedautogen = Collections.checkedSortedSet(sortedSet, Integer.class);
        sortedautogen.add(5);
        //sortedautogen.add(12.0) error
        out.println("type safe " + sortedautogen);
        out.println();

        out.println("implementing emptySet method");
        Set<Integer> emptySet = Collections.emptySet();
        // emptySet.add(1); error
        out.println(emptySet);
        out.println();

        out.println("implementing emptyNavigableSet method");
        NavigableSet<Integer> emptyNavigableSet = Collections.emptyNavigableSet();
         // emptyNavigableSet.add(12); error
        out.println(emptyNavigableSet);
        out.println();

        out.println("implementing emptySortedSet method");
        SortedSet<Integer> emptySortedSet = Collections.emptySortedSet();
        // emptySortedSet.add(1) error
        out.println(emptySortedSet);
        out.println();

        out.println("implementing singleton method");
        Set<String> singletonSet = Collections.singleton("hello");
        out.println(singletonSet);
        out.println();

        out.println("implementing synchronizedSet method");
        Set<Integer> syncSet = Collections.synchronizedSet(new HashSet<>());
        syncSet.add(1);
        syncSet.add(2);
        syncSet.add(3);
        out.println(syncSet);
        out.println();

        out.println("implementing synchronizedNavigableSet method");
        NavigableSet<Integer> syncNavigableSet = Collections.synchronizedNavigableSet(new TreeSet<>());
        syncNavigableSet.add(12);
        syncNavigableSet.add(67);
        syncNavigableSet.add(54);
        out.println(syncNavigableSet);
        out.println();

        out.println("implementing synchronizedSortedSet method");
        SortedSet<Integer> syncSortedSet = Collections.synchronizedSortedSet(new TreeSet<>());
        syncSortedSet.add(987);
        syncSortedSet.add(654);
        syncSortedSet.add(76);
        out.println(syncSortedSet);
        out.println();

        out.println("implementing unmodifiableSet method");
        Set<Integer> unmodSet = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(1, 2, 3, 4, 5)));
        // unmodSet.add(12) error
        out.println(unmodSet);
        out.println();

        out.println("implementing unmodifiableNavigableSet method");
        NavigableSet<Integer> unmodNavigableSet = Collections.unmodifiableNavigableSet(new TreeSet<>(Arrays.asList(34, 35, 78)));
        // unmodNavigableSet.add(78) error
        out.println(unmodNavigableSet);
        out.println();

        out.println("implementing unmodifiableSortedSet method");
        SortedSet<Integer> unmodSortedSet = Collections.unmodifiableSortedSet(new TreeSet<>(Arrays.asList(56, 89, 90)));
        // unmodSortedSet.add(90) error
        out.println(unmodSortedSet);
        out.println();
    }
}
