package DataStructures.sorting;
import java.util.*;
/*
this class is example of insertion sorting
here we start with the second index of the array
we can understand it with the help of the following example
let array a[]={9,8,7,6,5}
outer:1
     key=8
     after the while loop:  9,9,7,6,5
     8,9,7,6,5
outer:2
      key=7
      after the while loop:8,8,9,6,5
      7,8,9,6,5
 outer:3
       key=6
       after the while loop:7,7,8,9,5
       6,7,8,9,5
 outer:4
       key=5
       after the while loop:6,6,7,8,9
       5,6,7,8,9


    at last the array is sorted
 */
class insertsort
{
    public int[] sort(int a[])
    {
        for(int i=1;i<a.length;i++)
        {
            int j=i-1;
            int key=a[i];
            while(j>=0&&a[j]>key)
            {
                a[j+1]=a[j];
                j--;
            }
            a[j+1]=key;
        }
        return a;
    }
}
public class insertionsort
{
    public static void main(String args[])
    {
        Scanner s=new Scanner(System.in);
        System.out.println("enter how many elements");
        int  n=s.nextInt();
        int a[]=new int[n];
        System.out.println("enter the elements");
        for(int i=0;i<a.length;i++)
            a[i]=s.nextInt();
        a=new insertsort().sort(a);
        for(int i:a)
            System.out.println(i);

    }
}
