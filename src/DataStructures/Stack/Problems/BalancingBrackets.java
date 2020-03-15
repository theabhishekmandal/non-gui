package DataStructures.Stack.Problems;


import DataStructures.Stack.StackImpl.SLLStack;

import java.util.HashMap;
import java.util.Map;

public class BalancingBrackets {
    public static void main(String[] args) {
        String[] arr = {
                "(A+B)+(C+D)",
                "((A+B)+(C+D)",
                "((A+B)+[C+D])",
                "[(A+B)+[C+D]}"
        };
        for(String string : arr){
            System.out.println(string + " is " + (checkForBrackets(string)? "" : "not ") + "brackets balanced string");
        }
    }
    private static boolean checkForBrackets(String string){
        boolean match = true;
        SLLStack<Character> stack = new SLLStack<>();
        Map<Character, Character> bracketMap = new HashMap<Character, Character>(){
            {
                put('}', '{');
                put(']', '[');
                put(')', '(');
            }
        };

        for(char c : string.toCharArray()){
            if(bracketMap.containsKey(c)){

                // if opening bracket is not found or if opening bracket is not equal to closing bracket
                if(stack.isEmpty() || stack.pop() != bracketMap.get(c)){
                    match = false;
                    break;
                }
            }
            else if(bracketMap.containsValue(c))
                stack.push(c);
        }

        // if number of opening brackets are more than closing then stack will not empty
        // hence we return false
        return match && stack.isEmpty();
    }
}
