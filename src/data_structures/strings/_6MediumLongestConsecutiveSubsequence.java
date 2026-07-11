package data_structures.strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class _6MediumLongestConsecutiveSubsequence {
    public static void main(String[] args) {
        System.out.println(longestConsecutive(new int[]{100,4,200,1,3,2}));
        System.out.println(longestConsecutive(new int[]{1, 0, 1, 2}));

    }

    public static int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            set.add(i);
        }

        int maxlength = 0;
        // remember to start with set and not with array
        // as array can have duplicate values which can give time limit exceeded.
        for (int num : set) {
            int temp = num;

            // if the previous is present then don't continue with current number
            // since it will be covered later.
            if (!set.contains(temp - 1)) {
                int length = 1;
                while (set.contains(temp + 1)) {
                    temp++;
                    length++;
                }
                maxlength = Math.max(length, maxlength);
            }
        }
        return maxlength;
    }
}
