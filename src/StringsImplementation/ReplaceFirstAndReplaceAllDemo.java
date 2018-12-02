package StringsImplementation;

import static java.lang.System.out;

/**
 *  replaceFirst(String regex) replaces the first substring that is matched with the regex pattern
 *  replaceAll(String regex) replaces all the occurrences that is matched with the regex pattern
 */
public class ReplaceFirstAndReplaceAllDemo {
    public static void main(String[] args) {
        String hel = "hey there what are you doing here and there?";
        String newstring = hel.replaceFirst("(there)", "replaced");
        String newstring2 = hel.replaceAll("(there)", "replaced");
        out.println(newstring);
        out.println(newstring2);
    }
}
