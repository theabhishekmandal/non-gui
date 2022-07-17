package reflection.method_object.testing_framework;

public class Test {

    public Test() {
        // do nothing
    }

    public Test(String one) {
        // do nothing
    }

    public static void beforeClass() {
        System.out.println("valid beforeClass executed");
    }

    public static void beforeClass(int one) {
        System.out.println("Invalid before Class executed");
    }

    public static void afterClass() {
        System.out.println("valid afterClass executed");
    }

    public static void afterClass(int one) {
        System.out.println("InValid afterClass executed");
    }

    public void setupTest() {
        System.out.println("Valid setup executed");
    }

    public void setupTest(String one) {
        System.out.println("Invalid setup executed");
    }

    public void test1() {
        System.out.println("test1 executed");
    }

    public boolean testInvalid() {
        System.out.println("Invalid test case executed");
        return true;
    }

    public void test2() {
        System.out.println("test2 executed");
    }

    public void test3() {
        System.out.println("test3 executed");
    }
}
