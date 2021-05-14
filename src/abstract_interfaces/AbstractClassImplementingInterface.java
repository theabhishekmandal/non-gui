package abstract_interfaces;

/**
 * This program is an example of abstract class implementing an interface.
 * Here note that when abstract class implements an interface then it is not necessary to implement the methods. It should
 * be only done by the concrete class.
 */

interface MyName {
    void helloAbhishek();
}

abstract class New implements MyName {
    private int instance = 0;
    void instanceMethod() {
        System.out.println("today is 25 of august ");
    }
}

public class AbstractClassImplementingInterface extends New {

    public static void main(String[] args) {
        var t = new AbstractClassImplementingInterface();
        t.helloAbhishek();
        t.instanceMethod();
    }

    public void helloAbhishek() {
        System.out.println("HELLO ABHISHEK");
    }
}
