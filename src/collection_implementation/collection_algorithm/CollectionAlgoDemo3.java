package collection_implementation.collection_algorithm;

import java.util.*;

import static java.lang.System.out;

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
 *  8 static <K,V> Map<K,V> singletonMap(
 *    K k, V v)                                 :   returns the key/value pair k/v as an immutable map. This is an easy
 *                                                  way to convert a single key/value pair into a map.
 *
 *  9 static <K,V> Map<K,V> synchronizedMap(
 *     Map<K, V> m)                             :   returns a thread safe map backed by m.
 *
 *  10 static <K,V> NavigableMap<K,V>
 *      synchronizedNavigableMap(
 *      NavigableMap<K, V> nm)                  :   returns a synchronized navigable map backed by nm.
 *
 *  11 static <K,V> SortedMap<K,V>
 *      synchronizedSortedMap(SortedMap<K,
 *      V>sm)                                   :   returns a thread-safe sorted map backed by sm.
 *
 *  12 static <K, V> Map<K, V>
 *      unmodifiableMap(Map<? extends K, ?
 *      extends V> m)                           :   returns an unmodifiable map backed by m.
 *
 *  13 static <K, V> NavigableMap<K, V>
 *      unmodifiableNavigableMap(
 *      NavigableMap<K, ? extends V> nm)        :   returns an unmodifiable navigable map backed by nm.
 *
 *  14 static <K, V> SortedMap<K, V>
 *      unmodifiableSortedMap(
 *      SortedMap<K, ? extends V> sm)           :   returns an unmodifiable sorted map backed by sm.
 *
 */
public class CollectionAlgoDemo3 {
    public static void main(String[] args) {

        // without checkmap methods map variable does not force withoutGenericMap variable to be type safe
        out.println("without the different type of checkmap methods");
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "hello");
        map.put(2, "worldl");
        map.put(3, "mangekeyo");
        Map withoutGenericMap = map;
        withoutGenericMap.put(4, 2);
        withoutGenericMap.put(5, 2);
        withoutGenericMap.put(6, 2);
        out.println("not type safe " + withoutGenericMap);
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
        out.println("type safe " + autogen);
        out.println();

        // here you cannot use the map variable and use the checkedNavigableMap method
        // because the map variable does not implement NavigableMap interface
        out.println("implementing checkedNavigableMap method");
        NavigableMap<Integer, String> navigablemap = new TreeMap<>();
        navigablemap = Collections.checkedNavigableMap(navigablemap, Integer.class, String.class);
        navigablemap.put(1, "one");
        navigablemap.put(2, "two");
        NavigableMap navigableAutogen = navigablemap;
        navigableAutogen.put(3, "three");
        navigableAutogen.put(4, "four");
        // any other type will show ClassCastException
        // navigableSutogen.put(4, 4);
        out.println("type safe " + navigableAutogen);
        out.println();

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
        out.println("type safe " + sortedautogen);
        out.println();

        out.println("implementing emptyMap method");
        Map<Integer, Integer> emptyMap = Collections.emptyMap();
        // any assignment will cause exception
        // emptyMap.put(1, 1);
        out.println(emptyMap);
        out.println();

        out.println("implementing emptyNavigableMap method");
        NavigableMap<Integer, Integer> emptyNavigableMap = Collections.emptyNavigableMap();
        // any assignment will cause exception
        // emptyNavigableMap.put(1, 1);
        out.println(emptyNavigableMap);
        out.println();

        out.println("implementing emptySortedMap method");
        SortedMap<Integer, Integer> emptySortedMap = Collections.emptySortedMap();
        // any assignment will cause exception
        // emptySortedMap.put(1, 1);
        out.println(emptySortedMap);
        out.println();

        // when anything is added to the set then it is added to map object too
        out.println("implementing newSetFromMap method");
        Map<Integer, Boolean> weakHashMap = new WeakHashMap<>();
        Set<Integer> weakSet = Collections.newSetFromMap(weakHashMap);
        weakSet.add(1);
        weakSet.add(2);
        weakSet.add(3);
        weakSet.add(4);
        out.println("set elements " + weakSet);
        out.println("map elements " + weakHashMap);
        out.println();

        out.println("implementing singletonMap method");
        Map<Integer, Integer> singleMap = Collections.singletonMap(1, 2);
        out.println(singleMap);
        out.println();

        out.println("implementing synchronized map method");
        Map<Integer, Integer> normalMap = new HashMap<>();
        normalMap.put(1, 1);
        normalMap.put(2, 1);
        Map<Integer, Integer> syncMap = Collections.synchronizedMap(normalMap);
        out.println(syncMap);
        out.println();

        out.println("implementing synchronized navigable map method");
        NavigableMap<Integer, Integer> navMap = new TreeMap<>();
        navMap.put(1, 1);
        navMap.put(2, 1);
        NavigableMap<Integer, Integer> syncNavMap =  Collections.synchronizedNavigableMap(navMap);
        out.println(syncNavMap);
        out.println();

        out.println("implementing synchronized sorted map method");
        SortedMap<Integer, Integer> sortMap = new TreeMap<>();
        sortMap.put(1, 1);
        sortMap.put(2, 2);
        SortedMap<Integer, Integer> syncSortMap = Collections.synchronizedSortedMap(sortMap);
        out.println(syncSortMap);
        out.println();

        out.println("implementing unmodifiable map method");
        Map<Integer, Integer> moMap = new HashMap<>();
        moMap.put(1, 2);
        moMap.put(2, 2);
        Map<Integer, Integer> unMoMap = Collections.unmodifiableMap(moMap);
        //unMoMap.put(3, 3); error
        out.println(unMoMap);
        out.println();

        out.println("implementing unmodifiable Navigable map method");
        NavigableMap<Integer, Integer> moNavMap = new TreeMap<>();
        moNavMap.put(1, 1);
        moNavMap.put(2, 1);
        NavigableMap<Integer, Integer> unMoNavMap = Collections.unmodifiableNavigableMap(moNavMap);
        out.println(unMoNavMap);
        out.println();

        out.println("implementing unmodifiable sorted map method");
        SortedMap<Integer, Integer> moSortMap = new TreeMap<>();
        moSortMap.put(1, 1);
        moSortMap.put(2, 1);
        SortedMap<Integer, Integer> unMoSortMap = Collections.unmodifiableSortedMap(moSortMap);
        out.println(unMoSortMap);
        out.println();
    }
}
