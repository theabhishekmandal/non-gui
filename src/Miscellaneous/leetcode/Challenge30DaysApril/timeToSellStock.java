package Miscellaneous.leetcode.Challenge30DaysApril;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Say you have an array prices for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
 *
 * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
 *
 */

public class timeToSellStock {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] arr = new int[n];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) arr[i] = random.nextInt(n);
        System.out.println(Arrays.toString(arr));
        solve(arr);
        simple(arr);
    }

    private static void simple(int[] arr){
        /*
            just add the profit if the current value is greater than previous one
         */
        int maxProfit = 0;
        for(int i = 1; i < arr.length; i++){
            if(arr[i] > arr[i - 1]) maxProfit += arr[i] - arr[i - 1];
        }
        System.out.println(maxProfit);
    }

    private static void solve(int[] arr) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int profit = 0;
        for (int i = 0; i < arr.length;) {
            if (stack.isEmpty() || arr[stack.peek()] <= arr[i]) {
                stack.push(i++);
            } else {
                int top = stack.pop();
                if (!stack.isEmpty()) {
                    while (stack.size() > 1) stack.pop();
                    profit += arr[top] - arr[stack.pop()];
                }
            }
        }
        if(!stack.isEmpty()){
            int top = stack.pop();
            if(stack.size() >= 1){
                while(stack.size() > 1){
                    stack.pop();
                }
                profit += arr[top] - arr[stack.pop()];
            }
        }
        System.out.println(profit);
    }
}