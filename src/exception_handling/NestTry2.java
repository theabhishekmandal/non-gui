package exception_handling;

/**
 * This code is an example of how to explicitly throw your own exception using the throw keyword
 * if we throw an exception and it is not caught by it's respective catch block then it will find for other
 * matching catch blocks . If not found then the it will be handled by default exception handler
 */

public class NestTry2 {
    public static void main(String[] args) {
        try {
            demoProc();
        }
        catch(NullPointerException e) {
            System.out.println("reCaught e" + e);
        }
    }
    private static void demoProc(){
        try{
            //here throwing your own exception rather than by Java Runtime Environment
            throw new NullPointerException();
        }
        catch(ArithmeticException e) {
            System.out.println(e);
            throw e;
        }
    }
}
