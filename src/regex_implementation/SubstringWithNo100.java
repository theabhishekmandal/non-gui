package regex_implementation;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class SubstringWithNo100 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Pattern p = Pattern.compile("0*[1|0]*");
        Matcher m = p.matcher(s.next());
        if(m.find()) {
            System.out.println("100 is present");
        }
        else {
            System.out.println("naa");
        }
    }
}
