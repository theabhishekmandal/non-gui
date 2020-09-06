package miscellaneous;

import java.util.Arrays;
import java.util.Random;

/**
 * Below is the implementation of finding the nth largest variable in a single iteration,
 * To find the nth variable we have to use n variables.
 * Approach:
 *  -   Initialise array kArray of n size which will hold our 1 to n largest value in reverse order
 *      i.e 0 index will contain nth largest value and n - 1 index will contain the largest value
 *  -   now iterate over input array and check if input is greater than kArray(using reverse index)
 *          -   if it is greater then first replace every other index i.e i < indexToBeReplaced with kArray[i + 1] value
 *              and assign kArray[indexToBeReplaced] = input, then break
 *              eg: initially kArray[m, m, m], input = [1, 2, 3]
 *                  when input = 1, kArray[m, m, 1]
 *                                          /  /
 *                       input = 2, kArray[m, 1, 2]
 *                                          /  /
 *                       input = 3, kArray[1, 2, 3]
 *              eg:
 *                  initially kArray[m, m, m], input = [3, 1, 2]
 *                  when input = 3, kArray[m, m, 3]
 *                                          /
 *                       input = 1, kArray[m, 1, 3]
 *                                          /
 *                       input = 2, kArray[1, 2, 3]
 */
public class FindingNthHighestNumber {
    public static void main(String[] args){
        int[] arr = new int[20];
        Random random = new Random();
        for(int i = 0; i < arr.length; i++){
            arr[i] = random.nextInt(100);
        }
        int k = 1 + random.nextInt(10);

        System.out.println("k = " + k + "\n" + Arrays.toString(arr));
        System.out.println(solve(arr, k)[0]);
        System.out.println(Arrays.toString(solve(arr, k)));
    }
    static int[] solve(int[] arr, int k) {
        int[] kArray = new int[k];
        Arrays.fill(kArray, Integer.MIN_VALUE);
        for(int num : arr) {
            for(int i = kArray.length - 1; i >= 0; i--) {
                if(num > kArray[i]) {
                    System.arraycopy(kArray, 1, kArray, 0, i);
                    kArray[i] = num;
                    break;
                }
            }
        }
        return kArray;
    }
}