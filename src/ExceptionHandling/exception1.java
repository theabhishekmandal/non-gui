package ExceptionHandling;
/**
1 :this is a program in which we explicitly throw an exception by creating an instance of exception and then throwing it
2 :if the exception is not caught by the programmer then it is automatically passed on to the default exception handler

**/
public class exception1 {
    public static void main(String args[]) {
            throw new RuntimeException();
            //the println method is unreachable
            //System.out.println("hello world");
    }
}