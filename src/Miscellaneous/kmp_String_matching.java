package Miscellaneous;
/**
 * This program is the implementation of the kmp string matching algorithm
 * the time complexity of this algorithm is O(m + n) where m is the length
 * of pattern P and n is the length of the text T
 *
 * To find the substring we precompute longest prefix and suffix array
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
 *  start the matching of the text
 *
 *  if j is equal to the length of the pattern then the text is matched and we find the next index from
 *  where matching is to be done
 *
 */

import java.util.*;
public class kmp_String_matching
{
    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);
       // System.out.println("enter a text ");
        String text = in.next();
       // System.out.println("enter a pattern");
        String pattern = in.next();
        findsubstring(text, pattern);
    }
    private static void findsubstring(String arr, String hel)
    {
        int lps[] = compute(hel);
        int i = 0;
        int j = 0;
        while(i < arr.length())
        {
            if(arr.charAt(i) == hel.charAt(j))
            {
                i++;
                j++;
            }
            else if(arr.charAt(i) != hel.charAt(j))
            {
                if(j > 0)
                    j = lps[j - 1];
                else
                    i++;
            }
            if(j == hel.length())
            {
                System.out.println("match found at index " + (i - j));
                j = lps[j - 1];
            }
        }
    }
    private static int[] compute(String arr)
    {
        int i = 1;
        int j = 0;
        int lps[] = new int[arr.length()];
        lps[0] = 0;
        for(;i < lps.length;)
        {
            if(arr.charAt(i) == arr.charAt(j))
            {
                lps[i] = j + 1;
                i++;
                j++;
            }
            else
            {
                if(j > 0)
                {
                    j = lps[j - 1];
                }
                else
                {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }
}