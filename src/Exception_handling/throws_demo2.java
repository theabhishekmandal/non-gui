package Exception_handling;

public class throws_demo2 extends Exception{
    public static void main(String args[]) throws throws_demo2
    {
        new throws_demo2().test();
    }

    private static void test()throws throws_demo2 {
        throw new throws_demo2();
    }
}
