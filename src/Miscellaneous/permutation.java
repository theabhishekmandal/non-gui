package Miscellaneous;
import java.io.PrintWriter;
import java.util.*;
public class permutation {
    private static PrintWriter out= new PrintWriter(System.out);
    public static void main(String args[])
    {
        Scanner s=new Scanner(System.in);
        char a[]=s.next().toCharArray();
        permute(2,a);
        out.flush();

    }

    private static void permute(int i, char[] a) {
        if(i==a.length-1)
        {
            out.println(new String(a));
        }
        else
        {
            for(int j=i;j<a.length;j++)
            {
                swap(a,i,j);
                permute(i+1,a);
                swap(a,i,j);
            }
        }
    }

    private static void swap(char[] a, int i, int j) {
    char temp=a[i];
    a[i]=a[j];
    a[j]=temp;
    }
}
