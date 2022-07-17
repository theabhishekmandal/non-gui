package reflection.method_object.testing_framework;

public class Main {
    public static void main(String[] args) throws Throwable {
        var testingFrameWork = new TestingFrameWork();
        testingFrameWork.runTestSuite(Test.class);
    }
}
