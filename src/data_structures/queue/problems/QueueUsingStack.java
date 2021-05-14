package data_structures.queue.problems;

import data_structures.stack.stack_impl.SLLStack;

/**
 * This is an implementation of a Queue using Stack
 */
public class QueueUsingStack {
    public static void main(String[] args) {
        MyQueue<Integer> blah = new MyQueue<>();
        blah.enqueue(10);
        blah.enqueue(20);
        blah.enqueue(30);
        blah.enqueue(40);
        System.out.println(blah.peek());
        System.out.println(blah.dequeue());
    }

    static class MyQueue<T> {
        /*
        There are two Stacks one new and one old stack.
        The new Stack is used to perform enqueue operations
        While the old stack is used for finding the front element
        of the stack and also to perform the dequeue operation.
         */
        private final SLLStack<T> oldStack;
        private final SLLStack<T> newStack;

        public MyQueue() {
            this.oldStack = new SLLStack<>();
            this.newStack = new SLLStack<>();
        }

        public void enqueue(T value) {
            newStack.push(value);
        }

        public T peek() {
            /*
            if the old stack is empty then empty the elements of the new stack
            and put it in the old stack, in this way the older element will be
            at the top of the old stack.
             */
            if (oldStack.isEmpty()) {
                while (!newStack.isEmpty()) {
                    oldStack.push(newStack.pop());
                }
            }
            return oldStack.peek();
        }

        public T dequeue() {
            if (oldStack.isEmpty()) {
                while (!newStack.isEmpty()) {
                    oldStack.push(newStack.pop());
                }
            }
            return oldStack.pop();
        }
    }
}
