package CollectionImplementation.Comparator;
import java.util.*;
import static java.lang.System.*;

/**
 * Comparator is particularly used for ordering of elements in different way.
 * Whereas comparable is used for natural order sorting.
 * By default the elements are stored in natural order of insertion.
 * TreeSet and TreeMap particularly used comparator to store elements in sorted order.
 * Comparator is generic interface that has this declaration:
 *          interface Comparator<T>     Here T specifies the type of objects being compared.
 * It has the following methods:
 *
 * 1 int compare(T obj1, T obj2)            :   returns zero if the two objects are equal,
 *                                              returns positive if obj1 is greater than obj2
 *                                              returns negative if obj1 is smaller than obj2
 *
 * 2 boolean equals(object obj)             :   returns true if the invoking object is of the same
 *                                              type of obj else it returns false.
 *
 * 3 default Comparator<T> reversed()       :   returns a comparator that reverses the ordering of
 *                                              the comparator on which it is called.
 *
 * 4 static <T extends Comparable
 *   <? super T>> Comparator<T>
 *   reverseOrder()                         :   It returns a comparator that reverses the natural
 *                                              ordering of the elements
 *
 * 5 static<T, U extends Comparable
 *   <? super U>> Comparator<T>
 *   comparing(Function<? super T,
 *   ? extends U> getkey)                   :   it returns a comparator of the given type
 *
 * 6 static <T,U> Comparator<T>
 *   comparing(Function<? super T,
 *   ? extends U> keyExtractor,
 *   Comparator<? super U>
 *   keyComparator)                         :   it returns a comparator of the given type, but allows
 *                                              overriding the natural order of the sort key by providing
 *                                              the comparator that creates custom ordering for the sort key
 */
public class ComparatorDemo1 {
    public static void main(String[] args){
        // Creating a TreeSet in which elements will be inserted in descending order
        // passing a comparator object and thereby implementing the compare method
        // for customized sorting order.
        out.println("TreeSet with Descending comparator");
        TreeSet<Integer> arr = new TreeSet<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer one, Integer two){
                return -one.compareTo(two);
            }
        });
        for(int i = 1; i < 11; i++) arr.add(i);
        out.println(arr);
        out.println();

        out.println("implementing the reversed method");
        // first creating a comparator for natural sorting order
        // then using reversed method to return a reverse comparator and sorting in descending order
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };
        ArrayList<String> hel = new ArrayList<>();
        for(char ch = 'a'; ch <= 'z'; ch++) hel.add(ch + "");
        Collections.sort(hel, comparator.reversed());
        out.println(hel);
        out.println();


        out.println("implementing the reverseOrder method");
        ArrayList<City> helobj = new ArrayList<>();
        helobj.add(new City("Delhi"));
        helobj.add(new City("Mumbai"));
        helobj.add(new City("Kolkata"));
        helobj.add(new City("Chennai"));

        // implementing the comparing method of comparator
        /*
         This Comparator type can be replaced by Comparator.comparing
         Comparator<City>  cityComparator = (City one, City two) -> {
            return one.getName().compareTo(two.getName());
         };
        */
        //first getting the comparator using Comparator.comparing and then reversing it with reverseOrder method
        Comparator<City> cityComparator = Collections.reverseOrder(Comparator.comparing(City::getName));
        Collections.sort(helobj, cityComparator);
        out.println(helobj);
        out.println();

        out.println("implementing the comparing method and also by providing the keyComp");
        // the above comparison can also be performed by using the below way
        Comparator<City> cityComparator1 = Comparator.comparing(City::getName, Comparator.reverseOrder());
        helobj.sort(cityComparator1);
        out.println(helobj);
        out.println();
    }
}

class City{
    private String name;
    City(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    @Override
    public String toString(){
        return "[" + this.name + "]";
    }
}
