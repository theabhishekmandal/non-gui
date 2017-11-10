package spoj;
import java.util.*;
public class sum_of_all_continous_subarrays {
    public static void main(String args[])
    {
        Scanner s=new Scanner(System.in);

        //enter the size of the array
        int n=s.nextInt();

        //enter the elements of the array
        int a[]=new int[n];
        for(int i=0;i<a.length;i++)
            a[i]=s.nextInt();


        /*
          now how this is computed
          For eg:
          if arr= 1   2   3   4   5

          now for a given element arr[i]  there are i+1 left points and arr.length-i right points
          if the indexing starts from 0


          so the pattern becomes likes this
        [1]         [2]               [3]                   [4]                [5]             [6]
            [1,2]          [2,3]                [3,4]                  [4,5]             [5,6]
                 [1,2,3]             [2,3,4]               [3,4,5]               [4,5,6]
                         [1,2,3,4]            [2,3,4,5]               [3,4,5,6]
                                [1,2,3,4,5]             [2,3,4,5,6]
                                           [1,2,3,4,5,6]
           if we see then it shows then it shows that
           1 is repeated 6 times
           2 is repeated 10 times
           3 is repeated 12 times
           4 is repeated 12 times
           5 is repeated 10 times
           6 is repeated 12 times

         */
        long sum=0;
        for(int i=0;i<a.length;i++)
        {
            sum+=a[i]*((i+1)*(a.length-i+1));
        }
        System.out.println(sum);
    }
}
