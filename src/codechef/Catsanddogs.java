package codechef;
import java.util.*;
public class Catsanddogs     {
    public static void main(String args[])
    {
        Scanner s=new Scanner(System.in);
        int t=s.nextInt();
        while(t-->0)
        {
            long c=s.nextInt();
            long d=s.nextInt();
            long l=s.nextInt();
            long min=0;
            long max=(c+d)*4;
           if(c-2*d<=0)
               min=d*4;
           else
               min=(d+(c-2*d))*4;

            if((l%4==0)&&l>=min&&l<=max)
                System.out.println("yes");
            else
                System.out.println("no");

        }
    }
}
