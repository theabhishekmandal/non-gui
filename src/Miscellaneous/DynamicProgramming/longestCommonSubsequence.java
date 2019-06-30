package Miscellaneous.DynamicProgramming;
/**
 * This is based on dynamic programming: 1) Overlapping subproblems 2) Optimal substructure
 * This problem can be solved by using dynamic programming as:
 *
 * 1) if ith and jth character of String one and String two matches then the equation becomes
 *  L(one[i], two[j]) = 1 + L(one[i - 1], two[j - 1])
 *  eg: L(“AGGTAB”, “GXTXAYB”) = 1 + L(“AGGTA”, “GXTXAY”)
 *
 *  2) if the ith and jth character of String one and String two doesn't match then the equation becomes:
 *  L(one[i], two[j]) = Max(L(one[i - 1], two[j]), L(one[i], two[j - 1]))
 *  eg: L(“ABCDGH”, “AEDFHR”) = MAX ( L(“ABCDG”, “AEDFHR”), L(“ABCDGH”, “AEDFH”) ), here we see the max subsequence
 *  that is common to both is "ADH"
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
        //out.println(Arrays.deepToString(arr));
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
