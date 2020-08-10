package miscellaneous.code_gladiator;

import java.util.Scanner;

public class DefeatVillainsPowerPuff {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        long[] arr = new long[s.nextInt()];
        for(int i = 0; i < arr.length; i++) arr[i] = s.nextLong();
        long min = Long.MAX_VALUE;
        for (long l : arr) {
            long temp = s.nextLong();
            min = Math.min(min, temp / l);
        }
        System.out.println(min);
    }
}
