package dataStructures.Stack.Problems;

import dataStructures.Stack.StackImpl.SLLStack;

/**
 * Given a array of bars, find the a rectangle that covers maximum area.
 * for example arr = [6, 2, 5, 4, 5, 1], the maximum area covered by
 * bars are [5, 4, 5] and the answer is 4 * 3 = 12 where 4 is the length of the bar
 * and 3 is number of bars that have minimum length 4.
 *
 * Approach,
 * if stack is empty or current height of the bar is >= value pointed by top of stack
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
 *
 *                  eg:
 *                  if arr = [2, 4, 5, 6, 2], and if i = 4, then stack will contain stack = [0, 1, 2, 3]
 *                  now if after popping
 *                              stack = [0, 1, 2]
 *                              poppedIndex = 3
 *                              stack.peek = 2
 *                              i = 4
 *                              then i - 1 - stack.peek = 4 - 1  - 2 = 1
 *                                          => arr[poppedIndex] i.e arr[3] = 6 height's is of range 1
 *                  now after popping again
 *                              stack = [0, 1]
 *                              poppedIndex = 2
 *                              stack.peek = 1
 *                              i = 4
 *                              then i - 1 - stack.peek = 4 - 1 - 1 = 2
 *                                          => arr[poppedIndex] i.e arr[2] = 5 height's is of range 2
 *                  now after popping again
 *                              stack = [0]
 *                              poppedIndex = 1
 *                              stack.peek = 0
 *                              i = 4
 *                              then i - 1 - stack.peek = 4 - 1 - 0 = 3
 *                                          => arr[poppedIndex] i.e arr[1] = 4 height's is of range 3
 *                  now after popping again
 *                              stack = []
 *                              poppedIndex = 0
 *                              stack.peek is empty so left most is not required and now the current index
 *                              will give the range of the height
 *                              i = 4
 *                                          => arr[poppedIndex] i.e arr[0] = 2 height's is of range 4
 *
 *              else stack is empty
 *                  take max(currentMaximum, arr[poppedIndex] * currentIndex)
 *                  currentIndex is representing the number of elements that have minimum height
 *                  of popped Index element
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
            if(stack.isEmpty() || arr[stack.peek()] <= arr[i]) {
                stack.push(i++);
            }
            else{
                int top = stack.pop();
                int spanIndex;
                if(stack.isEmpty()) {
                    spanIndex = i;
                }
                else {
                    spanIndex = i - 1 - stack.peek();
                }
                maxArea = Math.max(maxArea, arr[top] * spanIndex);
            }
        }
        while(!stack.isEmpty()) {
            int top = stack.pop();
            int spanIndex;
            if(stack.isEmpty()) {
                spanIndex = i;
            }
            else {
                spanIndex = i - 1 - stack.peek();
            }
            maxArea = Math.max(maxArea, arr[top] * spanIndex);
        }
        System.out.println(maxArea);
    }
}
