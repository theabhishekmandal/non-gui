package Generics;

/**
 * This program shows how to implement a generic method in java
 * In this method you can pass any number of arguments with any type
 * and can also pass arrays to it.
 *
 */
import java.util.*;
public class demo5 {
    private static <T> void debug(T... ob)
    {
        System.out.println(Arrays.deepToString(ob));

    }
    public static void main(String args[])
    {
        debug(11);
        debug("hello world");
        debug(233.33);
        debug('a','b');
        debug("Abhishek","Mandal");
        debug(new int[]{1,2,3,4,5});

    }
}
