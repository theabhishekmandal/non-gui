package String_Handling;

import java.util.Arrays;
import static java.lang.System.out;


/**
 *  split(String regex), this method is used to separate a string on the basis of the given regex passed.
 *  split(String regex, int max), if max < 0 then String is fully decomposed
 *                                if max == 0 then String is fully decomposed but no trailing empty Strings are included
 *                                if max > 0 if max is greater than the String[] length then it is ignored
 *                                           else the it converts the String into max number of parts, where the
 *                                           remaining parts are kept in a single string.
 */
public class StringSplitDemo {
    public static void main(String[] args) {
        String name = "hey hello Abhishek Mandal";
        out.println(Arrays.toString(name.split(" " )));
        out.println(Arrays.toString(name.split(" " , 2)));
    }
}
