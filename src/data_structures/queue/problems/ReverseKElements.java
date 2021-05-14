package data_structures.queue.problems;

import data_structures.queue.queue_impl.SLLQueue;
import data_structures.stack.stack_impl.SLLStack;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * Given a queue with number of elements and a number k. Reverse the k elements
 */

public class ReverseKElements {
    public static void main(String[] args) {
        var random = new Random();

        var size = 10;
        var k = 5;
        SLLQueue<Integer> sllQueue = new SLLQueue<>();
        IntStream.range(0, size).forEach(x -> sllQueue.enQueue(random.nextInt(20)));

        System.out.println("queue is " + sllQueue + " and k is " + k);
        reverseKElements(sllQueue, k);
        System.out.println("queue is " + sllQueue);
    }

    private static <T> void reverseKElements(SLLQueue<T> sllQueue, int k) {
        if (sllQueue == null || sllQueue.isEmpty() || k == 0) return;
        SLLStack<T> sllStack = new SLLStack<>();
        for (var i = 0; i < k; i++) {
            sllStack.push(sllQueue.deQueue());
        }
        for (var i = 0; i < k; i++) {
            sllQueue.enQueue(sllStack.pop());
        }
        for (var i = 0; i < sllQueue.getSize() - k; i++) {
            sllQueue.enQueue(sllQueue.deQueue());
        }
    }
}
