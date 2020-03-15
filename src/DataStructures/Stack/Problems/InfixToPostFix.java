package DataStructures.Stack.Problems;

import DataStructures.Stack.StackImpl.SLLStack;

/**
    Given a string of infix expression, check whether it can be converted to it's respective
    postfix expression
 */

public class InfixToPostFix {
    public static void main(String[] args) {
        String[] array = {
          "a+b", "(a*b+c)", "a+b-c", "(a+b)*c-d", "(())"
        };
        for(String string : array){
            System.out.println(convertInfixToPostFix(string));
        }
    }
    private static int getPrecedence(char c){
        if(c == '^') return 4;
        if(c == '*' || c == '/') return 3;
        if(c == '+' || c == '-') return 2;
        return -1;
    }
    private static String convertInfixToPostFix(String string){
        StringBuilder answer = new StringBuilder();
        SLLStack<Character> stack = new SLLStack<>();
        try{
            for(char c : string.toCharArray()){
                if(Character.isLetterOrDigit(c)) answer.append(c);
                else if(c == '(') stack.push(c);
                else if(c == ')'){

                    // pop till ( is not found and add to output string
                    while(!stack.isEmpty() && stack.peek() != '('){
                        answer.append(stack.pop());
                    }

                    // if stack is empty then there is extra )
                    // whose respective ( could not be found so, throw exception
                    // else pop (
                    if(stack.isEmpty()) throw new Exception("mismatched number of closing brackets");
                    else stack.pop();
                }
                else{
                    // if stack is not empty and top of the stack operator has higher precedence over current char
                    // and top of the stack is not ( then pop until
                    // stack is empty or top of the stack
                    while(!stack.isEmpty() && getPrecedence(stack.peek()) > getPrecedence(c) && stack.peek() != '(')
                            answer.append(stack.pop());
                    stack.push(c);
                }
            }

            // now empty all the top of the stack elements
            // if ( is found then it is extra and throw error
            while(!stack.isEmpty()) {
                if(stack.peek() == '(') throw new Exception("mismatched number of opening brackets");
                answer.append(stack.pop());
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return answer.toString();
    }
}
