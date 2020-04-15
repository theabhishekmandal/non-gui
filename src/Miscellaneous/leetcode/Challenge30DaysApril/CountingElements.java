package Miscellaneous.leetcode.Challenge30DaysApril;

import java.util.*;

public class CountingElements {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < arr.length; i++) 
            arr[i] = s.nextInt();
        solve(arr);
    }

    private static void solve(int[] arr) {
        NavigableMap<Integer, Integer> map = new TreeMap<>();
        for(int i : arr){
            if(!map.containsKey(i)) map.put(i, 0);
            map.put(i, map.get(i) + 1);
        }
        Map.Entry<Integer, Integer> previous = map.firstEntry();
        Map.Entry<Integer, Integer> curr;
        boolean firsttime = false;
        int counter = 0;
        int finalCounter = 0;
        for(Map.Entry<Integer, Integer> val : map.entrySet()){
            if(!firsttime) firsttime = true;
            else{
                curr = val;
                if(previous.getKey() + 1 == curr.getKey()){
                    counter += Math.max(curr.getValue(), previous.getValue());
                    previous = curr;
                }
                else{
                    finalCounter += counter;
                    counter = 0;
                }
            }
        }
        finalCounter += counter;
        System.out.println(finalCounter);
    }
}
