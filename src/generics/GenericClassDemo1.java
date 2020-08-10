package generics;

/**
 * Generics means parameterized types. Parameterized types are important because they enable you to create classes,
 * interfaces and methods in which the type of data upon which the operate is specified as parameter.
 *
 * Using Generics, it is possible to create a single class that automatically works with different types of data.
 *
 * A class, interface, or method that operates on a parameterized types is called generic, as in generic class or generic
 * method.
 *
 * Before Generics, generalized classes, interfaces & methods used object references to operate on various types of
 * objects.
 *
 * But objects lacked type safety which was ensured by Generics.
 *
 * By using Generics we didn't have to cast the objects to its primitive types. It was automatically done.
 *
 * You cannot use primitive types with generic types. Use only reference type such as Integer, Double, Character
 */

// here t represents type parameter
class Hello<T> {
    T ob;
    Hello(T ob) {
        this.ob = ob;
    }
    T get() {
        return this.ob;
    }
}
public class GenericClassDemo1 {
    public static void main(String[] args) {
        Hello<Integer> ob = new Hello<>(10);
        Hello<String> ob2 = new Hello<>("hello i am abhishek");
        int ten = ob.get();
        String value = ob2.get();
        System.out.println(ten);
        System.out.println(value);
    }
}
