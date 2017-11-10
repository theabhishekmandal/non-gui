package regexdemo;

import java.util.*;
import java.util.regex.*;
public class capturing_digits {
    public static void main(String args[])
    {
        Scanner s=new Scanner(System.in);
        String check=s.next();
        if(is_all_digits_same(check))
           System.out.println("yes the digits are same");
        else
            System.out.println("no the digits are not same");
    }

    private static boolean is_all_digits_same(String check) {
        Pattern pattern=Pattern.compile("(\\d)\\1*|(\\w)\\1*");
        Matcher matcher=pattern.matcher(check);
        return matcher.matches();
    }
}
