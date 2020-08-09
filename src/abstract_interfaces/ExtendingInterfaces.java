package abstract_interfaces;

/**
 * This program is an implementation of inheritance among interfaces.
 */

// a interface having two methods definitions
interface One {
    void meth1();
    void meth2();
}


//interface extending the previous interface and now have three method definitions
interface Two extends One {
    void meth3();
}


//this class has to implement all the methods of the interface two and one
class MyClass implements Two {

    @Override
    public void meth1() {
        System.out.println("Implementing meth1");
    }

    @Override
    public void meth2() {
     System.out.println("Implementing meth2");
    }

    @Override
    public void meth3() {
        System.out.println("Implementing meth3");
    }
}


public class ExtendingInterfaces {
    public static void main(String[] args) {
        MyClass ob = new MyClass();
        ob.meth1();
        ob.meth2();
        ob.meth3();
    }
}
