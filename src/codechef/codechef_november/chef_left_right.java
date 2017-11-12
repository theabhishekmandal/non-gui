package codechef.codechef_november;

import java.util.*;
public class chef_left_right {
    public static void main(String args[])
    {
        Scanner s=new Scanner(System.in);
        int t=s.nextInt();
        while(t-->0)
        {
            int n=s.nextInt();
            int r=s.nextInt();
            int arr[]=new int[n];
            for(int i=0;i<n;i++)
                arr[i]=s.nextInt();
            String ans="YES";
            for(int i=0;i<arr.length-1;i++)
            {
                if(arr[i]>r&&isgreater(arr,i+1,arr[i]))
                {
                    ans="NO";
                    break;

                }
               else if(arr[i]<r&&issmaller(arr,i+1,arr[i]))
                {
                    ans="NO";
                    break;
                }
            }

          System.out.println(ans);
        }
    }

    private static boolean issmaller(int[] arr, int i, int i1) {
        for(int j=i;j<arr.length-1;j++)
        {
            if(arr[j]<i1)
                return true;

        }
        return false;

    }

    private static boolean isgreater(int arr[],int i,int a) {
        for(int j=i;j<arr.length-1;j++)
        {
            if(arr[j]>a)
                return true;

        }
        return false;
    }

}
