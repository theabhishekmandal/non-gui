package DataStructures.Stack.Problems;

import DataStructures.Stack.Node.SLLStack;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class findingSpans {
    public static void main(String[] args) {
        Random random = new Random();
        List<Integer> list = IntStream.range(0, 10)
                .boxed()
                .map(x -> random.nextInt(10))
                .collect(Collectors.toList());
        System.out.println(list);
        System.out.println(getSpanOfList(list));
    }

    private static List<Integer> getSpanOfList(List<Integer> list) {
        List<Integer> spanList = new ArrayList<>();
        SLLStack<Integer> stack = new SLLStack<>();

        stack.push(0);
        spanList.add(1);

        for(int i = 1; i < list.size(); ++i){
            while(!stack.isEmpty() && list.get(stack.peek()) <= list.get(i))
                stack.pop();
            int spanValue = (stack.isEmpty())? (i + 1) : (i - stack.peek());
            spanList.add(spanValue);
            stack.push(i);
        }
        return spanList;
    }
}
