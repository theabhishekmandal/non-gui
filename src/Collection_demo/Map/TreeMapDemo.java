package Collection_demo.Map;

import java.util.*;

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
 *   TreeMap()                           :  This creates an empty TreeMap
 *
 *   TreeMap(Comparator<? super K> comp) :  This creates an empty TreeMap and elements will be arranged
 *                                          on the basis of the comparator used
 *
 *   TreeMap(Map<? extends K, ? extends  :  This initializes a tree map iwth the entries form m, which
 *   V>m)                                   will be sorted by using the natural order of the keys.
 *
 *   TreeMap(SortedMap<K,? extends V> sm):  This initializes a tree map with the entries from sm, which
 *                                          will be sorted in the same order as sm.
 */
public class TreeMapDemo {
    public static void main(String[] args) {

    }
}
