package Lambda_Expressions;

/**
 *  This is an example of METHOD REFERENCE to STATIC METHODS using lambda expressions.
 *
 *  A method reference provides a way to refer to a method without executing it i.e,
 *  if there is an interface with a single abstract method then we can assign a static method
 *  to the lambda expression so as to create the body of the lambda expression
 *
 *  When evaluated, a method reference also creates an instance of the functional interface.
 *
 *  The General Syntax is given by:
 *                                  Classname::methodName
 */

// a functional interface for string operations
interface StringFunc{
    String func(String n);
}
public class demo8 {

    // static method that reverses a string
    public static String strrev(String one){
        return new StringBuilder(one).reverse().toString();
    }

    public static void main(String[] args) {

        // it can be passed any instance of that interface, including a method reference.
        StringFunc sf = demo8::strrev;

        System.out.println(sf.func("hello world"));
    }
}
