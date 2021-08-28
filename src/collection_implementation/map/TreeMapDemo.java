package collection_implementation.map;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import static java.lang.System.out;

/**
 * TreeMap class extends AbstractMap and implements the NavigableMap interface.
 * NavigableMap interface extends SortedMap Interface.
 * It creates map in a tree structure.
 * A TreeMap provides an efficient means of storing key/value pairs in sorted order and
 * allows rapid retrieval.
 * Unlike HashMap it guarantees that the elements will be inserted in ascending order by default
 *
 * TreeMap is a generic class that has this declaration:
 *      class TreeMap<K, V> Here, K specifies the type of keys and V specifies the type of values.
 *
 * The following TreeMap constructors are defined:
 *
 * 1 TreeMap()                          :  This creates an empty TreeMap.
 *
 * 2 TreeMap(Comparator<? super K>
 *     comp)                            :  This creates an empty TreeMap and elements will be arranged
 *                                          on the basis of the comparator used.
 *
 * 3 TreeMap(Map<? extends K, ? extends
 *   V>m)                               :  This initializes a tree map with the entries form m, which
 *                                          will be sorted by using the natural order of the keys.
 *
 * 4 TreeMap(SortedMap<K,? extends V>
 *     sm)                              :  This initializes a tree map with the entries from sm, which
 *                                          will be sorted in the same order as sm.
 *
 *
 * The following methods are defined in the SortedMap Interface:
 *
 * 1 Comparator<? super K> comparator() :   returns the invoking sorted map's comparator. If
 *                                          natural ordering is used for the invoking map, null
 *                                          is returned.
 *
 * 2 K firstKey()                       :   returns the first key in the invoking map.
 *
 * 3 SortedMap<K, V> headMap(K end)     :   returns a sorted map for those map entries with keys that are
 *                                          less than end.
 *
 * 4 K lastKey()                        :   returns the last key in the invoking map.
 *
 * 5 SortedMap<K, V> subMap(K start, K
 *   end)                               :   returns a map containing those entries with keys that are
 *                                          greater than or equal to start and less than end.
 *
 * 6 SortedMap<K, V> tailMap(K start)   :   returns a map containing those entries with keys that
 *                                          are greater than or equal to start.
 *
 *
 * The following methods are defined in the NavigableMap Interface:
 *
 * 1 Map.Entry<K,V> ceilingEntry(K obj) :  Searches the map for the smallest key k such that
 *                                          k >= obj. If such a key is found, its entry is returned.
 *                                          Otherwise, null is returned.
 *
 * 2 K ceilingKey(K obj)                :  Searches the map for the smallest key k such
 *                                          that k >= obj. If such a key is found, it is returned.
 *                                          Otherwise, null is returned.
 *
 * 3 NavigableSet<K> descendingKeySet() :  Returns a NavigableSet that contains the keys in
 *                                          the invoking map in reverse order. Thus, it returns
 *                                          a reverse set-view of the keys. The resulting set is
 *                                          backed by the map.
 *
 * 4 NavigableMap<K,V> descendingMap()  :  Returns a NavigableMap that is the reverse of the
 *                                          invoking map. The resulting map is backed by the
 *                                          invoking map.
 *
 * 5 Map.Entry<K,V> firstEntry()        :  Returns the first entry in the map. This is the entry
 *                                          with the least key.
 *
 * 6 Map.Entry<K,V> floorEntry(K obj)   :  Searches the map for the largest key k such that
 *                                          k <= obj. If such a key is found, its entry is returned.
 *                                          Otherwise, null is returned.
 *
 * 7 K floorKey(K obj)                  :  Searches the map for the largest key k such that
 *                                          k <= obj. If such a key is found, it is returned.
 *                                          Otherwise, null is returned.
 *
 * 8 NavigableMap<K,V>
 *    headMap(K upperBound, boolean
 *    incl)                             :   Returns a NavigableMap that includes all entries
 *                                          from the invoking map that have keys that are less
 *                                          than upperBound. If incl is true, then an element
 *                                          equal to upperBound is included. The resulting map
 *                                          is backed by the invoking map.
 *
 * 9 Map.Entry<K,V> higherEntry(K obj)  :  Searches the set for the largest key k such that
 *                                          k > obj. If such a key is found, its entry is returned.
 *                                          Otherwise, null is returned.
 *
 * 10 K higherKey(K obj)                :  Searches the set for the largest key k such that
 *                                          k > obj. If such a key is found, it is returned.
 *                                          Otherwise, null is returned.
 *
 * 11 Map.Entry<K,V> lastEntry()        :  Returns the last entry in the map. This is the entry
 *                                          with the largest key.
 *
 * 12 Map.Entry<K,V> lowerEntry(K obj)  :  Searches the set for the largest key k such that
 *                                          k < obj. If such a key is found, its entry is returned.
 *                                          Otherwise, null is returned.
 *
 * 13 K lowerKey(K obj)                 :  Searches the set for the largest key k such that
 *                                          k < obj. If such a key is found, it is returned.
 *                                          Otherwise, null is returned.
 *
 * 14 NavigableSet<K> navigableKeySet() :  Returns a NavigableSet that contains the keys in the
 *                                          invoking map. The resulting set is backed by the
 *                                          invoking map.
 *
 * 15 Map.Entry<K,V> pollFirstEntry()   :  Returns the first entry, removing the entry in the
 *                                          process. Because the map is sorted, this is the entry
 *                                          with the least key value. null is returned if the map
 *                                          is empty.
 *
 * 16 Map.Entry<K,V> pollLastEntry()    :  Returns the last entry, removing the entry in the
 *                                          process. Because the map is sorted, this is the entry
 *                                          with the greatest key value. null is returned if the
 *                                          map is empty.
 *
 * 17 NavigableMap<K,V>
 *      subMap(K lowerBound,
 *      boolean lowIncl,
 *      K upperBound
 *      boolean highIncl)               :  Returns a NavigableMap that includes all entries
 *                                          from the invoking map that have keys that are
 *                                          greater than lowerBound and less than upperBound. If
 *                                          lowIncl is true, then an element equal to lowerBound
 *                                          is included. If highIncl is true, then an element equal
 *                                          to highIncl is included. The resulting map is backed
 *                                          by the invoking map.
 *
 * 18 NavigableMap<K,V>
 *      tailMap(K lowerBound, boolean
 *      incl)                           :  Returns a NavigableMap that includes all entries
 *                                          from the invoking map that have keys that are
 *                                          greater than lowerBound. If incl is true, then an
 *                                          element equal to lowerBound is included. The
 *                                          resulting map is backed by the invoking map.
 */
public class TreeMapDemo {
    public static void main(String[] args) {
        TreeMap<Integer, String> arr = new TreeMap<>();
        arr.put(1, "Abhishek");
        arr.put(2, "Mohan");
        arr.put(3, "Daksh");
        arr.put(4, "Shivam");

        out.println(arr);
        out.println();

        out.println("Implementing SortedMap interface methods");

        out.println("implementing the comparator method, returns null for natural order");
        out.println(arr.comparator());
        out.println();

        out.println("implementing the firstKey method");
        out.println(arr.firstKey());
        out.println();

        out.println("implementing the headMap method");
        out.println(arr.headMap(3, true));
        out.println();

        out.println("implementing the lastKey method");
        out.println(arr.lastKey());
        out.println();

        out.println("implementing the subMap method");
        out.println(arr.subMap(2, 4));
        out.println();

        out.println("implementing the tailMap method");
        out.println(arr.tailMap(1, false));
        out.println();

        out.println("Implementing NavigableMap methods");

        out.println("implementing ceilingEntry method");
        out.println(arr.ceilingEntry(5));
        out.println();

        out.println("implementing ceilingKey method");
        out.println(arr.ceilingKey(4));
        out.println();

        out.println("implementing descendingKehSet method");
        for(Integer i : arr.descendingKeySet())
            out.println(i + " " + arr.get(i));
        out.println();

        out.println("implementing descendingMap method");
        NavigableMap<Integer, String> reversemap = arr.descendingMap();
        out.println(reversemap);
        out.println();

        out.println("implementing firstEntry method");
        Map.Entry<Integer, String> one = arr.firstEntry();
        out.println(one);
        out.println();

        out.println("implementing floorEntry method");
        out.println(arr.floorEntry(2));
        out.println();

        out.println("implementing headMap method");
        out.println(arr.headMap(3, true ));
        out.println();

        out.println("implementing higherEntry method");
        out.println(arr.higherEntry(3));
        out.println();

        out.println("implementing higherKey method");
        out.println(arr.higherKey(3));
        out.println();

        out.println("implementing lastEntry method");
        out.println(arr.lastEntry());
        out.println();

        out.println("implementing lowerEntry method");
        out.println(arr.lowerEntry(3));
        out.println();

        out.println("implementing lowerKey method");
        out.println(arr.lowerKey(3));
        out.println();

        out.println("implementing navigableKeySet method");
        out.println(arr.navigableKeySet());
        out.println();

        out.println("implementing pollFirstEntry method");
        out.println(arr.pollFirstEntry());
        out.println();

        out.println("implementing pollLastEntry method");
        out.println(arr.pollLastEntry());
        out.println();

        out.println("implementing subMap method");
        out.println(arr.subMap(2, true, 4, true));
        out.println();

        out.println("implementing tailMap method");
        out.println(arr.tailMap(2, true));
    }
}
