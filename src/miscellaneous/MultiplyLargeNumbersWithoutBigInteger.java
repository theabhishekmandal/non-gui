package miscellaneous;

import java.util.*;
public class MultiplyLargeNumbersWithoutBigInteger {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        //input  two strings where the large numbers are stored in reverse
        char[] a = (new StringBuilder(s.next()).reverse().toString()).toCharArray();
        char[] b = (new StringBuilder(s.next()).reverse().toString()).toCharArray();

        //initialising the result array where the output will be stored
        int[] res = new int[a.length + b.length];

        //now starting with the last digit of first number which is store in reverse order
        for(int i = 0; i< a.length; i++) {
            //converting the digit from char to integer
            int num1 = a[i] - '0';

            //initially the carry is zero
            int carry = 0;

            //starting with the last digit of the second number which is stored in reverse order
            int j = 0;

            for(; j<b.length; j++) {
                // type casting the char to integer
                int num2 = b[j] - '0';

                //multiplying the digits and then the result is added and stored in res
                int temp = num1 * num2 + carry + res[i + j];

                res[i + j] = temp % 10;
                carry = temp / 10;
            }
            //storing the remaining carry at the last position
            if(carry > 0) {
                res[i + b.length] = carry;
            }
        }

        //as we have stored the result in reverse order then we have to print it the other way around
        int i = res.length - 1;
        //escaping all the remaining zeroes which is in front of the number
        while(i >= 0 && res[i] == 0) {
            i--;
        }

        //now printing the digits in same line
        StringBuilder result = new StringBuilder();
        while(i>=0) {
            result.append(res[i]);
            i--;
        }
        System.out.print(result);
    }
}
