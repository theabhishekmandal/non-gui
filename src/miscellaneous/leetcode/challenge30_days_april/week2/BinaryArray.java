package miscellaneous.leetcode.challenge30_days_april.week2;

import java.util.Arrays;
import java.util.Random;

/**
 *Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
 *
 * Example 1:
 *
 * Input: [0,1]
 * Output: 2
 * Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
 *
 * Example 2:
 *
 * Input: [0,1,0]
 * Output: 2
 * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 *
 * Note: The length of the given binary array will not exceed 50,000.
 */
public class BinaryArray {
    private static final Random random = new Random();

    public static void main(String[] args) {
        solve();
    }

    private static void first(){
        int[] arr = new int[10  + random.nextInt(10)];
        for(int i = 0; i < arr.length; i++){
            arr[i] = random.nextInt(2);
        }
        arr = new int[]{0, 1, 1, 1, 1, 0, 1, 1, 1, 1};
        int count = 0;
        for(int length = 2; length <= arr.length; length += 2){
            for(int i = 0; i < arr.length - length + 1; i++){
                count = Math.max(count, (find(arr, i, i + length))? length : 0);
            }
        }
        System.out.println(Arrays.toString(arr));
        System.out.println(count);
    }

    private static boolean find(int[] arr, int i, int length) {
        int zeroCounter = 0;
        int oneCounter = 0;
        for(int k = i; k < length; k++){
           if(arr[k] == 0) zeroCounter++;
           else
               oneCounter++;
        }
        return zeroCounter == oneCounter;
    }

    private static void solve(){
        int[] arr = new int[5 + random.nextInt(10)];
        for(int i = 0; i < arr.length; i++){
           arr[i] = random.nextInt(2);
        }

        int counter = 0;
        int maxValue = 0;
        boolean flag = true;
        for(int i = 0; i < arr.length - 2; i += 2){
            int first = arr[i];
            int second = arr[i + 1];
            if(first == 0 && second == 1){
                if(!flag) {
                    maxValue = Math.max(maxValue, counter);
                    counter = 0;
                }
                flag = true;
                counter++;
            }
            else if(first == 1 && second == 0){
                if(flag){
                    maxValue = Math.max(maxValue, counter);
                    counter = 0;
                }
                flag = false;
                counter++;
            }
            else{
                maxValue = Math.max(maxValue, counter);
                counter = 0;
            }
        }
        System.out.println(Arrays.toString(arr));
        System.out.println(maxValue);
    }
}
