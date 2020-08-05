package dataStructures.sorting;
import java.util.*;
public class mergesort {
    public static void main(String args[])
    {
        Scanner s=new Scanner(System.in);
        System.out.println("enter how many elements and then enter the elements");
        int n=s.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++)
            arr[i]=s.nextInt();
        MergeSort(arr,0,n-1);
        for(int i:arr)
            System.out.println(i);

    }

    public static void MergeSort(int[] arr,int p,int r) {
        if(p<r)
        {
            int q=(int)(p+(r-p)/2);
            MergeSort(arr,p,q);
            MergeSort(arr,q+1,r);
            Merge(arr,p,q,r);
        }
    }

    private static void Merge(int[] arr, int p, int q, int r) {
         int n1=q-p+1;
         int n2=r-q;
         int one[]=new int[n1+1];
         int two[]=new int[n2+1];

         //Adding very large numbers at the end
        one[one.length-1]=Integer.MAX_VALUE;
        two[two.length-1]=Integer.MAX_VALUE;

        for(int i=0;i<n1;i++)
            one[i]=arr[p+i];

        for(int j=0;j<n2;j++)
            two[j]=arr[q+1+j];
        int i=0;
        int j=0;
        for(int k=p;k<=r;k++)
        {
            if(one[i]<two[j]){

                arr[k]=one[i];
                i++;
            }
            else if(two[j]<one[i])
            {
                arr[k]=two[j];
                j++;
            }
        }

    }
}
