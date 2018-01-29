package Lambda_Expressions;
import static java.lang.System.*;

/**
 * Although the lambda expressions cannot be generic but the interfaces which use them can
 * be of generic type.
 *
 * Thus in this we use a generic interface and implement a lambda expression.
 *
 * Also we can pass a lambda expression as an argument to the function.
 * @param <T>
 */
interface  func<T>
{
    T func(T t);
}
public class demo5 {
     private static void passinglambdaExpressionAsArguments(func<Integer> iseven, int n) {

        out.println((iseven.func(n) == 1)? "No":"Yes");

    }
    public static void main(String args[]) {
        /*
            using integer based version of the func to
            return the factorial of a given number
         */
     func<Integer> fact = (n) ->{
         int sum = 1;
         for(int i = n; i >= 1; i--)
             sum *= i;
         return sum;
     };
     out.println(fact.func(5));

     /*
     using the string based version of the func
     to return the reverse of the string
      */
     func<String> reverse = (n) ->{
         StringBuilder br = new StringBuilder();
       for(int i = n.length() - 1; i >= 0; --i)
       {
           br.append(n.charAt(i));
       }
       return br.toString();
     };

     out.println(reverse.func("hello"));

     /*
     Now passing an lambda expression as an argument
     which checks if a number is even or odd
      */

     passinglambdaExpressionAsArguments((n) -> n % 2, 10);

    }
}
