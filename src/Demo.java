import java.util.*;
import java.io.*;
public class Demo {
    public static void main(String args[]) throws IOException
    {
        Scanner s=new Scanner(System.in);
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(br.readLine());
        while(t-->0)
        {
            String arr1[]=br.readLine().split(" ");
            int n=Integer.parseInt(arr1[0]);
            int m=Integer.parseInt(arr1[0]);
            int q=Integer.parseInt(arr1[0]);
            int p[]=new int[n+1];
            int arr[]=new int[n+1];
            for(int i=0;i<n;i++)
         {
             p[i+1]=Integer.parseInt(br.readLine());
             arr[i+1]=0;
         }
            for(int i=0;i<m;i++)
         {
             String per[]=br.readLine().split(" ");
             if(per[0].equals("ADD"))
             {
                 int x=Integer.parseInt(per[1]);
                 int y=Integer.parseInt(per[2]);
                 arr[x]+=y;
             }
             else if(per[0].equals("ADDUP"))
             {
                 int x=Integer.parseInt(per[1]);
                 int y=Integer.parseInt(per[2]);
                 arr[x]+=y;
                 arr[p[x]]+=y;
                 arr[p[p[x]]]+=y;


             }

         }
         for(int i=0;i<q;i++)
         {
             String per[]=br.readLine().split(" ");

             if(per[0].equals("VAL"))
             {
                 int x=Integer.parseInt(per[1]);
                 System.out.println(arr[x]);
             }
             else if(per[0].equals("VALTREE"))
             {
                 int x=Integer.parseInt(per[1]);
                 int sum=arr[x];
                 for(int j=0;j<p.length;j++)
                 {
                     if(p[j]==x)
                     {
                         sum+=arr[j];
                     }
                 }
                 System.out.println(sum);

             }
         }

        }

    }

    private static int findsum(int[] arr, int j, int[] sum) {
        return 1;
    }
}
