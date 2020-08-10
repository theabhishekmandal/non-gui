package lambda_expressions;

/**
 * This is an example of METHOD REFERENCE to INSTANCE METHODS using lambda expressions.
 *
 * This type of method reference is particularly dependent on each object i.e here
 * we are creating method reference for each object separately. In the next example
 * we will specify an instance method that can be used with any object of a given class and
 * not just a specified object.
 *
 * The syntax of this is similar to the method reference to static methods.
 *
 * The General syntax is given by:
 *                                 ObjectName::methodName
 * Notice here we are creating object to pass the instance method to the reference of interface.
 */
interface ObjectFunc {
    String func(String n);
}
public class LambdaExpressionWithInstanceMethodReferenceDemo {
    private String strRev(String input){
        return new StringBuilder(input).reverse().toString();
    }
    public static void main(String[] args) {

        // creating the object and then passing the instance method to the reference of interface.
        LambdaExpressionWithInstanceMethodReferenceDemo object = new LambdaExpressionWithInstanceMethodReferenceDemo();
        ObjectFunc ob = object::strRev;

        System.out.println(ob.func("hello world"));
    }
}
