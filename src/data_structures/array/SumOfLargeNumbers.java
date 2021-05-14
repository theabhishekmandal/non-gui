package data_structures.array;

import java.util.Random;
import java.util.function.Supplier;

/**
 * Given two large numbers in the form of arrays. Find there sum.
 */
public class SumOfLargeNumbers {
    public static void main(String[] arg) {
        Random random = new Random();

        //input  two strings where the large numbers are stored in reverse
        Supplier<char[]> sup = () -> {
            String k = String.valueOf(1 + random.nextInt(100));
            System.out.println(k);
            return new StringBuilder(k).reverse().toString().toCharArray();
        };
        char[] a = sup.get();
        char[] b = sup.get();
        //calculating the max length
        int max = Math.max(a.length, b.length);


        if(a.length > b.length) {
            char[] c = a;
            a = b;
            b = c;
            c = null;
        }

        //initialising the carry
        int carry = 0;
        for(int i = 0; i < a.length; i++) {
            int sum = carry + a[i] - '0' + b[i] - '0';
            b[i] = (char)((sum % 10) + '0');
            carry = sum / 10;
        }

        for(int i = a.length; i < b.length; i++) {
            int sum = carry + b[i] - '0';
            b[i] = (char)((sum % 10) + '0');
            carry = sum / 10;
        }

        //if carry is not zero then we would just display
        if(carry != 0) {
            System.out.print(carry);
        }
        for(int i = max - 1; i >= 0; i--) {
            System.out.print(b[i]);
        }
    }
}
