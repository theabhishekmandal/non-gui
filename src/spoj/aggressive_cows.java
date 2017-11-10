package spoj;
import java.util.*;

public class aggressive_cows {
    public static void main(String args[])
    {
        Scanner s=new Scanner(System.in);
        int t=s.nextInt();
        while(t-->0)
        {
            int n=s.nextInt();
            int c=s.nextInt();//denoting the number of cows
            int a[]=new int[n];
            for (int i=0;i<n;i++)
                a[i]=s.nextInt();
            Arrays.sort(a);
            System.out.println(search_and_find_maximum_minimum_distance(a,c));

        }
    }

    private static int search_and_find_maximum_minimum_distance(int[] a, int c) {
        int low=0;
        int high=a[a.length-1];
        int max=-1;
        while(high>low)
        {
            int mid=low+(high-low)/2; //to avoid overflow instead of using low+high/2
            if(call(a,c,mid))
            {
                if(mid>max)
                {
                    max=mid;
                }
                low=mid+1;
            }
            else
                high=mid;

        }
        return max;
    }

    private static boolean call(int[] a, int c, int mid) {
        int cows=1;
        int pos=a[0];
        for(int i=1;i<a.length;i++)
        {
            if(a[i]-pos>=mid)
            {
                pos=a[i];
                cows++;
                if(cows==c)
                    return true;
            }
        }
        return false;
    }
}
