package miscellaneous;

/**
 * Given an array find all the sum in odd length subArray
 * Input: arr = [1,4,2,5,3]
 * Output: 58
 * Explanation: The odd-length subarrays of arr and their sums are:
 * [1] = 1
 * [4] = 4
 * [2] = 2
 * [5] = 5
 * [3] = 3
 * [1,4,2] = 7
 * [4,2,5] = 11
 * [2,5,3] = 10
 * [1,4,2,5,3] = 15
 * If we add all these together we get 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58
 */
public class SumOfOddLengthSubArrays {
    public static void main(String[] args) {
        System.out.println(sumOddLengthSubArrays(new int[]{1, 4, 2, 5, 3}));
    }

    /*
        if j is 0 then add length (i - 1) in j to find the sum
        if j not 0 then do as above but we also need to subtract extra sum that is present
        example suffix sum for arr[1, 4, 2, 5, 3] then suffix sum is temp[1, 5, 7, 12, 15]
        if i = 3(length of subarray to be calculate), j = 0 then answer would be temp[0 + 3 - 1] = temp[2] = 7
        but if j = 2, then ans temp[2 + 3 - 1] - temp[2 - 1] = temp[4] - temp[1] = 15 - 5 = 10
     */
    public static int sumOddLengthSubArrays(int[] arr) {
        int sum = 0;
        int[] temp = new int[arr.length];

        // calculate suffix sum array
        temp[0] = arr[0];
        for(int i = 1; i < arr.length; i++) {
            temp[i] = arr[i] + temp[i - 1];
        }

        // now for odd length
        for(int i = 1; i <= arr.length; i += 2) {
            for(int j = 0; j + i - 1 < arr.length; j++) {
               sum += (j == 0)? temp[j + i - 1] : temp[j + i - 1] - temp[j - 1];
            }
        }
        return sum;
    }
}
