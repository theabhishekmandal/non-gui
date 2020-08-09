package data_structures.queue.problems;

import data_structures.queue.queue_impl.SLLQueue;
import data_structures.stack.stack_impl.SLLStack;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * Given a queue with n elements, add the elements to the stack such that, top of the stack contains
 * first element of the queue.
 */
public class ConvertQueueToStack {
    public static void main(String[] args) {
        Random random = new Random();

        SLLQueue<Integer> sll = new SLLQueue<>();
        IntStream.range(0, 10).forEach(x -> sll.enQueue(random.nextInt(10)));
        System.out.println("queue is " + sll);

        SLLStack<Integer> sllStack = new SLLStack<>();
        while(!sll.isEmpty()) sllStack.push(sll.deQueue());
        while(!sllStack.isEmpty()) sll.enQueue(sllStack.pop());
        while(!sll.isEmpty()) sllStack.push(sll.deQueue());

        System.out.println("stack is " + sllStack);
    }
}
