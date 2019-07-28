package Miscellaneous.Recursion;

/**
 * This is an example of storing and retrieving nth fibonacci with logn time complexity
 */

import java.util.*;
import static  java.lang.System.*;

class findingNthFibonacci {
    static long modulo = 1000000000L + 7L;
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        while(t-- > 0){
            long n = s.nextLong() + 1;
            out.println(foo(n));
        }
    }
    static HashMap<Long, Long> save = new HashMap<>();
    private static long foo(long n){
        if(n == 0)
            return 0;
        if(n == 2 || n == 1)
            return 1;
        if(save.containsKey(n))
            return save.get(n);
        if(n % 2 == 0){ //  f(2n) = f(n) * ((2 * f(n - 1) + f(n)))
            long k = n / 2;
            save.put(n, (foo(k) * (2 * foo(k - 1) + foo(k))) % modulo);
        }
        else{ // f(2n - 1) = f(n) * f(n) + f(n - 1) * f(n - 1);
            long k = (n + 1)/ 2;
            save.put(n, ((foo(k) * foo(k)) + (foo(k - 1) * foo(k - 1))) % modulo);
        }
        return save.get(n);
    }

}
