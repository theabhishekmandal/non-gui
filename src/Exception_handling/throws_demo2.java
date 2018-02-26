package Exception_handling;

/**
 *  This program is an example of user-defined exception class
 *  User-defined exception classes are compile time exceptions so it is necessary to declare them using throws
 */

public class throws_demo2 extends Exception{
    public static void main(String args[]) throws throws_demo2
    {
        new throws_demo2().test();
    }

    private static void test()throws throws_demo2 {
        throw new throws_demo2();
    }
}
