package collection_implementation.map;
import java.util.*;
import static java.lang.System.*;
/**
 * EnumMap extends AstractMap and implements Map. It is specifically for use with keys of
 * an enum type. It is a generic class that has this declaration:
 *              class HashMap<K, V>   here K specifies the type of keys and V specifies the type of values
 * 
 * EnumMap defines the following constructors:
 * 
 * EnumMap(Class<K> kType)
 * EnumMap(Map<K, ? extends V> m)
 * EnumMap(EnumMap<K, ? extends V> em)
 * 
 * The first constructor creates an empty EnumMap of type kType.
 * The second creates an EnumMap map that contains the same entries as m.
 * The third creates an EnumMap initialized with the values in em.
 */
public class EnumMapDemo {
	public enum GFG{
        ONE, TWO, THREE, FOUR, FIVE
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EnumMap<GFG, String> arr = new EnumMap<>(GFG.class);

		// adding elements to the map
		arr.put(GFG.ONE, "one");
		arr.put(GFG.TWO, "two");
		arr.put(GFG.THREE, "three");
		arr.put(GFG.FOUR, "four");
		arr.put(GFG.FIVE, "five");
		
		// Displaying the values in the EnumMap
		out.println(arr);
	}
}
