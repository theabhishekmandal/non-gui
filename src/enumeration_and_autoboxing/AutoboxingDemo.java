package enumeration_and_autoboxing;

import java.util.Scanner;

public class AutoboxingDemo {
    public static void main(String[] args) {
        var s = new Scanner(System.in);
        var in = s.nextInt(); // Auto box an int
        Integer n = in; //Auto unbox an int
        System.out.println(n);
    }
}
