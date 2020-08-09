package data_structures.stack.problems;

import data_structures.stack.stack_impl.SLLStack;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * Given a stack reverse the elements using the push and pop operations.
 * for eg: stack is 5->4->3->2->1 then after reversing 1->2->3->4->5
 */
public class ReverseStackElements {
    public static void main(String[] args) {
        Random random = new Random();
        SLLStack<Integer> first = new SLLStack<>();
        IntStream.range(1, 11).forEach(x -> first.push(random.nextInt(11)));
        System.out.println("stack before reversing " + first);
        SLLStack<Integer> second = reverseStackElements(first);
        System.out.println("stack after reversing " + second);
    }

    private static <T> SLLStack<T> reverseStackElements(SLLStack<T> first){
        if(first == null || first.isEmpty()) return first;
        SLLStack<T> second = new SLLStack<>();
        while(!first.isEmpty()){
            second.push(first.pop());
        }
        return second;
    }
}
