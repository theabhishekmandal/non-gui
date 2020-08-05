package dataStructures.Queue;

import dataStructures.Queue.QueueImpl.SLLQueue;

public class Queue{
    public static void main(String[] args){
        SLLQueue<Integer> queue = new SLLQueue<>();
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.enQueue(4);
        System.out.println(queue);
        queue.deQueue();
        queue.deQueue();
        System.out.println("after two dequeue operations\n" + queue);
    }
}

