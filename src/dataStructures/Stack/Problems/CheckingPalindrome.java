package dataStructures.Stack.Problems;

import dataStructures.Stack.StackImpl.SLLStack;

/**
 * Given a characters formed with a's and b's. The string marked with special character X which represents the
 * middle of the list(for example ababa...ababXbaba.baba). Check whether the string is palindrome.
 *
 * Approach:
 *  find till x then push the chars to the stack.
 *  from index 0 start checking it with top of the stack element
 */

public class CheckingPalindrome {
    public static void main(String[] args) {
        String[] arr = {
                "ababxbaba",
                "ababxbab",
                "ab"
        };
        for(String string : arr)
            System.out.println(ispalindrome(string));
    }

    private static boolean ispalindrome(String string){
        SLLStack<Character> stack = null;
        for(char c : string.toCharArray()){
            if(c == 'x' || c == 'X') stack = new SLLStack<>();
            if(stack != null) stack.push(c);
        }
        int i = -1;
        while(stack != null && !stack.isEmpty()){
            if(string.charAt(++i) != stack.pop()) return false;
        }
        return true;
    }
}
