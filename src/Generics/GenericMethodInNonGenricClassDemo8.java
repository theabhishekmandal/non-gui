package Generics;
import java.util.*;

/**
 * This program shows how to use a generic method in  a non generic class
 * in this we are using the debug method with a parameter
 * the debug method can take any number of arguments
 * and the type of the arguments can be anything
 */
public class GenericMethodInNonGenricClassDemo8 {

    @SafeVarargs
    static <T> void debug(T... a)
    {
        System.err.println(Arrays.deepToString(a));
    }


    public static void main(String args[]) {
        debug(1,2,3,4,5,6,7,8);
        debug('a','b');
        int arr[] = {10, 11, 12, 13};
        debug(arr);

    }
}
