package lambda_expressions;

/**
 * This program implements block lambda expression which has a body
 */

interface MyTest {
    boolean isTest(int n);
}

public class LambdaExpressionWithBodyDemo {
    public static void main(String args[]) {

       MyTest isprime = n -> {
        if (n < 2) return false;
        if(n <= 3) return true;
        if( n % 2 == 0 || n % 3 == 0) return false;
        else {
            for(int i = 5 ; i * i <= n ; i++) {
                if((n % i) == 0 || n % (i + 2) == 0)
                    return false;
            }
        }
        return true;
       };

       if(!isprime.isTest(10)) {
           System.out.println("10 is not prime");
       }
       if(isprime.isTest(7)) {
           System.out.println("7 is prime");
       }
    }
}
