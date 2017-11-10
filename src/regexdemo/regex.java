package regexdemo;

/*this is a program that shows that if a given phone number is valid Indian number or not
--> first a given phone number is indian phone number if it start from 7,8,9 and the number of digits including them is 10
--> second if a given number is 11 digit then the first number should be a zero
-->third if a given number is 12 digit number then the first two digit should be 91
* */
import java.util.regex.*;
import java.util.*;
public class regex {
    public static void main(String args[])
    {
        Scanner s=new Scanner(System.in);
        //enter a number to check if it is a valid Indian mobile number
        String check=s.next();
        Pattern p=Pattern.compile("(0|91)?[7-9][0-9]{9}");
        Matcher m=p.matcher(check);
        if(m.matches())
            System.out.println("the given number is a valid Indian phone number!");
        else
            System.out.println("the given number is  NOT a valid Indian phone number!");


    }
}
