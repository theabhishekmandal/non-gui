package strings_implementation.StringBuilder;

import static java.lang.System.out;

/**
 * insert(int index, char[] str) is used to insert a string at a given index. Rest of the string gets shifted.
 */
public class InsertDemo {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("Abhishek Mandal");
        sb.insert(0, "Hello there ");
        out.println(sb);

    }
}
