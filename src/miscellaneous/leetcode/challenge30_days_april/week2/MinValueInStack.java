package miscellaneous.leetcode.challenge30_days_april.week2;

import java.util.LinkedList;

/**
 *  Week 2 day 3
 */
class MinStack{
    LinkedList<Integer> main;
    LinkedList<Integer> min;
    public MinStack(){
        main = new LinkedList<>();
        min = new LinkedList<>();
    }

    public void push(int x){
        if(min.isEmpty() || min.peek().compareTo(x) >= 0){
            min.push(x);
        }
        main.push(x);
    }

    public Integer pop(){
        if(main.isEmpty() || min.isEmpty()) throw new RuntimeException("stacks are empty");
        if(main.peek().compareTo(min.peek()) == 0) min.pop();
        return main.pop();
    }

    public Integer top(){
        if(main.isEmpty()) throw new RuntimeException("main stack is empty");
        return main.peek();
    }
    public Integer getMin(){
        if(min.isEmpty()) throw new RuntimeException("stack is empty");
        return min.peek();
    }
}
public class MinValueInStack {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());// return -3
        minStack.pop();
        System.out.println(minStack.top());// return 0
        System.out.println(minStack.getMin());// return -2
    }
}
