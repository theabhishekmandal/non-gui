import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Buying Sweets

 Sachin likes sweets a lot. So, he goes to a market of sweets. There is a row of sweet stalls. Every sweet stall has different sweets. To save some time, he decided to buy sweets from contiguous stalls. So, he can buy from as many stalls as he wants, but all of those stalls need to be contiguous. He also decided to buy only 1 kg of sweets from each of those stalls. Cost of 1 kg of sweets for each stall is given. There is a strange rule of billing in that market. And that rule is as follows- Total cost of all sweets bought is sum of the cost of all sweets multiplied by the cost of sweet he bought at the end. e.g. if he buys sweets having cost 2, 3, 4 in the same order than total cost of sweets will be 24+34+4*4=36. Now he wonders what will be the total cost of all possible ways of buying sweets. Can you help him. Because this number could be large, you should take modulo of the final result by 10^9+7.

 INPUT SPECIFICATION

 Your function contains a single argument- A One dimensional Integer array of Size N in which ith element denotes the cost of 1 kg sweets from ith stall. First line of input contains an Integer N denoting the size of Array. (1<=N<=10^5) Next N lines of input each containing a single integer from 1 to 9.

 OUTPUT SPECIFICATION

 You must return an integer- sum of the cost of all possible ways of buying sweets modulo 10^9+7.

 EXAMPLES

 Sample Test Case 1-Input

 3 1 2 3

 Output

 53

 Explanation

 Possible ways of buying sweets are- a) 1 b) 1 2 c) 2 d) 1 2 3 e) 2 3 f) 3 cost of each of these is following- a) 11= 1 b) 12+22= 6 c) 22= 4 d) 13+23+33= 18 e) 23+33= 15 f) 33= 9

 Hence total cost will be 1+6+4+18+15+9=53
 */

/**
 * For the solution i have approached as follows for eg:
 * we have given array a=[4,3,2]
 * so if we see the permutation we would get as such:
 *
 * 4*4
 *
 * 4*3+3*3
 *     3*3
 *
 *     3*2+2*2
 * 4*2+3*2+2*2
 *         2*2
 *
 * we will arrange in such a way that how many times 4 will repeat ,how many times 3 will repeat and so on
 * so we would get something like this
 *
 * 4(4)+3(4+3+3)+2(4+3+3+2+2+2)
 * on further solving we will get
 * 4(1*4)+3(1*4+2*3)+2(1*4+2*3+3*2)
 *
 *
 *
 * so i have created two arrays one which will contain the original elements 4,3,2 named input1
 * and another array named arr which will keep the counts of 4,3,2
 * then we will multiply the elements of arr and input1 and add it to get the output
 *
 *
 *

 */
import java.io.*;
public class test3 {
    //public static long answer=0;
    //public static int longvalue=1000000007;
    public static void main(String args[]) throws IOException
    {
        Scanner s=new Scanner(System.in);
        BufferedReader br=new BufferedReader(new FileReader("F:\\non-gui\\src\\hello.txt"));

        int n=Integer.parseInt(br.readLine());
        int a[]=new int[n];
        String k="";
        int i=0;
        while((k=br.readLine())!=null)
        {
            a[i]=Integer.parseInt(k);
            i++;
        }
        long start=System.currentTimeMillis();
        int output=possibleways(a);
        System.out.println(output);
        long stop=System.currentTimeMillis();
        System.out.println(stop-start);
    }
    private static int possibleways(int[] input1) {
        long[] arr=new long[input1.length];
        long answer=0;
        long longvalue=1000000007;
        arr[0]=input1[0];
        answer=0;
        for(int i=1;i<input1.length;i++)
        {
            int temp=((i+1)*input1[i]);
            arr[i]=arr[i-1]+temp;

        }
        for(int i=0;i<arr.length;i++)
        {
            long temp=(input1[i]*arr[i]);
            answer=(answer+temp)%longvalue;
        }

        return (int)(answer%longvalue);
    }

}
