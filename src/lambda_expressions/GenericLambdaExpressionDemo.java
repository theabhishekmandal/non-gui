package lambda_expressions;

import static java.lang.System.out;

/**
 * Although the lambda expressions cannot be generic but the interfaces which use them can
 * be of generic type.
 *
 * Thus in this we use a generic interface and implement a lambda expression.
 *
 * Also we can pass a lambda expression as an argument to the function.
 * @param <T>
 */
interface Func<T> {
    T myFunc(T t);
}

public class GenericLambdaExpressionDemo {

     private static void passingLambdaExpressionAsArguments(Func<Integer> iseven, int n) {
        out.println((iseven.myFunc(n) == 1)? "No" : "Yes");
     }

    public static void main(String[] args) {
         /*
            using integer based version of the func to
            return the factorial of a given number
         */
        Func<Integer> fact = n -> {
            int sum = 1;
            for(int i = n; i >= 1; i--) {
                sum *= i;
            }
            return sum;
        };
        out.println(fact.myFunc(5));

        /*
            using the string based version of the func
            to return the reverse of the string
        */
        Func<String> reverse = n -> {
            StringBuilder br = new StringBuilder();
            for(int i = n.length() - 1; i >= 0; --i) {
                br.append(n.charAt(i));
            }
            return br.toString();
        };
        out.println(reverse.myFunc("hello"));

        /*
            Now passing an lambda expression as an argument
            which checks if a number is even or odd
        */
        passingLambdaExpressionAsArguments(n -> n % 2, 10);
    }
}
