package Exception_handling;
import java.util.*;
public class test1 {
    public static void main(String args[]) {
        double x = 2.0;
        double y;
        try {
            y = x / 0;
            System.out.println(y);
        } catch (Exception e) {
            System.out.println("X");
        } finally {
            System.out.println("Y");
        }
        System.out.println("Z");
    }
}