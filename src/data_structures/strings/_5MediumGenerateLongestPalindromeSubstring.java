package data_structures.strings;

public class _5MediumGenerateLongestPalindromeSubstring {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));
    }


    public static String longestPalindrome(String s) {
        int start = 0;
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            // for every center we have to look for both
            int oddLen = expand(i, i, s);
            int evenLen = expand(i, i + 1, s);
            int len = Math.max(oddLen, evenLen);
            if (len > maxLen) {
                maxLen = len;
                // i is the middle index of even/odd length palindrome
                // so to get the start of the string then you have to subtract half from it.
                start = i - (len - 1) / 2;
            }
        }
        return s.substring(start, start + maxLen);
    }
    private static int expand(int left, int right, String s) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    public static String longestPalindromeSubstring(String s) {
        // we will use manacher's algorithm
        String string = "#" + String.join("#", s.split("")) + "#";
        int[] radius = new int[string.length()];
        int r = 0;
        int c = 0;

        for (int i = 0; i < string.length(); i++) {
            int mirror = 2 * c - i;

            // if r is present and greater than 0 it means there is some palindrome substring already calculated.
            // for which r is the rightmost end of the palindrome substring.
            // if i is smaller than r, in that case two things can happen
            // mirror of i may reach beyond 0, or it may be under 0
            // if it is under 0, then you can copy the mirror value to i, which would be similar.
            // if it is less than 0, in that case, radius of i will be r - i, as r is the rightmost index of the palindrome
            // substring to least palindrome substring length would be r - i.
            if (i < r) {
                radius[i] = Math.min(r - i, radius[mirror]);
            }


            int left = i - radius[i] - 1;
            int right = i + radius[i] + 1;
            // we have calculated the radius[i] from mirror above now we will expand around i further for the search of palindrome
            // substring if possible.
            while (left >= 0 && right < string.length() && string.charAt(left) == string.charAt(right)) {
                radius[i]++;
                left = i - radius[i] - 1;
                right = i + radius[i] + 1;
            }

            // now after expansion we will update the current center and the radius.
            // note that we are not keeping the max center, for every index we are calculating the center.
            if (i + radius[i] > r) {
                c = i;
                r = i + radius[i];
            }
        }

        int maxCenter = 0;
        int maxRadius = 0;
        for (int i = 0; i < string.length(); i++) {
            if (radius[i] > maxRadius) {
                maxRadius = radius[i];
                maxCenter = i;
            }
        }

        // now the new string object has extra characters,
        int left = (maxCenter - maxRadius) / 2;
        int right = (maxCenter + maxRadius) / 2;

        return s.substring(left, right);
    }
}
