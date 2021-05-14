package data_structures.stack;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
class StackIt {
    PrintWriter out = new PrintWriter(System.out);
    PrintWriter err = new PrintWriter(System.err);
    private int[] arr;
    private int size;
    private int top;

    StackIt() {
        this.size = 10;
        this.top = -1;
        this.arr = new int[this.size];
    }

    private void increaseCapacity(int[] a) {
        this.size = this.size * 2;
        arr = Arrays.copyOf(a,size);
    }

    public void push(int data) {
        if(top == arr.length - 1) {
            increaseCapacity(arr);
        }
        arr[++top] = data;
    }

    public void pop() {
        if(top == -1) {
            err.println("there is nothing to Delete");
            return;
        }
        out.println(arr[top--]);
    }

    public void show() {
        if(top == -1) {
            err.println("nothing to show");
            return;
        }
        for(int i = 0; i <= top; i++)
            out.print(arr[i] + " ");
    }
}
public class StackDemo {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        StackIt st = new StackIt();
        System.out.println("how many elements do you want to push");
        int t = s.nextInt();
        while(t-- > 0) {
            st.push(s.nextInt());
        }
        System.out.println("how many elements do you want to pop");
        t = s.nextInt();
        while(t-- > 0) {
            st.pop();
        }
        st.out.flush();
        st.err.flush();
        st.out.println("elements of the Queue are");
        st.show();
        st.out.flush();
        st.err.flush();
    }
}
