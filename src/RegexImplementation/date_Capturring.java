package RegexImplementation;
import java.util.*;
import static java.lang.System.*;
import java.util.regex.*;

/**
 * This program prints the valid date which is in the format dd\mm\yyyy.
 * Although it has some error which will be removed in further examples/
 *
 * on closely looking at it's particular regex [0-9]{2}\\\\[0-9]{2}\\\\[0-9]{4}.
 *
 * we see that in the curly brackets capture groups are given which tells that upto two digits are allowed
 * in the date and month part. And four digits are allowed in year.
 *
 * Also to find the backslash just after the digits we have used \\\\ because:
 * To match '\' in regex we use '\\'  in the regex engine because
 * '\' is a special character in regex.
 *
 * also if you do this [0-9]{2}\\[0-9]{2}\\[0-9]{4} still it is wrong because when will you put it inside the compile method
 * then java will treat '\\' as '\' and '\' will escape '[' in [0-9].
 *
 * So in order to resolve this problem we have used four backslashes '\\\\'. Now java will treat these '\\\\'
 * as '\\'  and the regex engine can now match '\' without escaping the '[' in '[0-9]'.
 *
 */
public class date_Capturring {
    public static void main(String args[])
    {
        Scanner s = new Scanner(System.in);

        String date = "123/32/89 12\\1\\2018 06\\03\\1997";

        Pattern p = Pattern.compile("[0-9]{2}\\\\[0-9]{2}\\\\[0-9]{4}");

        Matcher m = p.matcher(date);

        if(m.find()) out.println(m.group());
    }
}
