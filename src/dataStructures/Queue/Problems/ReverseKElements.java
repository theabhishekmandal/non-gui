package dataStructures.Queue.Problems;

import dataStructures.Queue.QueueImpl.SLLQueue;
import dataStructures.Stack.StackImpl.SLLStack;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * Given a queue with number of elements and a number k. Reverse the k elements
 */

public class ReverseKElements {
    public static void main(String[] args) {
       Random random = new Random();

       int size = 10;
       int k = 5;
       SLLQueue<Integer> sllQueue = new SLLQueue<>();
       IntStream.range(0, size).forEach(x -> sllQueue.enQueue(random.nextInt(20)));

       System.out.println("queue is " + sllQueue + " and k is " + k);
       reverseKElements(sllQueue, k);
       System.out.println("queue is " + sllQueue);
    }

    private static <T> void reverseKElements(SLLQueue<T> sllQueue, int k) {
        if(sllQueue == null || sllQueue.isEmpty() || k == 0) return;
        SLLStack<T> sllStack = new SLLStack<>();
        for(int i = 0; i < k; i++) sllStack.push(sllQueue.deQueue());
        for(int i = 0; i < k; i++) sllQueue.enQueue(sllStack.pop());
        for(int i = 0; i < sllQueue.getSize() - k; i++) sllQueue.enQueue(sllQueue.deQueue());
    }
}
