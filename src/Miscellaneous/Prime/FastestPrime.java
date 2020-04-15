package Miscellaneous.Prime;

import java.util.*;
import static java.lang.System.*;
import java.util.*;
import static java.lang.System.*;
public class FastestPrime{
    private static long start, stop;
    static void starttime(){start = currentTimeMillis();}
    static void stoptime(){stop = currentTimeMillis();}
    static void gettime(){err.println(((double)(stop - start) / 1000.0) + " seconds");}

    // This is not fast as division operator takes time
    private static List<Integer> fastPrime1(int num){
        boolean[] prime = new boolean[num + 1];
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;
        for(int i = 4; i <= num; i++){
            if(!prime[i]) continue;
            if((i & 1) == 0 || i % 3 == 0){
                prime[i] = false;
            }
            else{
                for(int j = 5; j * j <= i; j += 6){
                    if(i % j == 0 || i % (j + 2) == 0)
                        prime[i] = false;
                }
            }
        }
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i <= num; i++){
            if(prime[i]) list.add(i);
        }
        return list;
    }
    // This is fast because no division is taking place
    private static List<Integer> fastPrime2(int num){
        boolean[] prime = new boolean[num + 1];
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;
        for(int i = 2; i <= num; i++){
            if(!prime[i]) continue;
            for(long j = (long) i * i; j <= (long)num; j += i){
                prime[(int)j] = false;
            }
        }
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i <= num; i++){
            if(prime[i]) list.add(i);
        }
        return list;
    }
    public static void main(String[] args) {
        starttime();
        List<Integer> list1 = fastPrime1(10000000);
        stoptime();
        out.println(list1);
        gettime();
        starttime();
        List<Integer> list2 = fastPrime2(10000000);
        stoptime();
        out.println(list2);
        gettime();
    }
}
