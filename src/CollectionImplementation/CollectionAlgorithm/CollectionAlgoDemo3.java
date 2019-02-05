package CollectionImplementation.CollectionAlgorithm;
import java.util.*;
import static java.lang.System.*;

/**
 * Below is the implementation of several methods in Collections for Map:
 *
 *  1 static <K, V> Map<K, V>
 *    checkedMap(Map<K, V> c,
 *    Class<K> keyT,
 *    Class<V> valueT)                          :   returns a runtime type-safe view of a Map. An attempt
 *                                                  to insert an incompatible element will cause a
 *                                                  ClassCastException.
 *
 *  2 static <K, V> NavigableMap<K, V>
 *    checkedNavigableMap(NavigableMap<K, V>nm,
 *    Class<K> keyT,
 *    Class<V> valueT)                          :   returns a run-time type-safe view of a NavigableMap.
 *                                                  An attempt to insert an incompatible element will cause
 *                                                  a ClassCastException.
 *
 *  3 static <K, V> SortedMap<K, V>
 *    checkedSortedMap(SortedMap<K, V> c,
 *    Class<K> keyT,
 *    Class<V> valueT)                          :   returns a run-time type-safe view of a SortedMap. An
 *                                                  attempt to insert an incompatible element will cause
 *                                                  a ClassCastException.
 *
 *  4 static <K, V> Map<K, V> emptyMap()        :   returns an immutable, empty Map object of the inferred type.
 *
 *  5 static <K, V> NavigableMap<K, V>
 *    emptyNavigableMap()                       :   returns an immutable, empty NavigableMap object of the inferred type.
 *
 *  6 static <K, V> SortedMap<K, V>
 *    emptySortedMap()                          :   returns an immutable, empty NavigableMap object of the inferred type.
 *
 *  7 static <E> Set<E> newSetFromMap(
 *    Map<E, boolean> m)                        :   creates and returns a set backed by the map specified by m, which
 *                                                  must be empty at the time this method is called.
 *
 */
public class CollectionAlgoDemo3 {
    public static void main(String[] args) {

        // without the different type of checkmap methods
        out.println("without the different type of checkmap methods");
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "hello");
        map.put(2, "worldl");
        map.put(3, "mangekeyo");
        // without checkmap methods map variable does not force withoutGenericMap variable to be of the same type
        Map withoutGenericMap = map;
        withoutGenericMap.put(4, 2);
        withoutGenericMap.put(5, 2);
        withoutGenericMap.put(6, 2);
        out.println(withoutGenericMap);
        out.println();

        // implementing the checkedMap method
        out.println("implementing the checkedMap method");
        map = new HashMap<>();
        map = Collections.checkedMap(map, Integer.class, String.class);
        map.put(1, "hello");
        map.put(2, "worldl");
        map.put(3, "mangekeyo");
        Map autogen = map;
        autogen.put(4, "sharingan");
        autogen.put(5, "welcome");
        // any other type will throw ClassCastException
        // autogen.put("String", "String");
        out.println(autogen);
        out.println();

        // implementing checkedNavigableMap
        // here you cannot use the map variable and use the checkedNavigableMap method
        // because the map variable does not implement NavigableMap interface
        out.println("implementing checkedNavigableMap method");
        NavigableMap<Integer, String> navigablemap = new TreeMap<>();
        navigablemap = Collections.checkedNavigableMap(navigablemap, Integer.class, String.class);
        navigablemap.put(1, "one");
        navigablemap.put(2, "two");
        NavigableMap navigableautogen = navigablemap;
        navigableautogen.put(3, "three");
        navigableautogen.put(4, "four");
        // any other type will show ClassCastException
        // navigableautogen.put(4, 4);
        out.println(navigableautogen);
        out.println();

        // implementing checkedSortedMap method
        // here either use NavigableMap or SortedMap and not Map interface
        out.println("implementing checkedSortedMap method");
        SortedMap<Integer, Integer> sortedMap = new TreeMap<>();
        sortedMap = Collections.checkedSortedMap(sortedMap, Integer.class, Integer.class);
        sortedMap.put(1, 1);
        sortedMap.put(2, 2);
        SortedMap sortedautogen = sortedMap;
        sortedautogen.put(3, 3);
        sortedautogen.put(4, 4);
        // any other type will show ClassCastException
        // sortedautogen.put(5, 5);
        out.println(sortedautogen);
        out.println();

        // implementing emptyMap method
        out.println("implementing emptyMap method");
        Map<Integer, Integer> emptyMap = Collections.emptyMap();
        // any assignment will cause exception
        // emptyMap.put(1, 1);
        out.println(emptyMap);
        out.println();

        // implementing emptyNavigableMap method
        out.println("implementing emptyNavigableMap method");
        NavigableMap<Integer, Integer> emptyNavigableMap = Collections.emptyNavigableMap();
        // any assignment will cause exception
        // emptyNavigableMap.put(1, 1);
        out.println(emptyNavigableMap);
        out.println();

        // implementing emptySortedMap method
        out.println("implemting emptySortedMap method");
        SortedMap<Integer, Integer> emptySortedMap = Collections.emptySortedMap();
        // any assignment will cause exception
        // emptySortedMap.put(1, 1);
        out.println(emptySortedMap);
        out.println();

        // implementing newSetFromMap method
        // when anything is added to the set then it is added to map object too
        out.println("implementing newSetFromMap method");
        Map<Integer, Boolean> weakmap = new WeakHashMap<>();
        Set<Integer> weakset = Collections.newSetFromMap(weakmap);
        weakset.add(1);
        weakset.add(2);
        weakset.add(3);
        weakset.add(4);
        out.println("set elements " + weakset);
        out.println("map elements " + weakmap);
        out.println();

    }
}
