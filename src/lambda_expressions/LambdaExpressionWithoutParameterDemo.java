package lambda_expressions;

/**
 * This program is an implementation of lambda expression without a parameter.
 *
 * Below we have created an interface containing an abstract method with the return type
 * of double.
 *
 * So, while implementing the lambda expression , it must be compatible with the return type
 * of the interface's method.
 */
interface MyNumber {
    double getValue();
}

public class LambdaExpressionWithoutParameterDemo {
    public static void main(String[] args) {
        //declare an interface reference;
        MyNumber mynum;

        /*
        Here the lambda expression is simply a constant expression.
        When it is assigned to mynum, a class instance is
        constructed in which the lambda expressin implements
        the getValue() method in mynumber.
         */

        mynum = () -> 123.45;

        /*
            Calling getValue(), which is provided by the previously assigned lambda expression.
         */

        System.out.println("fixed value " + mynum.getValue());

        //Here a more complex lambda expression is used
        mynum = () -> Math.random() * 100;
        System.out.println("random value " + mynum.getValue());
        System.out.println("another random value " + mynum.getValue());

        /*
        A lambda expression must be compatible with the method
        defined by the functional interface .Therefore, this won't work.
         */
        //mynum = () -> "hello world"; //error cause return type is string but needed double
    }
}
