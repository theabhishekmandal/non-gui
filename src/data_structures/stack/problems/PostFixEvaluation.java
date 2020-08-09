package data_structures.stack.problems;

import data_structures.stack.stack_impl.SLLStack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Given a postfix expression, check whether it can be evaluated or not
 * Things to Remember
 *  -   a stack will be used to push the numbers and the result will also be in this stack.
 *
 *  Approach
 *      -   If the character is a digit then push it to the stack
 *      -   If the character is an operator then pop two operands from the stack, compute the result
 *          based on the operator and push the result on to the stack
 *      -   return the result by popping from the stack if it is not empty otherwise return 0
 */

class ConvertPostFixString {
    private final Map<Character, BiFunction<Integer, Integer, Integer>> applyFunctionMap;
    private final SLLStack<Integer> sllStack;

    public ConvertPostFixString() {
        sllStack = new SLLStack<>();
        applyFunctionMap = new HashMap<>();

        applyFunctionMap.put('+', Integer::sum);
        applyFunctionMap.put('-', (first, second) -> first - second);
        applyFunctionMap.put('*', (first, second) -> first * second);
        applyFunctionMap.put('/', (first, second) -> first / second);
    }

    public int getResult(String s) {
        if(s == null || s.trim().isEmpty()) {
            throw new NullPointerException();
        }

        int result;
        char[] inputArray = s.toCharArray();
        try {
            for(char c : inputArray){
                if(Character.isDigit(c)){
                    sllStack.push(c - '0');
                }
                else{
                    int second = sllStack.pop();
                    int first = sllStack.pop();
                    sllStack.push(applyFunctionMap.get(c).apply(first, second));
                }
            }
            result = sllStack.isEmpty() ? 0 : sllStack.pop();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        finally {
            while(!sllStack.isEmpty()) {
                sllStack.pop();
            }
        }
        return result;
    }
}

public class PostFixEvaluation {
    public static void main(String[] args) {
        String[] arr = {
          "123/+",
          "123*+5-",
        };
        ConvertPostFixString obj = new ConvertPostFixString();
        for(String string : arr){
            System.out.println(obj.getResult(string));
        }
    }
}
