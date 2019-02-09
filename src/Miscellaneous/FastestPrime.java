package Miscellaneous;

import java.util.*;
import static java.lang.System.*;
public class FastestPrime {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        StringBuilder br = new StringBuilder();
        int num = 1000000;
        out.println(num);
        for(int i = 2; i <= num; i++){
            if(isprime(i)){
                br.append(i).append("\n");
            }
        }
        out.println(br);
    }

    private static boolean isprime(int i) {
        if(i < 2) return false;
        if(i <= 3) return true;
        if(i % 2 == 0 ||  i % 3 == 0) return false;
        else{
            for(int k = 5; k * k <= i; k += 6){
                if(i % k == 0 || i % (k + 2) == 0) return false;
            }
        }
        return true;
    }
}
