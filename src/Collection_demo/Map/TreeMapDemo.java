package Collection_demo.Map;

import java.util.*;

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
        TreeMap<Integer, String> arr = new TreeMap<>();
        arr.put(1, "Abhishek");
        arr.put(2, "Mohan");
        arr.put(3, "Daksh");
        arr.put(4, "Shivam");

        out.println(arr);
        out.println();

        //Implementing SortedMap interface methods

        //implementing the comparator method, returns null for natural order
        out.println(arr.comparator());
        out.println();

        //implementing the firstKey method
        out.println(arr.firstKey());
        out.println();

        //implementing the headMap method
        out.println(arr.headMap(3, true));
        out.println();

        //implementing the lastKey method
        out.println(arr.lastKey());
        out.println();

        //implementing the subMap method
        out.println(arr.subMap(2, 4));
        out.println();

        //implementing the tailMap method
        out.println(arr.tailMap(1, false));
        out.println();

        //Implementing NavigableMap methods

        //implementing ceilingEntry method
        out.println(arr.ceilingEntry(5));
        out.println();

        //implementing ceilingKey method
        out.println(arr.ceilingKey(4));
        out.println();

        //implementing descendingKehSet method
        for(Integer i : arr.descendingKeySet())
            out.println(i + " " + arr.get(i));
        out.println();

        //implementing descendingMap method
        NavigableMap<Integer, String> reversemap = arr.descendingMap();
        out.println(reversemap);
        out.println();

        //implementing firstEntry method
        Map.Entry<Integer, String> one = arr.firstEntry();
        out.println(one);
        out.println();

        //implementing floorEntry method
        out.println(arr.floorEntry(2));
        out.println();


    }
}
