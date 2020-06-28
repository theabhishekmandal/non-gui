package AbstractInterfaces;

/**
 *
 * When extending a class which implements a interface, if the same interface is implemented on the sub class
 * then there is no need to define the implementation of the interface, because it is already defined in
 * the base class
 */
interface example {
    void method();
}

class exampleclass implements example {
    public void method(){
        System.out.println("hello from super class");
    }
}

public class InheritenceWithInterface extends exampleclass implements example{
    public static void main(String[] args) {
        (new InheritenceWithInterface()).method();
    }
}
