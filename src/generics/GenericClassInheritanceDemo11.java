package generics;

import java.io.PrintWriter;

/**
 * This is an example of generic class inheritence
 * In this a generic can be a superclass of both generic class and non-generic class
 */

//generic superclass
class Abhishek<T> {
    T ob;
    Abhishek(T o) {
        this.ob = o;
    }
    T getOb() {
        return ob;
    }
}

// a non - generic subclass
/*
Even though a subclass which extends a super generic class then also it must specify the
type parameters that are same as of the super class

Even though the subclass doesn't require the type parameters then also it must specify for the sake of the
generic superclass
 */
class Mandal<T> extends Abhishek<T> {
    int obj;
    Mandal(T o, int k) {
        super(o);
        obj = k;
    }
    int getCurrentObj() {
        return obj;
    }
}

//a generic subclass which can be different from the type of the generic superclass
class Mandal2<T, V> extends Abhishek<T> {
    V b;
    Mandal2(T a, V b) {
        super(a);
        this.b = b;
    }
    V getB() {
        return b;
    }
}
public class GenericClassInheritanceDemo11 {
    public static void main (String[] args) {
        Mandal<String> arr = new Mandal<>("hello mandal", 11);
        Mandal2<String, Integer> arr2 = new Mandal2<>("hello mandal",12);
        PrintWriter out = new PrintWriter(System.out,true);
        out.println(arr.getCurrentObj());
        out.println(arr.getOb());
        out.println(arr2.getOb());
        out.println(arr2.getB());
    }
}
