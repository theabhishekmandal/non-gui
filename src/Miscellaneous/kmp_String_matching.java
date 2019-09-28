package Miscellaneous;
/**
 * http://www.mathcs.emory.edu/~cheung/Courses/323/Syllabus/Text/Matching-KMP1.html
 * The KMP matching algorithm uses degenerating property (pattern having same sub
 * -patterns appearing more than once in the pattern) of the pattern and improves the worst
 * case complexity to O(n). The basic idea behind KMP’s algorithm is: whenever we detect a
 * mismatch (after some matches), we already know some of the characters in the text of the
 * next window. We take advantage of this information to avoid matching the characters that
 * we know will anyway match
 *
 * This program is the implementation of the kmp string matching algorithm
 * the time complexity of this algorithm is O(m + n) where m is the length
 * of pattern P and n is the length of the text T
 *
 * To find the substring we precompute longest prefix and suffix array of the pattern
 * This array stores the information of the longest proper suffix which is also
 * a prefix of the pattern.
 *
 * while computing the lps array we use two counters i and j
 * initially i = 1 and j = 0
 * if P.charAt(i) == P.charAt(j) then we store the value in the lps
 *
 * if it does not match then we find the previous character position from where we should start matching
 * only if j > 0 else we increment i
 *
 * so in this way we precompute the lps array
 *
 * in the findsubstring()
 *  initially we take two counter i = 0 for the text and j = 0  for the pattern
 *
 *  if T.charAt(i) == P.charAt(j) then we increment both
 *  else if they do not match then we find the previous position for the pattern from where it should
 *  start the matching of the text, if it still does not find the value from where it should start
 *  then just increment i
 *
 *  if j is equal to the length of the pattern then the text is matched and we find the next index from
 *  where matching is to be done
 *
 */

import java.util.*;
public class kmp_String_matching {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
       // System.out.println("enter a text ");
        String text = in.next();
       // System.out.println("enter a pattern");
        String pattern = in.next();
        findsubstring(text, pattern);
    }
    private static void findsubstring(String arr, String pattern) {
        int lps[] = compute(pattern);
        int i = 0;
        int j = 0;
        while(i < arr.length()) {
            if(arr.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }
            else if(arr.charAt(i) != pattern.charAt(j)) {
                if(j > 0)
                    j = lps[j - 1];
                else
                    i++;
            }
            if(j == pattern.length()) {
                System.out.println("match found at index " + (i - j));
                j = lps[j - 1];
            }
        }
    }

    /*
    This is to precompute the prefix array.
    1. two counters are used and and lps array
    2. the first if condition describes that when there is match,
        then add the incremented value of j to array, because till i in the pattern
        there is a substring of length (length present at lps[i]) which is suffix as well
        as prefix. For eg: if pattern = "acaca" i = 3, j = 1, lps[] = [0, 0, 1, 2]
        so for pattern starting from 0 to (i = 3) index there is a substring of length
        2 which is suffix as well as prefix.
     3. the else condition is present because if ith and jth characters don't match, then at what
        previous index value j should start from.
            if j is greater than 0, then the new value of j would be lps[j - 1], this lps[j - 1]
            will provide two things, first the length of the substring which is suffix and prefix
            and the next index of j from which we will start again the match. if the current j
            index dosen't match then we will get new value of j = lps[j - 1] and matching continues.

            example: pattern = "a  c  a  c  a  b  a  c  a  c  a  b  a  c  a   c   a  c"
                                0  1  2  3  4  5  6  7  8  9 10 11 12 13 14  15  16 17
                         lps = [0, 0, 1, 2, 3, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ]
            now j = 11 and i = 17 so i and j value doesn't match next j would be j = lps[j - 1]
            i.e j = 5, so there is a substring of length 5 which is suffix as well as prefix
            we check again if i and j character match
            j = 5, i = 17, i and j value doesn't match j becomes j = lps[5 - 1] = 3
            now j = 3 and j = 17 and the value does match so new value is added at lps[i] = 3 + 1
            lps = [0, 0, 1, 2, 3, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 3 + 1]
     */
    private static int[] compute(String arr) {
        int i = 1;
        int j = 0;
        int lps[] = new int[arr.length()];
        lps[0] = 0;
        for(;i < lps.length;) {
            if(arr.charAt(i) == arr.charAt(j)) {
                lps[i] = j + 1;
                i++;
                j++;
            }
            else {
                if(j > 0) {
                    j = lps[j - 1];
                }
                else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }
}