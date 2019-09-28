package Miscellaneous.DynamicProgramming;
/**
 * This is based on dynamic programming: 1) Overlapping subproblems 2) Optimal substructure
 * This problem can be solved by using dynamic programming as:
 *
 * 1) if i - 1th and j - 1th character of String one and String two matches then the equation becomes
 *  L(one[i], two[j]) = 1 + L(one[i - 1], two[j - 1])
 *  eg: L(“AGGTAB”, “GXTXAYB”) = 1 + L(“AGGTA”, “GXTXAY”)
 *
 *  2) if the i - 1th and j - 1th character of String one and String two doesn't match then the equation becomes:
 *  L(one[i], two[j]) = Max(L(one[i - 1], two[j]), L(one[i], two[j - 1]))
 *  eg: L(“ABCDGH”, “AEDFHR”) = MAX ( L(“ABCDG”, “AEDFHR”), L(“ABCDGH”, “AEDFH”) ), here we see the max subsequence
 *  that is common to both is "ADH"
 *
 *  let the matrix of the two strings be this
 *                            g  x  t  x  a  y  b
 *                            0  1  2  3  4  5  6  7
 *                     a   0 [0, 0, 0, 0, 0, 0, 0, 0]
 *                     g   1 [0, 0, 0, 0, 0, 1, 1, 1]
 *                     g   2 [0, 1, 1, 1, 1, 1, 1, 1]
 *                     t   3 [0, 1, 1, 1, 1, 1, 1, 1]
 *                     a   4 [0, 1, 1, 2, 2, 2, 2, 2]
 *                     b   5 [0, 1, 1, 2, 2, 3, 3, 3]
 *                         6 [0, 1, 1, 2, 2, 3, 3, 4]
 *
 * Notes:
 *  1. Index represents the length of the strings taken at a time, so if i == 0 or j == 0  then we
 *      can see that there is no common subsequence.
 *
 *  2. i and j index represents the length of the two strings, now the current matching value will depend on
 *      the previous i and j values. i.e to find how many characters got matched in "aggt" and "gxtx" we need to find
 *      how many got matched in "agg" and "gxtx" (a length lesser). And similarly for "agg" and "gxtx" we need to find
 *      in "ag" "gx".
 *
 *      a[1][5] = 1, it means that for first string of length 1 i.e 'a' matches with one of the character of the
 *      second string of length 5 i.e "gxtxa"
 *       a[4][3] = 2, it means that for first string of length 4 i.e "aggt", matches with 2 characters of the
 *      second string of length 3 i.e "gxt"
 *
 *
 */
import java.util.*;
import static java.lang.System.*;
public class longestCommonSubsequence{
    private static void debug(Object...a){
        System.err.println(Arrays.deepToString(a));
    }
    private static int getlength(String one, String two){
        int found = 0;
        int[][] arr = new int[one.length() + 1][two.length() + 1];
        //out.println(Arrays.deepToString(arr));
        for(int i = 0; i <= one.length(); i++){
            for(int j = 0; j <= two.length(); j++){
                if(i == 0 || j ==0) continue;
                else if(one.charAt(i - 1) == two.charAt(j - 1)){
                    arr[i][j] = 1 + arr[i - 1][j - 1];
                }
                else{
                    arr[i][j] = Math.max(arr[i - 1][j], arr[i][j - 1]);
                }
            }
        }
        return arr[one.length()][two.length()];
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(in);
        String first = s.next();
        s.nextLine();
        String second = s.next();
        System.out.println(getlength(first, second));
    }
}
