package DataStructures.Queue.Problems;

import DataStructures.Queue.QueueImpl.SLLQueue;
import DataStructures.Stack.StackImpl.SLLStack;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * Given a Queue reverse it's elements using enqueue and dequeue operations
 * Approach:
 *  Stack is used because it helps in reversing the elements
 */
public class ReverseQueue {
    public static void main(String[] args) {
        Random random = new Random();
        SLLQueue<Integer> queue = new SLLQueue<>();
        IntStream.range(0, 10).forEach(x -> queue.enQueue(random.nextInt(100)));
        System.out.println("queue before reversing " + queue);
        reverseQueue(queue);
        System.out.println("queue after reversing " + queue);
    }

    private static <T> void reverseQueue(SLLQueue<T> queue) {
        SLLStack<T> stack = new SLLStack<>();
        while(!queue.isEmpty())
            stack.push(queue.deQueue());
        while(!stack.isEmpty())
            queue.enQueue(stack.pop());
    }
}
