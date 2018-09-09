package String_Handling;

/**
 *  replace(char original, char replacement), this method replaces a given character with another character. It replaces
 *  all of its occurrences.
 *
 *  replace(CharSequence original, CharSequence replacement), this method replaces a given charsequence with another
 *  charSequence. It also replaces all of its occurences.
 */
public class ReplaceDemo {
    public static void main(String[] args) {
        String hel = "Hello world";
        String string = hel.replace('o', 'e');
        String stringa = hel.replace("ello", "i");
        System.out.println(string);
        System.out.println(stringa);
    }
}
