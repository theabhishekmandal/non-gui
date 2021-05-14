package miscellaneous.leetcode.challenge30_days_april.week1;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Week 1 day 4
 * Given an array of numbers and zeros, move all the zeros to the right without disturbing
 * the order of other elements
 */
public class MoveZeros {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] arr = new int[n];
        Random random = new Random();
        for(int i = 0; i < arr.length; i++){
            boolean bool = random.nextBoolean();
            if(bool) arr[i] = random.nextInt(n);
            else arr[i] = 0;
        }
        System.out.println(Arrays.toString(arr));
        solve(arr);
    }

    private static void solve(int[] arr) {
        int zeros = -1;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] != 0){
                if(zeros != -1){
                    arr[zeros++] = arr[i];
                    arr[i] = 0;
                }
            }
            else if(arr[i] == 0 && zeros == -1){
                zeros = i;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
