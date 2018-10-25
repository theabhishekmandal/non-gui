package Collection_demo.Map;
import java.util.*;
import static java.lang.System.*;
/**
 * LinkedHashMap extends HashMap. It maintains a linked list of the entries in the map, in the order in which they
 * were inserted.
 * The elements will be returned in the same way as they were inserted into the map.
 * You can also create a LinkedHashMap that returns its elements in the order in which they were last accessed.
 * LinkedHashMap is a generic class that has this declaration:
 *              class LinkedHashMap<K, V>   here K specifies the type of keys and V specifies the type of values
 *              
 * LinkedHashMap defines the following constructors:
 * 
 * 		LinkedHashMap()
 * 		LinkedHashMap(Map<? extends K, ? extends V> m)
 * 		LinkedHashMap(int capacity)
 * 		LinkedHashMap(int capacity, float fillRatio)
 * 		LinkedHashMap(int capacity, float fillRatio, boolean Order)
 * 
 * The first constructor a default LinkedHashMap.
 * The second form initializes the LinkedHashMap with the elements from m.
 * The third form initializes the capacity. 
 * The fourth form initializes both capacity and fill ratio.
 * The default capacity is 16. The default ratio is 0.75.
 * The last form allows you to specify whether the elements will be stored in the linked list by insertion 
 * order, or by order of last access. If Order is true, then access order is used. If Order is false, 
 * then insertion order is used.
 * 
 * LinkedHashMap adds only one method to those defined by HashMap. This method is removeEldestEntry():
 * 		protected boolean removeEldestEntry(Map.Entry<K, V> e)
 * 
 * This method is called by put( ) and putAll( ). The oldest entry is passed in e. By default, this 
 * method returns false and does nothing. However, if you override this method, then you can have
 * the LinkedHashMap remove the oldest entry in the map. To do this, have your override return true. 
 * To keep the oldest entry, return false.
 * 
 */
public class LinkedHashMapDemo {
	public static void randomOrderAcess(LinkedHashMap<?, ?> arr){
		//Set<Map.Entry<?, ?>> set = arr.entrySet();
		int num = (int)Math.random() * (arr.size() + 1);
	}
    public static void main(String[] args) {
    	LinkedHashMap<Integer, String> arr = new LinkedHashMap<>();
    	arr.put(1, "one");
    	arr.put(2, "two");
    	arr.put(3, "three");
    	arr.put(4, "four");
    	arr.put(5, "five");
    	randomOrderAcess(arr);
    }
}
