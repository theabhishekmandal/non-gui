package Miscellaneous.DynamicProgramming;

import java.util.Scanner;

public class LongestCommonSubstring {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String one = s.next().trim();
        String two = s.next().trim();
        int[][] arr = new int[one.length() + 1][two.length() + 1];
        int max = Integer.MIN_VALUE;
        for(int i = 0; i <= one.length(); i++){
            for(int j = 0; j <= two.length(); j++){
                if(i == 0 || j ==0) continue;
                else if(one.charAt(i - 1) == two.charAt(j - 1)){
                    arr[i][j] = 1 + arr[i - 1][j - 1];
                    max = Math.max(max, arr[i][j]);
                }
                else{
                    arr[i][j] = 0;
                }
            }
        }
        //out.println(Arrays.deepToString(arr));
        System.out.println(max);
    }
}
