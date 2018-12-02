package Miscellaneous;

import java.util.*;

/** This program finds the next 4 consequtive integers  who's prime numbers are distinct.

 */
public class Prime1
{

    public static void main(String[] args)
    {
        System.out.println(sol());
    }


    public static  String sol()
    {
        for (int i = 2; ; i++)
        {
            if (PrimeFactors(i + 0)&& PrimeFactors(i + 1)&& PrimeFactors(i + 2)&& PrimeFactors(i + 3))
                return Integer.toString(i);
        }

    }


    private static boolean PrimeFactors(int n)
    {
        return DistinctCount(n) == 4;
    }


    private static int DistinctCount(int n) {
        int count = 0;
        for (int i = 2, end = (int)Math.pow(n,0.5); i <= end; i++)
        {
            if (n % i == 0)
            {
                do n /= i;
                while (n % i == 0);
                count++;
                end = (int)Math.pow(n,0.5);
            }
        }
        if (n > 1)
            count++;
        return count;
    }

}