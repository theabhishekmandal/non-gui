package abstract_interfaces;

/**
 * This program is an example of abstract class implementing an interface
 */

interface MyName {
    void helloAbhishek();
}

abstract class New implements MyName {
    void instanceMethod() {
        System.out.println("today is 25 of august ");
    }
}

public class AbstractClassImplementingInterface extends New{

    public void helloAbhishek() {
        System.out.println("HELLO ABHISHEK");
    }

    public static void main(String[] args) {
        AbstractClassImplementingInterface t = new AbstractClassImplementingInterface();
        t.helloAbhishek();
        t.instanceMethod();
    }
}
