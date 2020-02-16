package DataStructures.Stack.Node;

import java.util.Arrays;
import java.util.EmptyStackException;

public class kStack<T>{
    private Object[] arr;
    private int[] next;
    private int[] top;
    private int size;
    private int free;

    public kStack(int n, int k){
        this.size = n;
        this.arr = new Object[this.size];
        this.next = new int[this.size];
        this.top = new int[k];
        this.free = 0;
        Arrays.fill(top, -1);
        for(int i = 0; i < this.size - 1; i++)
            this.next[i] = i + 1;
        this.next[n - 1] = -1;
    }

    public void push(int stackNo, T value) {
        if(isFull()) throw new StackOverflowError();
        int curr = free;
        free = next[curr];

        next[curr] = top[stackNo];
        top[stackNo] = curr;

        arr[curr] = value;
    }


    public T pop(int stackNo) {
        if(isEmpty(stackNo)) throw new EmptyStackException();

        int curr = top[stackNo];

        top[stackNo] = next[curr];
        next[curr] = free;
        free = curr;

        return (T)arr[curr];
    }

    public boolean isFull(){
        return free == -1;
    }

    public boolean isEmpty(int stackNo){
        return top[stackNo] == -1;
    }
}
