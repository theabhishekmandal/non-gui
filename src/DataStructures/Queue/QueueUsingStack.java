package DataStructures.Queue;

import java.util.Stack;

/**
 * This is an implementation of a Queue using Stack
 */
public class QueueUsingStack {
    static class MyQueue<T>{
        /*
        There are two Stacks one new and one old stack.
        The new Stack is used to perform enqueue operations
        While the old stack is used for finding the front element
        of the stack and also to perform the dequeue operation.
         */
        Stack<T>  oldstack = new Stack<T>();
        Stack<T>  newstack = new Stack<T>();
        public void enqueue(T value){
            newstack.push(value);
        }
        public T peek(){
            /*
            if the old stack is empty then empty the elements of the new stack
            and put it in the old stack, in this way the older element will be
            at the top of the old stack.
             */
            if(oldstack.isEmpty()){
                while(!newstack.isEmpty()){
                    oldstack.push(newstack.pop());
                }
            }
            return oldstack.peek();
        }
        public T dequeue(){
            if(oldstack.isEmpty()){
                while(!newstack.isEmpty()){
                    oldstack.push(newstack.pop());
                }
            }
            return oldstack.pop();
        }
    }

    public static void main(String[] args) {
        MyQueue<Integer> blah = new MyQueue<>();
        blah.enqueue(10);
        blah.enqueue(20);
        blah.enqueue(30);
        blah.enqueue(40);
        System.out.println(blah.peek());
        System.out.println(blah.dequeue());
    }
}
