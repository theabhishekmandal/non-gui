package Collection_demo.Map;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import static java.lang.System.out;

/**
 *  HashMap extends AbstractMap and implements the Map interface.
 *  A map is an object that stores associations between key value pairs.
 *  Given a key you can find its value.Both keys and values are objects.
 *  The keys must be unique but the values can be duplicated.
 *  Some maps can accept null values and null keys while some cannot.
 *  Also Maps don't implement the Iterable interface i.e you cannot iterate through a map using a for-each
 *  for  loop
 *  The order of the elements is not stored in hashMap
 *
 *  It has the following methods:
 *  1 void clear()                      :   Removes all Key/value pairs from the invoking map.
 *
 *  2 default V compute(K k, BiFunction :   calls func to construct a new value. If func returns non-null
 *     <? super K, ? super V, ? extends     , the new key/value pair is added to the map, any preexisting
 *     V>func)                              pairing is removed, and the new value is returned. If func
 *                                          returns null, any preexisting pairing is removed, and null is
 *                                          returned
 *
 *  3 default V computeIfAbsent(K k,    :   returns the value associated with the key k, Otherwise,
 *    Function<? super K, ? extends V>      the value is constructed through a call to a func and the
 *    func)                                 pairing is entered into the map and the constructed value is
 *                                          returned. If no value can be constructed, null is returned.
 *                                          
 *  4 default V computeIfPresent(K k,   :   if k is in the map, a new value is constructed through a call 
 *    BiFunction <? super K, ? super V,     to func and the new value replaces the old value in the map. 
 *    ? extends V>func)                     In this case the new value is returned. If the value returned
 *                                          by func is null, the existing key and value are removed from
 *                                          the map and null is returned.
 *
 *  5 boolean containsKey(Object k)     :   returns true if the invoking map contains the given key
 *                                          otherwise returns false
 *
 *  6 boolean containsValue(Object k)   :   returns true if the invoking map contains the given value
 *                                          otherwise returns false
 *
 *  7 Set<Map.Entry<K, V>> entrySet()   :   returns a Set that contains the entries in the map. The
 *                                          set contains objects of type Map.Entry. Thus, this method
 *                                          provides a set view of the invoking map
 *
 *  8 boolean equals(Object ob)         :   returns true if obj is a Map and contains the same entries.
 *                                          Otherwise, returns false.
 *
 *  9 default void forEach(BiConsumer   :   executes action on each element in the invoking map. A
 *  <? super K, ? super V> action)          ConcurrentModificationException will be throws if an object
 *                                          is removed during the process
 *
 *  10 V get(Object k)                  :   returns the value associated with the key k. Returns null if
 *                                          if the key is not found.
 *
 *  11 default V getOrDefault(Object k, :   returns the value associated with k if it is in the map.
 *     V defVal)                            Otherwise defVal is returned.
 *
 *  12 int hashCode()                   :   returns the hash code for the invoking map.
 *
 *  13 boolean isEmpty()                :   returns true if the invoking map is empty. Otherwise returns
 *                                          false.
 *
 *  14 Set<K> KeySet()                  :   returns a Set that contains the keys in the invoking map.
 *                                          This method provides a setview of hte keys in the invoking map.
 *
 *  15 default V merge(K k, V v,        :   If k is not in the map, the pairing k, v is added to the map.
 *     BiFunction<? super V, ? super V,     In this case, v is returned. Otherwise, func returns a new value
 *     ? extends V> func)                   based on the old value, the key is updated to use this value,
 *                                          and merge() returns this value. If the value returned by func is
 *                                          null, the existing key and value are removed from the map and
 *                                          null is returned.
 *
 *  17 V put(K k, V v)                  :   puts an entry in the invoking map, overwriting any previous value
 *                                          associated with the key. The key and value are k and v,
 *                                          respectively. Returns null if the key did not already exist.
 *                                          Otherwise, the previous value linked to the key is returned
 *
 *  18 void putAll(Map<? extends K, ?   :   puts all the entries from m into this map.
 *      extends V> m)
 *
 *  19 default V putIfAbsent(K k, V v)  :   inserts the key/value, pair into the invoking map if this
 *                                          pairing is not already present or if hte existing value is
 *                                          null. Returns the old value. The null value is returned when no
 *                                          previous mapping exists, or the value is null.
 *
 *  20 V remove(Object k)               :   removes the entry whose key equals k.
 *
 *  21 default boolean remove(Object k, :   If the key/value pair specified by k and v is in the invoking map,
 *     Object v)                            it is removed and true is returned. Otherwise, false is returned.
 *
 *  22 default boolean replace(K k,     :   if the key/value pair specified by k and oldV is in the invoking
 *     V oldV, V new V)                     map, the value is replaced by new V and true is returned.
 *                                          Otherwise, false is returned.
 *
 *  23 default V replace(K k, V v)      :   If the key specified by k is in the invoking map, its value is
 *                                          set to v and the previous value is returned. Otherwise, null is
 *                                          returned
 *
 *  24 default void replaceAll(         :   Executes func on each element of the invoking map,replacing the
 *  BiFunction<? super K, ? super V,        element with the result returned byfunc. A
 *  ? extends V> func)                      ConcurrentModificationException will bethrown if an element
 *                                          is removed during the process.
 *
 */

public class HashMapDemo {
    public static void main(String[] args) {
        HashMap<String , String> arr = new HashMap<>();
        arr.put("Abhishek", "Mandal");
        arr.put("hello", "world");
        arr.put("Mangekyo", "Sharingan");
        arr.put("Rasen", "Shuriken");
        arr.put("Taijuu", "kage bunshinn jutsu");

        // Getting the collections view of the map as map does not implements collections interface
        // entrySet() method provides a set of key value pairs
        Set<Map.Entry<String, String>> set = arr.entrySet();

        // now looping and displaying the keys and values
        for(Map.Entry<String, String> ob : set)
            out.println("Key: " + ob.getKey() + " Value:" + ob.getValue());
        
        out.println(); 
        
        // Getting the keyset and printing the values
        for(String i : arr.keySet()){
        	out.println("Key: "+ i + " " + "Value:" + arr.get(i));
        }

        out.println();

        // implementing compute method and updating the value in key value pair
        // this key does not exists so new value is added
        out.println("Key: " + "blah " + "Value : " + arr.compute("blah", (key, value) -> (value == null) ? "Nothing found" : (value += " " + value)));
        // this key exists so new value is added
        out.println("Key: " + "hello " + "Value : " + arr.compute("blah", (key, value) -> (value == null) ? "Nothing found" : (value += " " + value)));
        out.println();

        //implenting computeIfAbsent method
        // if key exists then nothing is assigned
        out.println("Key :" + "Abhishek" + " " + "Value:" + arr.computeIfAbsent("Abhishek", (key) -> "nothing found"));
        // it key does not exists then it puts a new value
        out.println("Key :" + "fuuton" + " " + "Value:" + arr.computeIfAbsent("fuuton", (key) -> "nothing found"));
        out.println();
        
        //implementing computeIfPresent method
        // if the key is present then new value is assigned
        out.println("Key :" + "fuuton" + " " + "Value:" + arr.computeIfPresent("fuuton", (key, value) -> "Wind style"));
        // if the key is not present then null is returned
        out.println("Key :" + "mokton" + " " + "Value:" + arr.computeIfPresent("mokton", (key, value) -> "nothing found"));
        out.println();
    }
}
