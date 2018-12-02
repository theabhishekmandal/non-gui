package StringsImplementation.StringBuilder;

/**
 *  replace(int startIndex, int endIndex, char[] str), unlike replace in the String class, this replace of StringBuilder
 *  replaces the string which is specified by indices.
 */
public class ReplaceDemo {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("This is a test");
        sb.replace(5, 7, "was");
        System.out.println(sb);

    }
}
