package com.company;
/**
 * In this program ,I have tried to implement the program for replacing a given substring in
 * with another substring in a given input string .
 * For example : if the input string is "abcdef" and i want to replace "cd" with "abhi" then it will
 * show ouput as "ababhief"
 */
import java.util.*;

public class replace_a_substring {
    private static boolean ismatch(String s,String hel,int k)
    {
        for(int i=0;i<hel.length();i++)
        {
            if(s.charAt(i+k)!=hel.charAt(i))
                return false;
        }
        return true;
    }
    public static void main(String args[])
    {
        Scanner s=new Scanner(System.in);
        int t=s.nextInt();
        while(t-->0)
        {
            String in=s.next();
            String one=s.next();
            String two=s.next();
            char arr[]=null;
            for(int i=0;i<in.length();i++)
            {
                if(ismatch(in,one,i))
                {
                    /*
                       this is because the total length would be length of main string "abcdef" minus length of "cd"
                       and addition of length of string "abhi"
                     */
                    arr=new char[in.length()+two.length()-one.length()];
                    int k;
                    for(k=0;k<i;k++)
                        arr[k]=in.charAt(k);
                    for(int j=0;j<two.length();j++)
                        arr[k++]=two.charAt(j);
                    for(int j=i+one.length();j<in.length();j++)
                        arr[k++]=in.charAt(j);
                    break;
                }
            }
            if(arr!=null)
            System.out.println(new String(arr));
            else
                System.err.println("wrong string is being replaced that doesn't exist");
        }

    }
}
