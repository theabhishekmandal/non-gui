package DataStructures.Stack.Problems;

import DataStructures.Stack.Node.SLLStack;

import java.util.Arrays;
import java.util.Random;

/**
 * Given an array arr = [1, 5, 6, 8, 8, 8, 0, 1, 1, 0, 6, 5], remove the adjacent duplicates
 * so answer is arr = [1]
 */

public class RemoveAdjacentDuplicates {
    public static void main(String[] args) {
        Random random = new Random();

        // generating random same adjacent numbers
        int[] arr = new int[10 + random.nextInt(10)];
        for(int i = 0; i < arr.length;){
            int times = 1 + random.nextInt(3);
            int number = random.nextInt(20);
            while(i < arr.length && times > 0){
                arr[i++] = number;
                times--;
            }
        }
        System.out.println(Arrays.toString(arr));
        System.out.println(removeAdjacentNumbers(arr));
    }

    private static SLLStack<Integer> removeAdjacentNumbers(int[] arr) {
        SLLStack<Integer> stack = new SLLStack<>();
        for(int i = 0; i < arr.length;){
            if(stack.isEmpty() || stack.peek() != arr[i]){
                stack.push(arr[i]);
                i++;
            }
            else{
                while(i < arr.length && stack.peek() == arr[i]) i++;
                stack.pop();
            }
        }
        return stack;
    }
}
