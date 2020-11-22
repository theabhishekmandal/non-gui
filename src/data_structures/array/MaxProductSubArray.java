package data_structures.array;

/**
 * Given an array of elements find the max Product subArray.
 * if arr = [6, -3, -10, 0, 2], then maxProductSubArray is = 180 [6, -3, -10]
 *
 * Approach:
 *  -   It is just similar to kadane's algorithm to find max sum sub-array, But in case of max product  sub-array there
 *      is a catch.
 *  -   While considering the (current element) and (current element * currMaxValue), it can be that the given element
 *      value is negative, this causes to never pick it(negative value) up again. For example arr = [6, -2, -2]
 *      if currMax = 6, element = -2, then currMax = Math.max(-2, 6 * -2) = -2
 *      and in next iteration currMax = -2, element = -2, then currMax = Math.max(-2, -2 * -2) = 4
 *      This 4 is incorrect because max sub- array is [6, -2, -2] => 24
 *  -   So, to save the negative value, we can use another variable
 *  -   Now, the approach becomes,
 *      -   first initialise currPositive and currNegative as the first element of the
 *          array
 *      -   now if current element is negative then swap both currPositive and currNegative, (this is done so that
 *          if after swapping currPositive has -ve value then currPositive * current element will give positive answer.)
 *      -   Now currPositive will be max(currentElement, currentElement * currPositive)
 *      -   currNegative will be min(currentElement, currentElement * currNegative)
 *      -   maxProduct = Math.max(currPositive, maxProduct), (this is done because there can be n number of subArrays
 *          so out of those subArrays we have to take the maximum value).
 *
 */
public class MaxProductSubArray {
    public static void main(String[] args) {
        System.out.println(getMaxProductSubArray(new int[]{6, -3, -10, 0, 2}));
        System.out.println(getMaxProductSubArray(new int[]{2, 3, -2, 4}));
    }

    private static int getMaxProductSubArray(int[] arr) {
        if(arr == null || arr.length == 0) return 0;
        int maxProduct = arr[0];
        int currPositive = arr[0];
        int currNegative = arr[0];
        for(int i = 1; i < arr.length; i++) {
            int element = arr[i];
            if(element < 0) {
                int temp = currPositive;
                currPositive = currNegative;
                currNegative = temp;
            }
            currPositive = Math.max(element, currPositive * element);
            currNegative = Math.min(element, currNegative * element);
            maxProduct = Math.max(currPositive, maxProduct);
        }
        return maxProduct;
    }
}
/*
    6 -3 -10 0 2
    pos = 6, swap,  max(-3, 6 * -3) -3, swap ,max(-10, -18 * - 10) 180, max(0, 180 * 0) 0, max(2, 0 * 2) 2
    neg = 6,        min(-3, 6 * -3) -18,     ,min(-10, -3 * -10) -10, min(0, -10 * 0) 0, min(2, 0 * 2) 0,
    (18, -18 * -10)
 */
