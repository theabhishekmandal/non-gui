package com.company;

import java.util.*;
/**
 This program shows how to reverse the contents of the string such as
 i am happy
 happy am i

 for this we first reverse the list
 yppah ma i
 then we reverse the words.


 */

class reversing
{
    public static void reverse(char arr[],int start,int end)
    {
        while(start<=end)
        {
            char temp=arr[start];
            arr[start]=arr[end];
            arr[end]=temp;
            start++;
            end--;
        }
    }
    public static void main(String args[])
    {
        Scanner s=new Scanner(System.in);
        char arr[]=s.nextLine().toCharArray();
        reverse(arr,0,arr.length-1);
        int j=0;
        int i=0;
        for( i=0;i<arr.length;i++)
        {
            if(arr[i]==' ')
            {
                if(i-j>0)          //to check if there is a string to be reversed
                {
                    reverse(arr,j,i-1);
                    j=i;
                }
                j++;           //for escaping  spaces
            }
        }
        reverse(arr,j,i-1);

        System.out.println(new String(arr));
    }
}