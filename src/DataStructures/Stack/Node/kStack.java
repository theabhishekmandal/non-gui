package DataStructures.Stack.Node;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 *
 * Implementing K stack using single array.
 *
 * Requirements:
 *  arr     : This is generic array which will hold the stack elements
 *  next    : This is array which will allow us to store the next and previous index of all the stack
 *  top     : This array is for k number of stacks, this will hold the top of the stack index.
 *
 *  Approach:
 *      free will hold 0,
 *      Initially arr is having empty values,
 *      top will hold -1
 *      next will hold the next index, through this free variable will assign
 *      the next free index for storage.
 *
 *      push(stackNo, value):
 *          1. When pushing a value in stack, first check if it is full or not,
 *          2. get the current index from the free variable
 *          3. get the next value for free variable from next array
 *          4. set the previous index for tracking, by setting next using top
 *          5. set the current index in top and add the value in arr
 *
 *      pop(stackNo):
 *          1. check if it is empty or not
 *          2. get the current index by using top[stackNo]
 *          3. getting to previous index by using next[current]
 *          4. setting the next free index in next[]
 *          5. making the current index as free
 *
 */
public class kStack<T>{
    private Object[] arr;
    private int[] next;
    private int[] top;
    private int free;

    public kStack(int n, int k){
        this.arr = new Object[n];
        this.next = new int[n];
        this.top = new int[k];
        this.free = 0;
        Arrays.fill(top, -1);
        for(int i = 0; i < n - 1; i++)
            this.next[i] = i + 1;
        this.next[n - 1] = -1;
    }

    public void push(int stackNo, T value) {
        // checking if free is available or not
        if(isFull()) throw new StackOverflowError();

        // getting the current index for processing
        int curr = free;
        // setting the next index for processing
        free = next[curr];

        // setting the previous index
        next[curr] = top[stackNo];
        // top shows top element of the stack
        top[stackNo] = curr;

        // storing the value
        arr[curr] = value;
    }

    @SuppressWarnings("unchecked")
    public T pop(int stackNo) {
        if(isEmpty(stackNo)) throw new EmptyStackException();

        // getting current index
        int curr = top[stackNo];

        // setting the previous index
        top[stackNo] = next[curr];

        // setting the next index
        next[curr] = free;
        free = curr;

        return (T) arr[curr];
    }

    @SuppressWarnings("unchecked")
    public T peek(int stackNo){
        if(isEmpty(stackNo)) throw new EmptyStackException();
        return (T) arr[top[stackNo]];
    }

    public boolean isFull(){
        return free == -1;
    }

    public boolean isEmpty(int stackNo){
        return top[stackNo] == -1;
    }

    public String toString(int stackNo){
        if(isEmpty(stackNo)) throw new EmptyStackException();
        int temp = top[stackNo];
        StringBuilder br = new StringBuilder("[");
        while(temp != -1) {
            br.append(arr[temp]);
            temp = next[temp];
            if(temp != -1) br.append(",");
        }
        br.append("]");
        return br.toString();
    }
}
