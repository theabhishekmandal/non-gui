package Lambda_Expressions;

/**
 * This is an example of METHOD REFERENCE to INSTANCE METHODS using lambda expressions.
 *
 * The syntax of this is similar to the method reference to static methods.
 *
 * The General syntax is given by:
 *                                 ObjectName::methodName
 */
interface objectfunc{
    String func(String n);
}
public class demo9{
    private String strrev(String input){
        return new StringBuilder(input).reverse().toString();
    }
    public static void main(String[] args) {

        // creating the object and then passing the instance method to the reference of interface.
        demo9 object = new demo9();
        objectfunc ob = object::strrev;

        System.out.println(ob.func("hello world"));
    }
}
