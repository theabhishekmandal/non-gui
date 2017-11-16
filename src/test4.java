import java.util.*;
public class test4 {
    public static void main(String args[])
    {
        Scanner s=new Scanner(System.in);
        int n=s.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<arr.length;i++)
            arr[i]=s.nextInt();
        System.out.println(possibleways(arr));
    }
    private static int possibleways(int input1[])
    {
        int a[]=new int[input1.length];
        a[0]=input1[0];
        for(int i=1;i<input1.length;i++)
            a[i]=input1[i]+a[i-1];
        long answer=0;
        long longvalue=1000000007;
        for(int i=1;i<=a.length;i++)
        {
            for(int j=0;j<a.length-i+1;j++)
            {
                int save=0;
                if(j==0)
                    save+=a[i+j-1];
                else
                    save+=a[i+j-1]-a[j-1];
                answer+=((save)*(input1[i+j-1]))%longvalue;
            }
        }
        return (int)(answer%longvalue);
    }
}
