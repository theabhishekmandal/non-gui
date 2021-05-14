package abstract_interfaces;

/**
 * This is an example of how to avoid diamond deadlock problem, as First and Second both have same name method
 * and both are being implemented by Concrete class,
 */

interface First {
    default void hello() {
        System.out.println("hello from one");
    }
}

interface Second {
    default void hello() {
        System.out.println("hello from two");
    }
}

class Concrete implements First, Second {

    @Override
    public void hello() {
        First.super.hello();
        Second.super.hello();
    }
}

public class DefaultMethodDeadLockDemo {
    public static void main(String[] args) {
        var obj = new Concrete();
        obj.hello();
    }
}
