package data_structures.array;
import java.util.*;

/**
 * Find factorial of a large number.
 * Approach
 *  -   Factorial of a large number cannot be stored in long or double.
 *  -   Instead we can use integer array of a large length where each index will hold one digit of that large factorial result
 *  -   Also, we are storing the result of factorial in reverse order i.e 0 index of array will hold least significant digit
 *      while the last will hold the most significant digit. example if n = 12345 then arr = [5, 4, 3, 2, 1]
 *  -   Initialise the 0th index with 1, as factorial of 1 and 0 is always 1.
 *  -   Starting from index 2 to n, we multiply the number with each element of the array and store according to carry generated
 *      or not. eg arr = [1, 2, 3] and i = 12, then we do 12 * 36, 12 * 2, 12 * 1
 *              1 2 3
 *               * 12
 *             ------
 *                36
 *               24
 *              12
 *             ------
 *             1476
 *  -   If there is a carry then we add it to the last index pointed by m and increment it.
 *  -   In the last just reverse add it to the string and factorial is generated.
 */
public class LargeFactorials {
    public static void main(String[] args) {
        Random random = new Random();
        int n = 20 + random.nextInt(1);
        for(int i = 1; i < n; i++) {
            System.out.println("given number is " + i);
            System.out.println(getFactorial(i));
            System.out.println();
        }
    }

    private static String getFactorial(int n) {
        if(n <= 1) return "1";
        int[] arr = new int[200];
        arr[0] = 1;
        int m = 1;
        StringBuilder br = new StringBuilder();
        for(int i = 2; i <= n; i++) {
            int carry = 0;
            for(int j = 0; j < m; j++) {
                int temp = i * arr[j] + carry;
                carry = temp / 10;
                arr[j] = temp % 10;
            }
            while(carry > 0) {
                arr[m] = carry % 10;
                carry /= 10;
                m++;
            }
        }
        for(int i = 0; i < m; i++) {
           br.insert(0, arr[i]);
        }
        return br.toString();
    }
}
