package Generics;

/**
 * This program shows how to implement a generic method in java
 *
 */
import java.util.*;
public class demo5 {
    private static<T> void   display(T ob)
    {
        System.out.println(ob.getClass().getName()+" "+ob);

    }
    public static void main(String args[])
    {
        display(11);
        display("hello world");
        display(233.33);
    }
}
