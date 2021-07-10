package enumeration_and_autoboxing;

import java.util.*;

public class AutoboxingDemo {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int in = s.nextInt(); // Auto box an int
        Integer n = in; //Auto unbox an int
        System.out.println(n);
    }
}
