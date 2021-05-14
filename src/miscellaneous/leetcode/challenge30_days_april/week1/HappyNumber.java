package miscellaneous.leetcode.challenge30_days_april.week1;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import static java.lang.System.out;

/**
 * Week 1 day 2
 * Write an algorithm to determine if a number n is "happy".
 *
 * A happy number is a number defined by the following process: Starting with any positive integer,
 * replace the number by the sum of the squares of its digits,
 * and repeat the process until the number equals 1 (where it will stay),
 * or it loops endlessly in a cycle which does not include 1.
 * Those numbers for which this process ends in 1 are happy numbers.
 *
 * Return True if n is a happy number, and False if not.
 */

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
