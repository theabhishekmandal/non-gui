package DataStructures.Stack.Problems;

import DataStructures.Stack.StackImpl.SLLStack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/*
    Given a postfix expression, check whether it can be evaluated or not
 */

public class PostFixEvaluation {
    public static void main(String[] args) {
        String[] arr = {
          "123/+",
          "123*+5-",
        };
        for(String string : arr){
            System.out.println(evaluatePostFixExpr(string));
        }
    }

    private static int evaluatePostFixExpr(String str){
        SLLStack<Integer> stack = new SLLStack<>();
        Map<Character, BiFunction<Integer, Integer, Integer>> map =
                new HashMap<Character, BiFunction<Integer, Integer, Integer>>(){
            {
                put('+', Integer::sum);
                put('-', (first, second) -> first - second);
                put('*', (first, second) -> first * second);
                put('/', (first, second) -> first / second);
            }
        };

        try{
            for(char c : str.toCharArray()){
                if(Character.isDigit(c)) stack.push(c - '0');
                else{
                    // pop the elements and apply the respective function
                    int[] arr = {0, 0};
                    for(int i = 0; i < 2; i++){
                        if(stack.isEmpty()) throw new Exception("not enough operands");

                        // getting the last two elements in reverse order
                        // in which current operation will be applied and pushed to stack
                        arr[arr.length - i - 1] = stack.pop();
                    }
                    stack.push(map.get(c).apply(arr[0], arr[1]));
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        // returning the remaining element of the stack as answer
        return stack.pop();
    }
}
