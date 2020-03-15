package DataStructures.Stack.Problems;

import DataStructures.Stack.StackImpl.SLLStack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Given a infix expression, evaluate it in a single pass
 * Approach:
 *  use two stacks operand stack and operator stack
 */

public class InfixEvaluation {
    public static void main(String[] args) {
        String[] array = {
                "1+2", "(1*2+3)", "1+2-3", "(1+2)*3-4", "(())"
        };
        for(String string : array){
            System.out.println(evaluateInfix(string));
        }
    }

    private static int getPrecedence(char c){
        if(c == '^') return 4;
        if(c == '*' || c == '/') return 3;
        if(c == '+' || c == '-') return 2;
        return -1;
    }

    private static int evaluateInfix(String string){
        SLLStack<Integer> operandStack = new SLLStack<>();
        SLLStack<Character> operatorStack = new SLLStack<>();
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
            for(char c : string.toCharArray()){
                if(Character.isDigit(c)) operandStack.push(c - '0');
                else if(c == '(') operatorStack.push(c);
                else if(c == ')'){

                    // pop until ( is found and used to the popped operator to evaluate the result
                    while(!operatorStack.isEmpty() && operatorStack.peek() != '('){
                        popAndPushResult(operatorStack, operandStack, map);
                    }

                    // if ( is not found then throw error else pop (
                    if(operatorStack.isEmpty()) throw new Exception("mismatched number of closing brackets");
                    operatorStack.pop();
                }
                else{
                    // if the top of stack has higher precedence then pop the operator and two operands and compute
                    // result till top of the stack doesn't have equal or less precedence then current character
                    while(!operatorStack.isEmpty() && getPrecedence(operatorStack.peek()) > getPrecedence(c)
                            && operatorStack.peek() != '('){
                        popAndPushResult(operatorStack, operandStack, map);
                    }
                    operatorStack.push(c);
                }
            }

            // pop the remaining operators and execute the result
            while(!operatorStack.isEmpty()){
                if(operatorStack.peek() == '(') throw new Exception("mismatched number of opening brackets");
                popAndPushResult(operatorStack, operandStack, map);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        // return result if exists eg: if (()) then return 0
        return operandStack.isEmpty() ? 0 : operandStack.pop();
    }

    private static void popAndPushResult(SLLStack<Character> operatorStack, SLLStack<Integer> operandStack,
                                         Map<Character, BiFunction<Integer, Integer, Integer>> map) throws Exception{
        char operator = operatorStack.pop();
        int[] arr = {0, 0};
        for(int i = 0; i < 2; ++i){
            if(operandStack.isEmpty()) throw new Exception("not enough operands");
            arr[arr.length - i - 1] = operandStack.pop();
        }
        operandStack.push(map.get(operator).apply(arr[0], arr[1]));
    }

}
