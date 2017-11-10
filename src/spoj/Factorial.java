package spoj;
import java.util.*;
public class Factorial {
    public static void main(String args[])
    {
        Scanner s=new Scanner(System.in);
        int t=s.nextInt();
        while(t-->0)
        {
            long num=0;
            Long n=s.nextLong();
            long five=5;
            while((n/five)>0)
            {
                num=num+(n/five);
                five=five*5;
            }
            System.out.println(num);
        }
    }

}
