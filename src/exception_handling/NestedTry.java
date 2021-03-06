package exception_handling;

import java.util.Scanner;

/**
 An example of nested try statements.
 * this code shows about the properties of nested try statements
 * if in a nested try statements ,if a statement generated a exception and it's respected catch block didn't handle the
 * exception then ,it will be handled by the nearest outer catch block which has it's exception declared. Otherwise it will
 * handled by the JVM's default exception handler.

 * for example
 * try {
    if i divide by 1
    try {
        int[] array=new int[1];
        array[100]=10;
    }
    catch(NullPointerException e) {
        e.printStackTrace();
    }
 }
 catch(ArithmeticException e){
    e.printStackTrace();
 }

 in this case the exception would be handled by the default exception handler

 */
class NestTry {
    public static void main(String[] args) {

        try {
            var len = new Scanner(System.in).nextInt();
            var a = 0 / len;
            solve(len);
        }
        catch(ArithmeticException e) {
            e.printStackTrace();
        }
    }

    private static void solve(int len) {
        try {
            if(len == 1){
                len = len / (len - 1);
            }
            else {
                int[] c = new int[1];
                c[99] = 2;
            }
        }
        catch(ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}
