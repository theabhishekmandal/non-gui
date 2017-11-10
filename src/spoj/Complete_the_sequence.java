package spoj;
/**
 * IN this program we complete the sequence while using difference table
 */

import java.util.*;
public class Complete_the_sequence {
    public static void main(String args[])
    {
        Scanner s=new Scanner(System.in);
        int t=s.nextInt();
        while(t-->0)
        {
            int n=s.nextInt();
            int c=s.nextInt();
            int arr[]=new int[n];
            for(int i=0;i<arr.length;i++)
                arr[i]=s.nextInt();
            int count=0;
            int count2=n;
            int save[][]=new int[n][n];
            for(int i=0;i<n-1;i++)
            {
                boolean check=true;
                count++;
                count2--;
                for(int j=0;j<n-i-1;j++)
                {
                    save[i][j]=arr[j+1]-arr[j];
                    if(arr[j]!=arr[j+1])
                        check=false;
                }
                if(check)
                 break;
            }

            for(int i=0;i<c;i++)
            {
                save[count-1][count2+i]=save[count-1][count2+i-1];
            }


            for(int i=count-1;i>=0;i--)
            {

            }
        }
    }
}
