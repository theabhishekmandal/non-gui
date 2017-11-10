package spoj;

/**
 * Created by abhishek mandal on 7/17/2017.
 */
import java.util.*;
public class generate_next_palindrome {

    public static boolean check_if_left_is_large(int a[])   //this method is to check if there exist a number in left side which is greater than right side
    {                                                        //also this method  escapes if the digits are same of the left and right portion
        int mid=a.length/2;
        int left=mid-1;
        int right=(a.length%2==0)? mid:mid+1;
        boolean check=false;
        while(left>=0&&a[left]==a[right])
        {
            left--;
            right++;
        }
        if(left>=0&&a[left]>a[right])
            check=true;
        return check;

    }
    public static boolean is_all_digits_nine(int a[])
    {
        boolean check=true;
        for(int i=0;i<a.length;i++)
        {
            if(a[i]!=9)
            {
                check=false;
                break;
            }
        }
        return check;
    }
    public static int[] increment_and_copy(int a[],int carry)//this method is present to add the carry (0 or 1 according to the condition)
    {                                                        //it adds the carry to the middle digit and passes the carry from the left to
                                                             //most significant

        int mid=a.length/2;
        int left=mid-1;
        int right=(a.length%2==0)? mid:mid+1;
        if(a.length%2==1)
        {
            a[mid]=a[mid]+carry;
            carry=a[mid]/10;
            a[mid]%=10;

        }
        while(left>=0)
        {
            a[left]=a[left]+carry;
            carry=a[left]/10;
            a[left]%=10;
            a[right]=a[left];
            right++;
            left--;
        }
        return a;
    }

    public static void main (String[] args) throws java.lang.Exception
    {
        Scanner s=new Scanner(System.in);
        int t=s.nextInt();
        while(t-->0) {
            String k = s.next();
            int a[] = new int[k.length()];
            for (int i = 0; i < k.length(); i++)
                a[i] = k.charAt(i) - '0';
            if (is_all_digits_nine(a)) {
                String hello = "1";
                for (int i = 0; i < a.length - 1; i++)
                    hello+= "0";
                hello += 1;
                System.out.println(hello);

            } else {
                if (k.equals(new StringBuilder(k).reverse().toString())) {
                    a = increment_and_copy(a, 1);//  if the number is palindrome such as 2222 then it will become 2332 or if 22222 then 22322
                } else {
                    if (check_if_left_is_large(a)) {
                        a = increment_and_copy(a, 0);    //if 14523 then just copy the left part wihtout any carry
                    } else {
                        a = increment_and_copy(a, 1);//if left part is larger than right part then add 1 to the middle part and copy
                    }
                }
                StringBuilder build = new StringBuilder();
                for (int i = 0; i < a.length; i++)
                    build.append(a[i]);
                System.out.println(build);
            }
        }

    }
}
