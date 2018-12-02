package DataStructures.sorting;

import java.util.Scanner;
/*
This program is an example of bubble sorting example let's check for values on how this algorithm works
let array a contains following elements

Bubble sorting is based on swapping the adjacent values if they differ in their value
   a[]={9,8,7,6,5}
   outer:0
         inner 0:
                  8,9,7,6,5
         inner 1:
                  8,7,9,6,5
         inner 2:
                  8,7,6,9,5
         inner 3:
                  8,7,6,5,9
    outer:1
          inner 0:
                  7,8,6,5,9
         inner 1:
                  7,6,8,5,9
         inner 2:
                  7,6,5,8,9
    outer:2
          inner 0:
                  6,7,5,8,9
          inner 1:
                  6,5,7,8,9
    outer:3
            inner 2:
                   5,6,7,8,9


    so we can see that the outer loop runs for about n-1 times where n is the size of the array
    and the inner loop runs n-i-1 times where i the iteration number of the outer loop


 */

class bubble
{
public int[] sort(int a[])
{
    for(int i=0;i<a.length-1;i++)
    {
        for(int j=0;j<a.length-i-1;j++)
        {
            if(a[j]>a[j+1])
            {
                int temp=a[j];
                a[j]=a[j+1];
                a[j+1]=temp;
            }
        }
    }
    return a;
}
}
 class Bubblesort {
    public static void main(String args[])
    {
        Scanner s=new Scanner(System.in);
        System.out.println("enter how many elements");
        int size=s.nextInt();
        int a[]=new int[size];
        System.out.println("enter the elements ");
        for(int i=0;i<size;i++)
            a[i]=s.nextInt();
        bubble sortobject=new bubble();
        a=sortobject.sort(a);
        System.out.println("values after DataStructures.sorting");
        for(int i=0;i<a.length;i++)
            System.out.println(a[i]);

    }
}
