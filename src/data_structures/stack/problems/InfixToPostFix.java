package data_structures.stack.problems;

import data_structures.stack.stack_impl.SLLStack;

import java.util.InputMismatchException;

/**
 * Given a string of infix expression, check whether it can be converted to it's respective
 * postfix expression
 *
 * Things to Remember:
 *  -   Use a single stack to store the operators
 *
 * Approach:
 *  -   If the character is digit or alphabet then add it to the answer string
 *  -   If the character is '(' then push it to stack
 *  -   If the character is ')' then pop until '(' is reached, also while popping add it to the answer string
 */

class InfixToPostFixImpl {
    private final SLLStack<Character> stack;
    private StringBuilder answer;

    public InfixToPostFixImpl() {
        this.stack = new SLLStack<>();
    }

    private int getPrecedence(char c){
        if(c == '^') return 4;
        if(c == '*' || c == '/') return 3;
        if(c == '+' || c == '-') return 2;
        return -1;
    }

    public String getPostFixString(String s) {
        if(s == null || s.trim().isEmpty()) {
            throw new NullPointerException();
        }

        char[] arr = s.toCharArray();
        answer = new StringBuilder();
        try {
            for(char c : arr) {
                if(Character.isLetterOrDigit(c)) {
                    answer.append(c);
                }
                else if(c == '(') {
                    stack.push(c);
                }
                else if(c == ')') {
                    while(!stack.isEmpty() && stack.peek() != '(') {
                        answer.append(stack.pop());
                    }
                    if(stack.isEmpty()) {
                        throw new InputMismatchException("less ( no. of brackets");
                    }
                    stack.pop();
                }
                else {
                    while(!stack.isEmpty() && getPrecedence(stack.peek()) > getPrecedence(c)) {
                        answer.append(stack.pop());
                    }
                    stack.push(c);
                }
            }
            computeIfNotEmpty();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        finally {
            while(!stack.isEmpty()) {
                stack.pop();
            }
        }
        return answer.toString();
    }

    private void computeIfNotEmpty() {
        while(!stack.isEmpty()) {
            if(stack.peek() == '(') {
                throw new InputMismatchException("extra ( brackets");
            }
            answer.append(stack.pop());
        }
    }
}
public class InfixToPostFix {
    public static void main(String[] args) {
        String[] array = {
          "a+b", "(a*b+c)", "a+b-c", "(a+b)*c-d", "(())"
        };
        InfixToPostFixImpl obj = new InfixToPostFixImpl();
        for(String string : array){
            System.out.println(obj.getPostFixString(string));
        }
    }
}
