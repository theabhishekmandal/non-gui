package DataStructures.Queue;

import DataStructures.Queue.QueueImpl.Qll;

public class Queue{
    public static void main(String[] args){
        Qll<Integer> queue = new Qll<>();
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.enQueue(4);
        System.out.println(queue);
        queue.dQueue();
        queue.dQueue();
        System.out.println("after two dequeue operations\n" + queue);
    }
}

