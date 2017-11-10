

/**
 * A positive integer is called a palindrome if its representation in the decimal system is the same when read from left to right and from right to left.
 * For a given positive integer K of not more than 1000000 digits, write the value of the smallest palindrome larger than K to output.
 * Numbers are always displayed without leading zeros.
 Input

 The first line contains integer t, the number of test cases. Integers K are given in the next t lines.
 Output

 For each K, output the smallest palindrome larger than K.
 Example

 Input:
 2
 808
 2133

 Output:
 818
 2222

**/
import java.util.*;
import java.io.*;
public class test
{

    private static boolean isnine(String hel)
    {
        for(int i=0;i<hel.length();i++)
        {
            if(hel.charAt(i)!='9')
                return false;

        }
        return true;
    }
    private static String addandprint(String hel,int add)
    {
        int stop=(hel.length()%2==0)? -1:0;
        int j=hel.length()/2;
        char arr[]=hel.toCharArray();
        for(int i=j+stop;i>=0;i--)
        {
            int temp=arr[i]-'0'+add;
            arr[i]=(char)(temp%10+48);
            arr[hel.length()-1-i]=arr[i];
            add=temp/10;

        }
        return new String(arr);
    }
    private static boolean rightislarge(String hel)
    {
        int stop=(hel.length()%2==0)? -1:0;
        int j=hel.length()/2;
        char arr[]=hel.toCharArray();
        for(int i=j+stop;i>=0;i--)
        {
           if((int)arr[i]<(int)arr[hel.length()-1-i])
               return  true;
        }
        return false;
    }

    private static void solve(String hel)
    {
         if(isnine(hel))
            {
                StringBuilder nine=new StringBuilder("1");
                for(int i=0;i<hel.length()-1;i++)
                {
                    nine.append("0");

                }
                nine.append("1");
                System.out.println(nine);
                return;
            }
        else if(hel.equals(new StringBuilder(hel).reverse().toString()))
        {
            System.out.println(addandprint(hel,1));
        }
        else  {
             if(rightislarge(hel))
             System.out.println(addandprint(hel, 1));
             else
                 System.out.println(addandprint(hel, 0));

         }
    }
    public static void main(String args[]) throws IOException
    {
        Scanner s=new Scanner(System.in);
         int t=s.nextInt();
         while(t-->0)
         {
         	solve(s.next());
         }

    }
}



