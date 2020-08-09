package collection_implementation.Iterator;

import java.util.HashSet;
import java.util.Set;

import static java.lang.System.out;

// for each alternative of iterators and listIterators
public class ForEachAlternative {
    public static void main(String[] args) {
        Set<Object> arr = new HashSet<>();
        arr.add(1);
        arr.add("blah");
        arr.add(2.3);
        arr.add(1e9);

        for(Object x : arr){
            out.print(x + " ");
        }
    }
}
