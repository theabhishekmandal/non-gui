package DataStructures.Stack.Problems;

import DataStructures.Stack.Node.SLLStack;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Given an array A the span S[i] of A[i] is the maximum number of consecutive elements A[j]
 * immediately preceding A[i] and such that A[j] <= A[j + 1]?
 *
 *                  or
 *
 * The stock span problem is a financial problem where we have a series of n daily price quotes for a stock
 * and we need to calculate span of stock’s price for all n days.
 * The span Si of the stock’s price on a given day i is defined as the maximum number of consecutive days
 * just before the given day, for which the price of the stock on the current day is less than or equal to its price on the given day.
 *
 * For example, if an array of 7 days prices is given as {100, 80, 60, 70, 60, 75, 85},
 * then the span values for corresponding 7 days are {1, 1, 1, 2, 1, 4, 6}
 *
 * link: https://www.geeksforgeeks.org/the-stock-span-problem/
 *
 *
 */

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
