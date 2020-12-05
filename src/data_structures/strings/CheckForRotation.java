package data_structures.strings;

import data_structures.Pair;

import java.util.Arrays;
import java.util.List;

/**
 * Given two strings find whether one string is the rotation of the second one.
 * For eg one = "abacd" two = cdaba", then function will return true
 * Approach 1
 *  -   First approach will be just concatenate the first string with itself and check whether
 *      the second string is present in the first one or not.
 *      eg: one = "abacd" two = cdaba"
 *          adding one two times temp = "abacdabacd" now search "cdaba" in this
 *  -   Time complexity is more since it checks again and again with each character
 *
 *  Approach 2
 *  -   This approach depends on the KMP string matching algorithm.
 *  -   we have string one = "abacd" two = cdaba". Using both of these we will generate prefix array
 *  -   now the prefix array will tell us at what index was the rotation done in the second string
 *  -   For eg for the above two strings lps array will be lps[0, 0, 0, 1, 2] here 2 represents two things i.e
 *      -   2 length sub-string which is matched in both of them, now we have to match the other remaining characters eg [c, d]
 *          is suffix in original string and prefix in the rotated string
 *      -   Starting from index 2 in rotated string we have to start matching the remaining characters
 */

public class CheckForRotation {
    public static void main(String[] args) {
        List<Pair<String, String>> list = Arrays.asList(
                Pair.of("abacd", "cdaba"),
                Pair.of("geeks", "eksge")
        );
        for(var strings : list) {
            String rotation = (areRotationsFast(strings.getFirst(), strings.getSecond())) ? " a rotation" : " not a rotation";
            System.out.println("string two = " + strings.getSecond() + " is" + rotation + " of string one = " + strings.getFirst());
        }
    }
    private static boolean areRotations(String one, String two) {
        if(one == null || two == null || one.trim().isEmpty() || two.trim().isEmpty()) {
            return false;
        }
        String temp = one + one;
        return temp.contains(two);
    }

    private static boolean areRotationsFast(String one, String two) {
        if(one == null || two == null || one.trim().isEmpty() || two.trim().isEmpty()) {
            return false;
        }
        int[] lps = new int[one.length()];
        lps[0] = 0;
        int i = 1;
        int j = 0;
        while(i < one.length()) {
            if(one.charAt(i) == two.charAt(j)) {
                lps[i] = j + 1;
                i++;
                j++;
            }
            else {
                if(j == 0) {
                    lps[i] = 0;
                    i++;
                }
                else {
                   j = lps[j - 1];
                }
            }
        }

        i = 0;
        for(int k = lps[one.length() - 1]; k < two.length(); k++, i++) {
            if(two.charAt(k) != one.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
