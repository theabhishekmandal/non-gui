package strings_implementation.StringBuilder;

import static java.lang.System.out;

/**
 *  delete(int startIndex, int endIndex), this deletes the sequence of chars from the given string, starting from
 *                                          startIndex to endIndex - 1
 *  deleteCharAt(int index), this deletes a character at the given location
 */
public class DeleteAndDeleteCharAtDemo {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("This is a test");
        sb.delete(4, 7);
        out.println("After delete: " + sb);
        sb.deleteCharAt(0);
        out.println("After deleteCharAt: " + sb);
    }
}
