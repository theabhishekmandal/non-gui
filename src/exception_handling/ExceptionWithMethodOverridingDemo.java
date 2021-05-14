package exception_handling;

/**
 * Broadly there are two rules for exception handling with method overriding in Java-
 *
 *     If superclass method has not declared any exception using throws clause then subclass overridden method can't
 *     declare any checked exception though it can declare unchecked exception with the throws clause.
 *
 *     If superclass method has declared an exception using throws clause then subclass overridden method can do one of the three things.
 *        - sub-class can declare the same exception as declared in the super-class method.
 *        - subclass can declare the subtype exception of the exception declared in the superclass method.
 *          But subclass method can not declare any exception that is up in the hierarchy than the exception declared in the super class method.
 *        - subclass method can choose not to declare any exception at all.
 */
class Parent {
    public void display() {
        System.out.println("hello from parent");
    }

    public void display2() throws Exception {
        System.out.println("hello from child");
    }
}

class Child extends Parent {

    // child can override with unchecked exception, but cannot have checked exception, if parent doesn't have it.
    @Override
    public void display() throws RuntimeException {
        System.out.println("hello from child");
    }

    @Override
    public void display2() throws Error // Throwable, cannot throw super class of Exception from child overridden method
    {
        System.out.println("error");
    }
}
public class ExceptionWithMethodOverridingDemo {
    public static void main(String[] args) {
        new Child();
    }
}
