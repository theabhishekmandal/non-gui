package strings_implementation;

/**
 * substring(int startIndex) method generates a substring from the given index to the string.length() - 1
 * substring(int startIndex, int endIndex) method generates a substring from the given index to the endIndex - 1
 */
public class SubstringDemo {
    public static void main(String[] args) {
        String hel = "hello world";
        System.out.println(hel.substring(4));
        System.out.println(hel.substring(4, 7));
    }
}
