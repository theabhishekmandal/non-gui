package Miscellaneous.leetcode.Challenge30DaysApril.Week2;


import java.util.Scanner;

/**
 * ab#####cd#ba#
 * #####a##b#d
 */

public class BackSpaceStringCompare {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String[] arr = {"ab#c", "ad#c", "ab##", "c#d#", "a##c", "#a#c", "a#c", "b"};
        for(int i = 0; i < arr.length; i += 2){
            String first = arr[i];
            String second = arr[i + 1];
            boolean isequal = solve(first, second);
            System.out.println(isequal);
        }
    }

    private static boolean solve(String first, String second) {
        first = getSingleHashString(first);
        second = getSingleHashString(second);
        return first.equals(second);
    }
    private static String getSingleHashString(String string){
        StringBuilder br =  new StringBuilder();
        int hashCounter = 0;
        for(int i = string.length() - 1 ; i >= 0;){
            char curr = string.charAt(i);
            if(curr == '#'){
                int temp = hashCounter;
                for(i = i - 1; i >= 0 && string.charAt(i) == '#';){
                    temp += 1;
                    i--;
                }
                if(i == -1) break;
                while(i >= 0 && string.charAt(i) != '#');
            }
            else
                br.append(curr);

            i--;
        }
        return br.toString();
    }
}
