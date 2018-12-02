package ExceptionHandling;

/**
 * this program is an example of checked exceptions
 *                         :checked exceptions are those exceptions which are to be checked during compile time
 *                         :unchecked exceptions are those exceptions which are to be checked during runtime
 *
 * Also, remember that the checked exceptions are a compulsion to be either handled by using try catch block
 * or explicitly declared within that method in which it is used by using the throws clause
 */

public class Throws_demo {
    public static void main(String args[])
    {
        try{test();}
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    //HERE IT IS A CHECKED EXCEPTION SO WE DECLARE IT EXPLICITLY USING THE THROWS KEYWORD
    private static void test()throws ArithmeticException{
        throw new ArithmeticException();
        }
    }

