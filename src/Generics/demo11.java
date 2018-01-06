package Generics;

import java.io.PrintWriter;

/**
 * This is an example of generic class inheritence
 * In this a generic can be a superclass of both generic class and non-generic class
 */

//generic superclass
class abhishek<T>
{
    T ob;
    abhishek(T o)
    {
        this.ob = o;
    }
    T getob()
    {
        return ob;
    }
}

// a non - generic subclass
/*
Even though a subclass which extends a super generic class then also it must specify the
type parameters that are same as of the super class

Even though the subclass dosen't require the type parameters then also it must specify for the sake of the
generic superclass
 */
class mandal<T> extends abhishek<T>
{
    int ob;
    mandal(T o,int k)
    {
        super(o);
        ob = k;
    }
    int getint()
    {
        return ob;
    }
    T getob()
    {
        return super.getob();
    }
}

//a generic subclass which can be different from the type of the generic superclass
class mandal2<T, V> extends abhishek<T>
{
    V b;
    mandal2(T a, V b)
    {
        super(a);
        this.b = b;
    }
    T getob()
    {
        return super.getob();
    }
    V getb()
    {
        return b;
    }
}
public class demo11 {
    public static void main (String args[])
    {
        mandal<String> arr = new mandal<String>("hello mandal", 11);
        mandal2<String, Integer> arr2 = new mandal2<>("hello mandal",12);
        PrintWriter out = new PrintWriter(System.out,true);
        out.println(arr.getint());
        out.println(arr.getob());
        out.println(arr2.getob());
        out.println(arr2.getb());
    }
}
