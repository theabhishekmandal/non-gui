package RegexImplementation;

/*to check for the valid email id
* */
import java.util.regex.*;
import java.util.*;
public class all_mail_id {
    public static void main(String args[])
    {
        Scanner s=new Scanner(System.in);
        //enter a number to check if it is a valid Indian mobile number
        String check=s.next();
        Pattern p=Pattern.compile("[0-9a-zA-Z][0-9a-zA-Z._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m=p.matcher(check);
        if(m.matches())
            System.out.println("the given number is a valid mail id!");
        else
            System.out.println("the given number is  NOT a mail id!");


    }
}
