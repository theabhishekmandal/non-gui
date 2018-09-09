package Collection_demo;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static java.lang.System.out;

/**
 *  Implementing Iterator interface
 */
public class IteratorDemo {
    public static void main(String[] args) {
        Set<Object> arr = new HashSet<>();
        arr.add(1);
        arr.add("blah");
        arr.add(2.3);
        arr.add(1e9);
        out.println(arr);

        // it.remove() will throw illegalStateException if remove() is called without calling next()
        Iterator it = arr.iterator();
        while(it.hasNext()){
            out.println(it.next());
            it.remove();
        }
    }
}
