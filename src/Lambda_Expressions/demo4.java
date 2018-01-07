package Lambda_Expressions;

/**
 * This program implements block lambda expression which has a body
 */

interface mytest
{
    boolean istest(int n);
}

public class demo4 {
    public static void main(String args[])
    {

       mytest isprime = (n) ->{
        if (n < 2) return false;
        if(n <= 3) return true;
        if( n % 2 == 0 || n % 3 == 0) return false;
        else
        {
            for(int i = 5 ; i * i <= n ; i++)
            {
                if((n % i) == 0 || n % (i + 2) == 0)
                    return false;
            }
        }
        return true;
       };

       if(!isprime.istest(10))System.out.println("10 is not prime");
       if(isprime.istest(7))System.out.println("7 is prime");
    }
}
