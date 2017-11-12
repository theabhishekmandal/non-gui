package codechef.codechef_november;

import java.io.BufferedReader;
import java.util.*;
import java.io.*;
class test
{
    public static void main(String args[]) throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out=new PrintWriter(System.out);
        int t=Integer.parseInt(br.readLine());
        while(t-->0)
        {

            String hel=br.readLine();
            int i=0;
            int a=0;
            int b=0;
            while(i<hel.length()&&hel.charAt(i)=='.') i++;
            if(i==hel.length())
                out.println(a+" "+b);

            else
            {
                int j=i;
                for(;i<hel.length();i++)
                {
                    if(hel.charAt(i)!='.')
                    {
                        if(hel.charAt(i)=='A')
                            a++;
                        else
                            b++;
                        if(hel.charAt(i)==hel.charAt(j)&&i!=j)
                        {
                            if(hel.charAt(i)=='A')
                                a+=i-j-1;
                            else
                                b+=i-j-1;
                        }
                        j=i;
                    }
                }

                out.println(a+" "+b);
            }
          out.flush();

        }
    }

}