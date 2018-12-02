package CollectionImplementation.Map;
import java.util.*;

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
	private static final int max = 6;
	public static void main(String[] args) {
		
		// creating a LinkedHashMap in which access order is preserved
		// true is used to indicate that access order is used
		// implementing removeEldestEntry method
		// in this method if number of elements inserted is greater than 6 
		// then the last accessed element is removed from the LinkedHashMap
    	LinkedHashMap<Integer, String> arr = new LinkedHashMap<Integer, String>(16, 0.75f, true){
    		protected boolean removeEldestEntry(Map.Entry<Integer, String> e){
    			return size() > max;
    		}
    	};	
    	arr.put(1, "one");
    	arr.put(2, "two");
    	arr.put(3, "three");
    	arr.put(4, "four");
    	arr.put(5, "five");
    	arr.put(6, "six");
    	System.out.println(arr);
    	
    	// adding another element will remove the last accessed element 
    	arr.put(7, "seven");
    	System.out.println(arr);
    	
    	// adding another element will remove the last accessed element
    	arr.put(8, "eight");
    	System.out.println(arr); 	
    }
}
