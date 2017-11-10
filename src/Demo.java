import java.util.*;
import data_structure.sorting.mergesort;
public class Demo {
    public static void main(String args[])
    {
        Scanner s=new Scanner(System.in);
        int t=s.nextInt();
        while(t-->0)
        {
         int n=s.nextInt();
         int x=s.nextInt();
            int arr[]=new int[n];
            int sum=0;
            for(int i=0;i<arr.length;i++)
         {
             arr[i]=s.nextInt();
             sum+=arr[i];
         }
         Arrays.sort(arr);
            System.out.println(((sum%x>=arr[0])? -1:sum/x));

        }

    }
}
