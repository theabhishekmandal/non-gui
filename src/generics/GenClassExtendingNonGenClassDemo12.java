package generics;

/**
 * This program is an example of that a generic class can be a subclass of non-generic class
 */
class NonGen {
    int i;
    NonGen(int i) {
        this.i = i;
    }
    int getVal() {
        return i;
    }
}

class Generic<T> extends NonGen {
    T ob;
    Generic(T ob, int i) {
        super(i);
        this.ob = ob;
    }
    T getOb() {
        return ob;
    }
}
public class GenClassExtendingNonGenClassDemo12 {
    public static void main(String[] args) {
        Generic<String> arr = new Generic<>("hello abhishek",12);
        System.out.println(arr.getOb());
        System.out.println(arr.getVal());
    }
}
