package data_structures.stack.problems;

import data_structures.stack.stack_impl.SLLStack;

import java.util.Random;
import java.util.stream.IntStream;

/**
 *  Given a stack, how would you store the current minimum onto the stack
 *  Approach:
 *      we would use two stacks, one will contain the elements, other one
 *      will contain the minimum elements
 *
 *  When pushing elements to the main stack, we will push to minStack only
 *  when data <= minStack.peek(), equal to sign is important
 *
 *  when popping elements from the mainStack, we will pop from minStack only
 *  when mainStack.peek() value is equal to minStack.peek()
 */

class AdvanceStack<T extends Comparable<? super T>>{
    private final SLLStack<T> elementStack;
    private final SLLStack<T> minStack;
    public AdvanceStack(){
        elementStack = new SLLStack<>();
        minStack = new SLLStack<>();
    }

    public void push(T data){
        elementStack.push(data);
        if(minStack.isEmpty() || data.compareTo(minStack.peek()) <= 0) {
            minStack.push(data);
        }
    }

    public T pop(){
        if(elementStack.peek().compareTo(minStack.peek()) == 0) {
            minStack.pop();
        }
        return elementStack.pop();
    }

    public T peek(){
        return elementStack.peek();
    }

    public boolean isEmpty(){
        return elementStack.isEmpty();
    }

    public boolean isMinStackEmpty(){
        return minStack.isEmpty();
    }

    public T getMinimum(){
        return minStack.isEmpty()? null : minStack.peek();
    }

    @Override
    public String toString(){
        return "[" + "mainList " + elementStack.toString() + "\nminStack " + minStack.toString() + "]";
    }

    public int getSize(){
        return elementStack.getSize();
    }

}
public class MinimumValueInStack {

    public static void main(String[] args) {
        Random random = new Random();
        AdvanceStack<Integer> advanceStack = new AdvanceStack<>();
        IntStream.range(0, 10).forEach(x -> advanceStack.push(random.nextInt(100)));
        advanceStack.push(advanceStack.getMinimum());

        System.out.println(advanceStack);
        while(advanceStack.getSize() > 0){
            System.out.println("element is " + advanceStack.peek() + "\nmin element is " + advanceStack.getMinimum());
            advanceStack.pop();
        }
    }
}
