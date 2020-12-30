package data_structures.array;

/**
 * Given an array of integers, find minimum operations to make the array palindrome
 * ex: arr = [1, 4, 5, 9, 1] can be converted to arr = [1, 9, 9, 1] by merging 4 and 9
 *
 * Approach:
 *  -   take two pointers one at 0 one at end
 *  -   if the characters match then move the pointers
 *  -   if they don't match then we have to add numbers
 *          -   if arr[left] > arr[right] then add arr[left + 1] += arr[left] and increment left. Also count these additions
 *          -   if arr[left] < arr[right] then add arr[right - 1] += arr[right] and decrement right. Also count these additions
 *  -   in the end return the additions which will be the final answer.
 */
public class MinimumOpToMakePalindrome {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 4, 5, 9, 1};
        System.out.println(findMinOperationsToMakePalindrome(arr));
    }

    private static int findMinOperationsToMakePalindrome(int[] arr) {
        int ans = 0;
        for (int i = 0, j = arr.length - 1; i < j;) {
            if (arr[i] == arr[j]) {
                i++;
                j--;
            } else if (arr[i] < arr[j]) {
                ans++;
                i++;
                arr[i] += arr[i - 1];
            } else {
                ans++;
                j--;
                arr[j] += arr[j + 1];
            }
        }
        return ans;
    }
}
