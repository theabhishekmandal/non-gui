package collection_implementation.map;

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
 *  HashMap is a generic class and has the following declaration:
 *              class HashMap<K, V>   here K specifies the type of keys and V specifies the type of values
 *
 *  The following constructors are defined:
 *  HashMap()                           :   It constructs a default hashMap.
 *
 *  HashMap(Map
 *  <? extends K, ? extends V>m)        :   The second form initializes the hashmap using the elements of m.
 *
 *  HashMap(int capacity)               :   It initializes the capacity of the hashmap to capacity.
 *
 *  HashMap(int capacity, float fill    :   It initializes the capacity and fill ratio of the hashmap.
 *  Ratio)
 *
 *  It has the following methods:
 *
 *  1 void clear()                      :   Removes all Key/value pairs from the invoking map.
 *
 *  2 default V compute(K k, BiFunction
 *     <? super K, ? super V, ? extends
 *     V>func)                          :   calls func to construct a new value. If func returns non-null
 *                                          , the new key/value pair is added to the map, any preexisting
 *                                          pairing is removed, and the new value is returned. If func
 *                                          returns null, any preexisting pairing is removed, and null is
 *                                          returned
 *
 *  3 default V computeIfAbsent(K k,
 *    Function<? super K, ? extends V>
 *    func)                             :   returns the value associated with the key k, Otherwise,
 *                                          the value is constructed through a call to a func and the
 *                                          pairing is entered into the map and the constructed value is
 *                                          returned. If no value can be constructed, null is returned.
 *                                          
 *  4 default V computeIfPresent(K k,
 *    BiFunction <? super K, ? super V,
 *    ? extends V>func)                 :   if k is in the map, a new value is constructed through a call
 *                                          to func and the new value replaces the old value in the map.
 *                                          In this case the new value is returned. If the value returned
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
 *  9 default void forEach(BiConsumer
 *  <? super K, ? super V> action)      :   executes action on each element in the invoking map. A
 *                                          ConcurrentModificationException will be throws if an object
 *                                          is removed during the process
 *
 *  10 V get(Object k)                  :   returns the value associated with the key k. Returns null if
 *                                          if the key is not found.
 *
 *  11 default V getOrDefault(Object k,
 *     V defVal)                        :   returns the value associated with k if it is in the map.
 *                                          Otherwise defVal is returned.
 *
 *  12 int hashCode()                   :   returns the hash code for the invoking map.
 *
 *  13 boolean isEmpty()                :   returns true if the invoking map is empty. Otherwise returns
 *                                          false.
 *
 *  14 Set<K> KeySet()                  :   returns a Set that contains the keys in the invoking map.
 *                                          This method provides a setview of hte keys in the invoking map.
 *
 *  15 default V merge(K k, V v,
 *     BiFunction<? super V, ? super V,
 *     ? extends V> func)               :   If k is not in the map, the pairing k, v is added to the map.
 *                                          In this case, v is returned. Otherwise, func returns a new value
 *                                          based on the old value, the key is updated to use this value,
 *                                          and merge() returns this value. If the value returned by func is
 *                                          null, the existing key and value are removed from the map and
 *                                          null is returned.
 *
 *  17 V put(K k, V v)                  :   puts an entry in the invoking map, overwriting any previous value
 *                                          associated with the key. The key and value are k and v,
 *                                          respectively. Returns null if the key did not already exist.
 *                                          Otherwise, the previous value linked to the key is returned
 *
 *  18 void putAll(Map<? extends K, ?
 *      extends V> m)                   :   puts all the entries from m into this map.
 *
 *  19 default V putIfAbsent(K k, V v)  :   inserts the key/value, pair into the invoking map if this
 *                                          pairing is not already present or if hte existing value is
 *                                          null. Returns the old value. The null value is returned when no
 *                                          previous mapping exists, or the value is null.
 *
 *  20 V remove(Object k)               :   removes the entry whose key equals k.
 *
 *  21 default boolean remove(Object k,
 *     Object v)                        :    If the key/value pair specified by k and v is in the invoking map,
 *                                          it is removed and true is returned. Otherwise, false is returned.
 *
 *  22 default boolean replace(K k,
 *     V oldV, V new V)                 :   if the key/value pair specified by k and oldV is in the invoking
 *                                          map, the value is replaced by new V and true is returned.
 *                                          Otherwise, false is returned.
 *
 *  23 default V replace(K k, V v)      :   If the key specified by k is in the invoking map, its value is
 *                                          set to v and the previous value is returned. Otherwise, null is
 *                                          returned
 *
 *  24 default void replaceAll(
 *  BiFunction<? super K, ? super V,
 *  ? extends V> func)                  :   Executes func on each element of the invoking map,replacing the
 *                                          element with the result returned byfunc. A
 *                                          ConcurrentModificationException will bethrown if an element
 *                                          is removed during the process.
 *
 *  25 int size()                       :   returns the number of key value pairs in the map.
 *
 *  26 Collection<V> values()           :   returns a collection containing the values in the map.
 *                                          This method provides a collection view of the values in the map.
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

        out.println("printing values using entrySet method");
        for(Map.Entry<String, String> ob : set)
            out.println("Key: " + ob.getKey() + " Value: " + ob.getValue());
        out.println(); 
        
        out.println("printing values using keySet method");
        for(Map.Entry<String, String> entry : arr.entrySet()){
        	out.println("Key: "+ entry.getKey() + " " + "Value: " + entry.getValue());
        }
        out.println();

        out.println("implementing compute method and updating the value in key value pair");
        out.println("when key does not exits so new value is added");
        out.println("Key: " + "blah " + "Value: " + arr.compute("blah", (key, value) -> (value == null) ? "Nothing found" : (value += " " + value)));
        out.println("when new key exists then new value is added");
        out.println("Key: " + "hello " + "Value: " + arr.compute("blah", (key, value) -> (value == null) ? "Nothing found" : (value += " " + value)));
        out.println();

        out.println("implementing computeIfAbsent method");
        out.println("if key exists then nothing is assigned");
        out.println("Key: " + "Abhishek" + " " + "Value: " + arr.computeIfAbsent("Abhishek", (key) -> "nothing found"));
        out.println("if key does not exists then new value is added");
        out.println("Key: " + "fuuton" + " " + "Value: " + arr.computeIfAbsent("fuuton", (key) -> "nothing found"));
        out.println();
        
        out.println("implementing computeIfPresent method");
        out.println("if key is present then new value is assigned");
        out.println("Key: " + "fuuton" + " " + "Value: " + arr.computeIfPresent("fuuton", (key, value) -> "Wind style"));
        out.println("if the key is not present then null is returned");
        out.println("Key: " + "mokton" + " " + "Value: " + arr.computeIfPresent("mokton", (key, value) -> "nothing found"));
        out.println();

        out.println("implementing containsKey method");
        out.println(arr.containsKey("fuuton"));
        out.println();

        out.println("implementing containsValue method");
        out.println(arr.containsValue("Wind style"));
        out.println();

        out.println("implementing the  equals method");
        out.println(arr.equals(new HashMap<>()));
        out.println();

        out.println("implementing the get method to get the value associated with the key");
        out.println("Key: " + "hello" + " Value: " + arr.get("hello"));
        out.println();

        out.println("implementing the getOrDefault method");
        out.println("Key: " + "key not present" + " Value: " + arr.getOrDefault("key not present", "defaultValue"));
        out.println();

        out.println("implementing merge method, it is used to update a specified key value pair");
        out.println(arr.merge("blah", "Nothing found Nothing found", (k,v) ->
            v.replace("Nothing found Nothing found", "Value update in merge method")));
        out.println();

        HashMap<String, String> num = new HashMap<>();
        num.put(1 + "", "one");
        num.put(2 + "", "two");
        num.put(3 + "", "three");

        out.println("implementing putAll method");
        arr.putAll(num);
        out.println(arr + "\n");

        out.println("implementing putIfAbsent method");
        out.println(arr.putIfAbsent("3", "three"));
        out.println();

        out.println("implementing remove method");
        out.println(arr.remove("2"));
        out.println();

        out.println("implementing remove method of key value pair");
        out.println(arr.remove("3", "three"));
        out.println(arr.remove("3", "three"));
        out.println();

        out.println("implementing replace method with old and new value as parameters");
        out.println(arr.replace("1", "one", "ONE"));
        out.println();

        out.println("implementing replace method with only key value as parameter");
        out.println(arr.replace("Abhishek", "Mandal", "MANDAL"));
        out.println();

        // implementing replaceAll method, if value length is greater than 3 than get length upto 3 character
        out.println("implementing replaceAll method ");
        arr.replaceAll((k, v) -> {
            if(v.length() > 3)
                v = v.substring(0,3);
            return v;
        });
        out.println(arr);
        out.println();

        out.println("implementing size method");
        out.println(arr.size());
        out.println();

        out.println("implementing values method");
        out.println(arr.values());
        out.println();
    }
}
