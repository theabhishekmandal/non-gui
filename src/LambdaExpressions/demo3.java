package LambdaExpressions;


/**
 * This program is an example of parameterized lambda expression.
 */
interface numerictest
{
    boolean test(int n);
}

public class demo3 {
    public static void main(String args[])
    {

        // a Lambda expression that tests that if a given number is even or not
        // also no need to declare the type of the parameter passed as it is implicitly declared.
        numerictest iseven = (n) -> n % 2 == 0;

        if(iseven.test(10)) System.out.println("10 is even");
        if(!iseven.test(9)) System.out.println("9 is not even");

        //converting the same lambda expression that tests that if
        // a given number is negative or not.
        numerictest isnonneg = (n) -> n >= 0;

        if(isnonneg.test(10)) System.out.println("10 is non negative");
        if(!isnonneg.test(-11))System.out.println("-11 is negative");
    }
}
