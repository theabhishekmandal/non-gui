package DataStructures.Stack.Problems;

import DataStructures.Stack.Node.SLLStack;

/**
 * Given a array of bars, find the a rectangle that covers maximum area.
 * for example arr = [6, 2, 5, 4, 5, 1], the maximum area covered by
 * bars are [5, 4, 5] and the answer is 4 * 3 = 12 where 4 is the length of the bar
 * and 3 is number of bars that have minimum length 4.
 *
 * Approach,
 * if stack is empty or current length of the bar is >= value pointed by top of stack
 *  then push it's index
 * otherwise
 *      find the area covered by the element pointed by the top of the stack
 *          and how to do it?
 *              first pop the top index pointed by stack arr[top]
 *              if stack is not empty
 *                  now the current index - 1 will give the right most index which has
 *                  the height of element pointed by popped index
 *                  and current top of the stack index(after popping) will give leftmost
 *                  index which has the height of element pointed by popped index
 *                  so take max(currentMaximum, arr[poppedIndex] * (currentIndex - 1 - stack.peek())
 *              else stack is empty
 *                  take max(currentMaximum, arr[poppedIndex] * currentIndex)
 *      do this if the stack is still not empty
 *
 *  https://www.geeksforgeeks.org/largest-rectangle-under-histogram/
 */
public class HistogramArea {
    public static void main(String[] args) {
        solve(new int[]{6, 2, 5, 4, 5, 1, 6});
    }
    private static void solve(int[] arr){
        SLLStack<Integer> stack = new SLLStack<>();
        int maxArea = 0;
        int i = 0;
        while(i < arr.length){
            if(stack.isEmpty() || arr[stack.peek()] <= arr[i]) stack.push(i++);
            else{
                int top = stack.pop();
                maxArea = Math.max(maxArea, arr[top] * ((stack.isEmpty())? i : i - 1 - stack.peek()));
            }
        }
        while(!stack.isEmpty()){
            int top = stack.pop();
            maxArea = Math.max(maxArea, arr[top] * ((stack.isEmpty())? i : i - stack.peek() - 1));
        }
        System.out.println(maxArea);
    }
}
