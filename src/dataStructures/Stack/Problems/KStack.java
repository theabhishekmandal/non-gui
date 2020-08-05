package dataStructures.Stack.Problems;

import dataStructures.Stack.StackImpl.kStack;

public class KStack {
    public static void main(String[] args) {
        kStack<Integer> stack = new kStack<>(10, 3);
        stack.push(1, 12);
        stack.push(1, 14);
        stack.push(2, 45);
        stack.push(2, 67);

        stack.pop(1);
        stack.push(2, 78);
        System.out.println(stack.toString(1));
        stack.pop(1);
        System.out.println(stack.toString(2));
    }
}
