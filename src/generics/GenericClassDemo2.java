package generics;

/**
 *     This program is an example of two generic demo
 *     here we can use multiple parameter types in a generic type class
 */
class TwoGen<T,V> {
   private final T ob;
   private final V ob1;

    TwoGen(T ob,V ob2) {
        this.ob = ob;
        this.ob1 = ob2;
    }

    public T getOb() {
        return ob;
    }

    public V getOb1() {
        return ob1;
    }
}
public class GenericClassDemo2 {
    public static void main(String[] args) {
        TwoGen<Integer,String> arr = new TwoGen<>(10,"hello  i am abhishek");
        int num = arr.getOb();
        String value = arr.getOb1();
        System.out.println(num + "\n" + value);
    }
}
