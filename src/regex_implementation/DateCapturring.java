package regex_implementation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.out;

/**
 * This program prints the valid date which is in the format dd\mm\yyyy.
 * Although it has some error which will be removed in further examples/
 *
 * on closely looking at it's particular ValidPhoneNumber [0-9]{2}\\\\[0-9]{2}\\\\[0-9]{4}.
 *
 * we see that in the curly brackets capture groups are given which tells that upto two digits are allowed
 * in the date and month part. And four digits are allowed in year.
 *
 * Also to find the backslash just after the digits we have used \\\\ because:
 * To match '\' in ValidPhoneNumber we use '\\'  in the ValidPhoneNumber engine because
 * '\' is a special character in ValidPhoneNumber.
 *
 * also if you do this [0-9]{2}\\[0-9]{2}\\[0-9]{4} still it is wrong because when will you put it inside the compile method
 * then java will treat '\\' as '\' and '\' will escape '[' in [0-9].
 *
 * So in order to resolve this problem we have used four backslashes '\\\\'. Now java will treat these '\\\\'
 * as '\\'  and the ValidPhoneNumber engine can now match '\' without escaping the '[' in '[0-9]'.
 *
 */
public class DateCapturring {
    public static void main(String[] args) {
        String date = "123/32/89 12\\1\\2018 06\\03\\1997";
        Pattern p = Pattern.compile("[0-9]{2}\\\\[0-9]{2}\\\\[0-9]{4}");
        Matcher m = p.matcher(date);
        if(m.find()) out.println(m.group());
    }
}
