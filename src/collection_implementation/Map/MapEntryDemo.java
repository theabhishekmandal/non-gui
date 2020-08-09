package collection_implementation.Map;

import java.util.*;
import static java.lang.System.*;

/**
 * The Map.Entry interface enables you to work with a map entry. Recall that the entrySet() method declared
 * by the Map interface returns a Set containing the map entries. Each of these set elements is a Map.Entry
 * object. Map.Entry is generic and is declared like this:
 *
 *          interface Map.Entry<K, V>  here K specifies the type of keys and V specifies the type of values
 *
 * It has the following methods:
 *
 * 1 boolean equals(Object obj)         :   Returns true if obj is a Map.Entry whose key and value are equal
 *                                          to that of the invoking object.
 *
 * 2 K getKey()                         :   Returns the key for this map entry.
 *
 * 3 V getValue()                       :   Returns the value for this map entry.
 *
 * 4 int hashCode()                     :   Returns the hash code for this map entry.
 *
 * 5 V setValue(V v)                    :   Sets the value for this map entry to v. A ClassCastException
 *                                          is thrown if v is not the correct type for the map. An
 *                                          IllegalArgumentException is thrown if there is a problem with v.
 *                                          A NullPointerException is thrown if v is null and the map does
 *                                          not permit null keys. An UnsupportedOperationException is
 *                                          thrown if the map cannot be changed.
 */

public class MapEntryDemo {
    public static void main(String[] args) {
        NavigableMap<Integer, String> arr = new TreeMap<>();
        arr.put(1,"Abhishek");
        arr.put(2, "Mandal");
        arr.put(3, "hello");
        arr.put(4, "World");

        Set<Map.Entry<Integer,String>> blah = arr.entrySet();
        for(Map.Entry<Integer, String> it : blah){
            // implementing setValue method
            it.setValue(it.getValue().toUpperCase());

            //implementing getKey and getValue method
            out.println("Key "+ it.getKey() + " Value " + it.getValue());

            //implementing hashcode method
            out.println(it.hashCode());
        }
    }
}
