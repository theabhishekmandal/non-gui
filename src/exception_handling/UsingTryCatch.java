package exception_handling;

public class UsingTryCatch {
    public static void main(String[] args) {
        int a = 42, d = 0;
        try {
            a = a / d;
            System.out.println("this is unreachable");
        }
        catch(ArithmeticException e) {
            System.out.println("divide by zero and i am inside catch block");
            throw new ArithmeticException("divide by zero");
        }
        System.out.println("hey i am outside catch block");
    }
}
