package AbstractInterfaces.AbstractClassImplementingInterface;

/**
 * This program is an example of abstract class implementing an interface
 */

interface myname {
    void helloAbhishek();
}


abstract class New implements myname {
    void instanceMethod() {
        System.out.println("today is 25 of august ");
    }
}


public class Main extends New{

    public void helloAbhishek() {
        System.out.println("HELLO ABHISHEK");
    }

    public static void main(String[] args) {
        Main t=new Main();
        t.helloAbhishek();
        t.instanceMethod();
    }
}
