package data_structures.stack.problems;

import data_structures.stack.stack_impl.SLLStack;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Given a infix expression, evaluate it in a single pass
 * Things to Remember
 *  -   use two stacks operand stack and operator stack
 *  -   lower precedence operator cannot be on top of higher precedence operator
 *  -   To compute the result, pop single operator from operator stack and pop two operands from operand stack
 *      apply the function related to the operator
 *  -   if high precedence operator is on top of stack, and next character is of lower precedence, then
 *      compute the result
 *  -   Don't forget to compute the result if both of stacks are non empty, this case will arrive when the string is
 *      processed but the stacks are non empty.
 * Approach:
 *  -   If the character is digit then find more characters that can form an integer and then push it to operand stack
 *  -   If the character is '(' then push it to operator stack
 *  -   If the character is ')' then pop until '(' is reached, also while popping compute the result
 *  -   If the character is an operator [^, +, -, *], then first check if top of the stack have higher precedence operator
 *      or not, if it is present then first compute the result then push current operator, otherwise push the operator
 *      directly to operator stack.
 */


class ConvertInfixString {

    private final SLLStack<Integer> operandStack;
    private final SLLStack<Character> operatorStack;
    private char[] inputArray;
    private final Map<Character, BiFunction<Integer, Integer, Integer>> applyFunctionMap;

    public ConvertInfixString() {
        operandStack = new SLLStack<>();
        operatorStack = new SLLStack<>();

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
        inputArray = s.toCharArray();
        try {
            int i = 0;
            while(i < inputArray.length) {

                char c = inputArray[i];
                if(Character.isDigit(c)) {
                    i = convertToIntegerAndPush(i);
                    continue;
                }
                else if(c == '(') {
                    operatorStack.push(c);
                }
                else if(c == ')'){
                    while(!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                        pushResult();
                    }
                    if(operatorStack.isEmpty()) {
                        throw new EmptyStackException();
                    }
                    operatorStack.pop();
                }
                else {
                    while(!operatorStack.isEmpty() && operatorStack.peek() != '(' && isGreater(operatorStack.peek(), c)) {
                        pushResult();
                    }
                    operatorStack.push(c);
                }
                i++;
            }
            computeIfNotEmpty();
            result = operandStack.isEmpty()? 0 : operandStack.pop();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        finally {
            emptyBothStack();
        }
        return result;
    }

    // emptying both stack after each string
    private void emptyBothStack() {
        while(!operandStack.isEmpty()) {
            operandStack.pop();
        }

        while(!operatorStack.isEmpty()) {
            operatorStack.pop();
        }
    }

    // if end of the input string reached, but stacks are not empty then compute result
    private void computeIfNotEmpty() {
        while(!operatorStack.isEmpty()){
            if(operatorStack.peek() == '(') {
                throw new InputMismatchException();
            }
            pushResult();
        }
    }

    // getting precedence of current operator
    private int getPrecedence(char c){
        if(c == '^') return 4;
        if(c == '*' || c == '/') return 3;
        if(c == '+' || c == '-') return 2;
        return -1;
    }

    // popping one operator and two operands, computing the result and pushing result to operand stack
    private void pushResult() {
        char operator = operatorStack.pop();
        int second = operandStack.pop();
        int first = operandStack.pop();
        operandStack.push(applyFunctionMap.get(operator).apply(first, second));
    }

    // checking if top of the stack precedence is greater than current char, if true pop the operator and compute result
    private boolean isGreater(char first, char second) {
        return getPrecedence(first) > getPrecedence(second);
    }

    // converting integer string and then pushing to operand stack
    private int convertToIntegerAndPush(int i) {
        StringBuilder br = new StringBuilder();
        while(i < inputArray.length && Character.isDigit(inputArray[i])) {
            br.append(inputArray[i++]);
        }
        operandStack.push(Integer.parseInt(br.toString()));
        return i;
    }
}

public class InfixEvaluation {
    public static void main(String[] args) {
        String[] array = {
                "11+22*14", "(1*2+3)", "1+2-3", "(1+2)*3-4", "(())"
        };
        ConvertInfixString obj = new ConvertInfixString();

        for(String string : array){
            System.out.println(obj.getResult(string));
        }
    }
}
