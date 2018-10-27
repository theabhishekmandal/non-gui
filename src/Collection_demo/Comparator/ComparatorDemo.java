package Collection_demo.Comparator;
import java.util.*;
import static java.lang.System.*;
/**
 * Compartor is particularly used for ordering of elements in different way.
 * By default the elements that are stored in natural order of insertion.
 * TreeSet and TreeMap particularly used comparator to store elements in sorted order.
 * Comparator is generic interface that has this declaration:
 * 			interface Comparator<T> 	Here T specifies the type of objects being compared
 * 
 * It has the following methods:
 * 
 * 1 int compare(T obj1, T obj2)		:	returns zero if the two objects are equal,
 * 											returns positive if obj1 is greater than obj2
 * 											returns negative if obj1 is smaller than obj2
 * 
 * 2 boolean equals(object obj)			:	returns true if the invoking object is of the same type of obj
 * 											else it returns false.
 * 
 * 3 default Comparator<T> reversed()	:	returns a comparator that reverses the ordering of the comparator on which
 * 											it is called
 */
public class ComparatorDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> arr = new ArrayList<>();
		for(int i = 0; i < 11; i++) arr.add(i);
		//Comparator<Integer> comparator = Comparator.comparing(Integer::);
		//Collections.sort(arr, comparator.reversed());
	}
}
