package abstract_interfaces;

/**
 *
 * When extending a class which implements a interface, if the same interface is implemented on the sub class
 * then there is no need to define the implementation of the interface, because it is already defined in
 * the base class
 */
interface Example {
    void method();
}

class ExampleClass implements Example {
    public void method(){
        System.out.println("hello from super class");
    }
}

public class InheritanceWithInterface extends ExampleClass implements Example {
    public static void main(String[] args) {
        (new InheritanceWithInterface()).method();
    }
}
