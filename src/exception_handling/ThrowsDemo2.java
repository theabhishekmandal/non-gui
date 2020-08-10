package exception_handling;

/**
 *  This program is an example of user-defined exception class
 *  User-defined exception classes are compile time exceptions so it is necessary to declare them using throws
 */

public class ThrowsDemo2 extends Exception {
    public static void main(String[] args) throws ThrowsDemo2 {
        ThrowsDemo2.test();
    }
    private static void test() throws ThrowsDemo2 {
        throw new ThrowsDemo2();
    }
}
