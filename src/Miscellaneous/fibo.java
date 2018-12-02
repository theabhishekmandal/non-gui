package Miscellaneous;
import java.util.*;
import static java.lang.System.*;
/**
 This is an example of dynamic programming in fibonacci series
 */
public class fibo {
    static long[] arr;

    /*
    This fibo method will also work but it will calculate the previously calculated values again and again.
    So for large input the exe time is also more.
     */
    static long fibo(int n){
        if(n == 0) return 0;
        if(n == 1 || n == 2) return 1;
        return fibo(n - 2) + fibo(n - 1);
    }

    /*
     This fibonacci method is a top down approach. In this the previously calculated value is stored and in this
     we also start from the base condition.
     */
    static long topDownFibo(int n){
        if(n == 0) return 0;
        if(n == 1 || n == 2) return 1;
        if(arr[n] != 0) return arr[n];
        return arr[n] = topDownFibo(n - 1) + topDownFibo(n - 2);
    }
    /*
     This fibonacci method is a bottom up approach. In this we start from the base condition and then go
     towards the base condition.
     */
    static long bottomUpFibo(int n){
        arr[1] = arr[2] = 1;
        for(int i = 3; i <= n; i++){
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[n];
    }

    /*
      One improvement that can be done is that we can see that the current value is dependent on
      the previous two calculations only
     */
    static long fiboImproved(int n){
        long a = 1L;
        long b = 1L;
        long c = 0L;
        for(int i = 3 ; i <= n; i++){
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    /*
    Finding the nth fibonacci in O(log n) time period by using the formula
    when n is even
    f(2n) = f(n) * (2 * f(n - 1) + f(n)) and put n = n / 2

    when n is odd
    f(2n - 1) = f(n - 1) * f(n - 1) + f(n) * f(n) and put n = n + 1 / 2

     */
    public static HashMap<Long, Long> save = new HashMap<>();
    public static long fiboImproved2(long n){
        if(n == 0)
            return 0L;
        if(n == 1 || n == 2)
            return 1L;
        if(save.containsKey(n))
            return save.get(n);
        if(n % 2 == 0){
            long k = n / 2;
            save.put( n, fiboImproved2(k) * (2 * fiboImproved2(k - 1) + fiboImproved2(k)));
        }
        else{
            long k = (n + 1) / 2;
            save.put(n, (long)Math.pow(fiboImproved2(k - 1), 2) + (long)Math.pow(fiboImproved2(k), 2));

        }
        return save.get(n);
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        out.println("enter the nth term to find the fibonacci");
        int k = s.nextInt();
        arr = new long[k + 1];
//        out.println(topDownFibo(k));
//        out.println(fibo(k));
//        out.println(bottomUpFibo(k));
//        out.println(fiboImproved(k));
        out.println(fiboImproved2(k));
    }
}
