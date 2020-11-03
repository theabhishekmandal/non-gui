package data_structures.bit_manipulation;

import java.util.Random;

public class CalculateSquareOfNumber {
    public static void main(String[] args) {
        var random = new Random();
        int n = 1 + random.nextInt(10);
        if(random.nextBoolean()) n = -n;
        System.out.println("number is " + n + " and square is = " + findSquare(n));
    }

    private static int findSquare(int n) {
        if(n < 0) n = -n;
        int res = n;
        for(int i = 0; i < n - 1; i++) res += n;
        return res;
    }
}
