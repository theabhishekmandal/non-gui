package RegexImplementation;

import java.util.*;
import java.util.regex.*;
public class CapturingDigits {
    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        String check = s.next();
        if(check.matches("(\\d)\\1*|(\\w)\\1*"))
           System.out.println("yes the digits are same");
        else
            System.out.println("no the digits are not same");
    }
}
