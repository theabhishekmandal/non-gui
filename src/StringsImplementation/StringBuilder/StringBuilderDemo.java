package StringsImplementation.StringBuilder;

/**
 *  There are four constructors in StringBuilder:
 *  StringBuilder();
 *  StringBuilder(int size);
 *  StringBuilder(String str);
 *  StringBuilder(CharSequence chars);
 *
 *  StringBuilder and StringBuilder are almost same. StringBuilder is thread safe whereas
 *  StringBuilder is not. StringBuilder is faster than StringBuilder.
 */
public class StringBuilderDemo {
    public static void main(String[] args) {
        StringBuilder br = new StringBuilder();
        StringBuilder br1 = new StringBuilder(12);
        StringBuilder br2 = new StringBuilder("Hello");
        StringBuilder br3 = new StringBuilder(new StringBuilder("12"));

    }
}
