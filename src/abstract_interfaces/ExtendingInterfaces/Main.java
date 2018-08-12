package abstract_interfaces.ExtendingInterfaces;

/**
 * This program is an implementation of inheritance among interfaces.
 */

// a interface having two methods definitions
interface one{
    void meth1();
    void meth2();
}


//interface extending the previous interface and now have three method definitions
interface two extends one{
    void meth3();
}


//this class has to implement all the methods of the interface two and one
class Myclass implements two{
    public void meth1() {
        System.out.println("Implementing meth1");

    }
    public void meth2() {
     System.out.println("Implementing meth2");
    }

    public void meth3() {
        System.out.println("Implementing meth3");
    }
}


public class Main {
    public static void main(String args[]) {
        Myclass ob=new Myclass();
        ob.meth1();
        ob.meth2();
        ob.meth3();
    }
}
