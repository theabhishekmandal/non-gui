package data_structures.array;

import java.util.Random;
import java.util.function.Supplier;

public class MultiplyLargeNumbers {
    public static void main(String[] args) {
        Random random = new Random();

        //input  two strings where the large numbers are stored in reverse
        Supplier<char[]> sup = () -> {
            String k = String.valueOf(1 + random.nextInt(100));
            System.out.println(k);
            return new StringBuilder(k).reverse().toString().toCharArray();
        };
        char[] a = sup.get();
        char[] b = sup.get();

        int[] res = new int[a.length + b.length];

        for (int i = 0; i < a.length; i++) {
            int num1 = a[i] - '0';

            int carry = 0;

            int j = 0;

            for (; j < b.length; j++) {
                int num2 = b[j] - '0';

                int temp = num1 * num2 + carry + res[i + j];

                res[i + j] = temp % 10;
                carry = temp / 10;
            }
            if (carry > 0) {
                res[i + b.length] = carry;
            }
        }

        int i = res.length - 1;
        while (i >= 0 && res[i] == 0) {
            i--;
        }

        StringBuilder result = new StringBuilder();
        while (i >= 0) {
            result.append(res[i]);
            i--;
        }
        System.out.print(result);
    }
}
