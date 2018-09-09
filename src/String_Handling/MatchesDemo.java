package String_Handling;

/**
 *  matches(String regexp), this method returns true if the given string matches with the regex pattern
 */
public class MatchesDemo {
    public static void main(String[] args) {
        String num = "12345";
        System.out.println(num.matches("[0-9]+"));
    }
}
