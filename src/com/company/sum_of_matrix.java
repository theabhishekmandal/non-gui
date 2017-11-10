package com.company;

/**
 * Created by abhishek mandal on 7/13/2017.
 * this program has
 * INPUT: T the number of test cases
 *        each test case has integer I representing the size of the matrix
 *  the value of the matrix at a[i][j]= absolute difference of the ith row and jth column
 *  OUTPUT: the sum of all the elements of the matrix
 *  for eg:
 *  if 3 is the size of the matrix then the matrix formed is
 *  0 1 2
 *  1 0 1
 *  1 2 0
 *  and the sum is 6
 *
 **/
import java.util.*;
import java.math.*;
public class sum_of_matrix {
    public static void main(String args[])
    {
        Scanner s=new Scanner(System.in);
        int t=s.nextInt();
        while(t-->0)
        {
            int n=s.nextInt();
            BigInteger sum=new BigInteger("0");
            for(int i=1;i<=n;i++)
            {
                long lol=(long)(i*(i-1)/2);
                BigInteger check=new BigInteger(Long.toString(lol));
                sum=sum.add(check);
            }
            System.out.println(sum.multiply(new BigInteger("2")));
        }
    }
}
