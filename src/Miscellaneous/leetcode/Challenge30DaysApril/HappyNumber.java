package Miscellaneous.leetcode.Challenge30DaysApril;

import java.util.*;

import static java.lang.System.out;

public class HappyNumber {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Random random = new Random();
        int n = s.nextInt();
        for(int i = 10; i < n + 1; i++) {
            out.println(solve(i));
        }
    }

    private static boolean solve(int a) {
        if(a == 0) return false;
       long b = a;
       Set<Long> set = new HashSet<>();
       while(b != 1){
           long sum = 0;
           set.add(b);
           while(b != 0){
               sum += (long)Math.pow(b % 10, 2);
               b /= 10;
           }
           if(set.contains(sum)) return false;
           b = sum;
       }
       return true;
    }
}
