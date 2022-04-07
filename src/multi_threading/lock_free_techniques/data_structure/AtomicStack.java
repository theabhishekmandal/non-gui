package multi_threading.lock_free_techniques.data_structure;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

public class AtomicStack<T> {
    static class StackNode<T> {
        T data;
        StackNode<T> next;
        public StackNode(T data) {
            this.data = data;
        }
    }
    private final AtomicReference<StackNode<T>> head = new AtomicReference<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    public void push(T data) {
        StackNode<T> newHead = new StackNode<>(data);

        while(true) {
            StackNode<T> currentHead = head.get();
            newHead.next = currentHead;
            if (head.compareAndSet(currentHead, newHead)) {
                break;
            } else {
                LockSupport.parkNanos(1);
            }
        }
        counter.incrementAndGet();
    }

    public T pop() {
        StackNode<T> currentHead = head.get();
        StackNode<T> newHeadNode;

        while (currentHead != null) {
            newHeadNode = currentHead.next;
            if (head.compareAndSet(currentHead, newHeadNode)) {
                break;
            } else {
                LockSupport.parkNanos(1);
                currentHead = head.get();
            }
        }

        counter.incrementAndGet();
        return currentHead != null? currentHead.data : null;
    }

    public int getCounter() {
        return counter.get();
    }
}
