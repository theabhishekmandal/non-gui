package miscellaneous.recursion;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class FindingPower {
    public static void main(String[] args) {

        Scanner s = new Scanner(in);
        int x = s.nextInt();
        int y = s.nextInt();
        out.println(pow(x, y));
    }

    private static int pow(int x, int y) {
        if (y == 0) {
            return 1;
        }
        if ((y & 1) == 0) {
            return pow(x, y / 2) * pow(x, y / 2);
        }
        else {
            return pow(x, y / 2) * pow(x, y / 2) * x;
        }
    }
}
