package DataStructures.Stack.Problems;

import DataStructures.Stack.Node.SLLStack;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * Given a stack sort the elements of the stack using stacks only.
 *
 * Approach:
 *      -   first pop the value from the old stack
 *          -   if the newStack is empty then push that popped value
 *          -   else if top of the stack of newStack is greater than popped value
 *              then start popping from newStack and push in oldStack
 *          -   at last add the popped value to new stack
 */
public class SortingStack {
    public static void main(String[] args) {
        Random random = new Random();
        SLLStack<Integer> stack = new SLLStack<>();
        IntStream.range(0, 10).forEach(x -> stack.push(random.nextInt(100)));
        System.out.println("stack before sorting " + stack);
        SLLStack<Integer> newStack = getSortedStack(stack);
        System.out.println("stack after sorting " + newStack);
    }

    private static <T extends Comparable<? super T>> SLLStack<T> getSortedStack(SLLStack<T> stack) {
        SLLStack<T> newStack = new SLLStack<>();
        while(!stack.isEmpty()){
            T value = stack.pop();
            while(!newStack.isEmpty() && value.compareTo(newStack.peek()) > 0){
                stack.push(newStack.pop());
            }
            newStack.push(value);
        }
        return newStack;
    }
}
