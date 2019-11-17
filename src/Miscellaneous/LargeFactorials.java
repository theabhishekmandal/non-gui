package Miscellaneous;
import java.util.*;
public class LargeFactorials {
    public static void main(String args[])
    {
        Scanner s=new Scanner(System.in);

        //enter number to find the factorial
        int n = s.nextInt();

        //initialising result array to store the result
        int res[] = new int[200];
        res[0]=1;

        //this showing how many digits are present
        int m=1;

        //initialising the carry with zero
        int carry=0;

        //starting from number 2 to number n we will multiply each element
        for(int i = 2; i <= n; i++)
        {
            //this loop we iterate to every digit of the res
            //then we store the result in REVERSE ORDER
            for(int j = 0; j < m; j++)
            {
                res[j] = i * res[j] + carry;
                carry = res[j] / 10;
                res[j] %= 10;
            }
            while(carry > 0)
            {
                //incrementing the number of digits and storing the remaining carry
                res[m++] = carry % 10;
                carry /= 10;
            }

        }
        String k = "";
        //printing the whole array in reverse
        for(int i=m-1;i>=0;i--)
            k += res[i];
        System.out.print(k);
    }
}
