package com.company;

/**
 * Created by abhishek mandal on 7/14/2017.
 */
import java.util.*;
public class rope_fire {
    public static void main(String args[])
    {
        Scanner s=new Scanner(System.in);
        int t=s.nextInt();
        while(t-->0)
        {
            int n=s.nextInt();
            int up[]=new int[n-1];
            int lo[]=new int[n-1];
            int sum=0;
            for(int i=0;i<up.length;i++)
            {
                int k=s.nextInt();
                if(k!=0)
                {
                    up[i] = 1;
                    sum += k - 1;
                }
            }
            for(int i=0;i<up.length;i++)
            {
                int k=s.nextInt();
                if(k>0)
                {
                    sum += k - 1;
                    lo[i] = 1;
                }
            }
            System.out.println(n+(sum+3-1)/3);


        }
    }
}
