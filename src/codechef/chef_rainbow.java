package codechef;

/*
Chef likes all arrays equally. But he likes some arrays more equally than others. In particular, he loves Rainbow Arrays.

An array is Rainbow if it has the following structure:

First a1 elements equal 1.
Next a2 elements equal 2.
Next a3 elements equal 3.
Next a4 elements equal 4.
Next a5 elements equal 5.
Next a6 elements equal 6.
Next a7 elements equal 7.
Next a6 elements equal 6.
Next a5 elements equal 5.
Next a4 elements equal 4.
Next a3 elements equal 3.
Next a2 elements equal 2.
Next a1 elements equal 1.
ai can be any non-zero positive integer.
There are no other elements in array.

Help Chef in finding out if the given array is a Rainbow Array or not.

Input
The first line of the input contains an integer T denoting the number of test cases.
The first line of each test case contains an integer N, denoting the number of elements in the given array.
The second line contains N space-separated integers A1, A2, ..., AN denoting the elements of array.
Output
For each test case, output a line containing "yes" or "no" (without quotes) corresponding to the case if the array is rainbow array or not.
Constraints
1 ≤ T ≤ 100
7 ≤ N ≤ 100
1 ≤ Ai ≤ 10
Subtasks
Subtask 1 (100 points) : Original constraints
Example
Input
3
19
1 2 3 4 4 5 6 6 6 7 6 6 6 5 4 4 3 2 1
14
1 2 3 4 5 6 7 6 5 4 3 2 1 1
13
1 2 3 4 5 6 8 6 5 4 3 2 1

Output
yes
no
no
 */
import java.io.*;
import java.util.*;
import  data_structure.*;
public class chef_rainbow {
    public static void main(String args[]) throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out= new PrintWriter(System.out);
        int t=Integer.parseInt(br.readLine());
        while(t-->0)
        {
            int k=Integer.parseInt(br.readLine());
            StringTokenizer st=new StringTokenizer(br.readLine());
            int a[]=new int[k];
            for(int i=0;i<k;i++)
                a[i] = Integer.parseInt(st.nextToken());
            boolean check=true;
            if(a[a.length/2]==7)
            {
                int start=0;
                int last=a.length-1;
                while(start<=last)
                {
                    if(a[last]!=a[start])
                    {
                        check=false;
                        break;
                    }
                    start++;
                    last--;
                }
                if(check)
                    out.println("yes");
                else
                    out.println("no");

            }
            else
                out.println("no");

        }
        out.flush();
    }

}
