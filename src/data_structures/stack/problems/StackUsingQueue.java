package data_structures.stack.problems;

import data_structures.queue.queue_impl.SLLQueue;

import java.util.EmptyStackException;

/**
 * Implement stack using two queues,
 *
 * Approach, when pushing data add to the main queue always
 * when popping the data first dequeue till size - 1 elements
 * add those deque elements to temporary queue.
 *
 * swap temp queue with orig queue and return the dequeue element from temp.
 */
public class StackUsingQueue {

    static class Stack<T>{
        private SLLQueue<T> temp;
        private SLLQueue<T> orig;

        public Stack(){
            temp = new SLLQueue<>();
            orig = new SLLQueue<>();
        }

        public void push(T data){
            orig.enQueue(data);
        }

        public T peek(){
            if(orig.isEmpty()){
                throw new EmptyStackException();
            }
            return orig.getLast();
        }

        public T pop(){
            if(orig.isEmpty()) {
                throw new EmptyStackException();
            }
            int length = orig.getSize();
            int i = 0;
            while(i < length - 1){
                temp.enQueue(orig.deQueue());
                ++i;
            }
            SLLQueue<T> tempo;
            tempo = orig;
            orig = temp;
            temp = tempo;
            return temp.deQueue();
        }
        public boolean isEmpty(){
            return orig.isEmpty();
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.peek();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        while(!stack.isEmpty()){
            System.out.print(stack.pop() + ", ");
        }
    }

}
