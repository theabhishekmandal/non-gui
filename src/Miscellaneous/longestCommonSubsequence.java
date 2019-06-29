package Miscellaneous;

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
