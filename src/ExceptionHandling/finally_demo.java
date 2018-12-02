package ExceptionHandling;

/**
 * This program is an example of finally keyword
 */

public class finally_demo {
    /*
     this method tells  if a try block throws an exception and there is no
     respective catch block to handle it then also the finally block will get executed

     Note:in this later the exception is handled to ensure the normal flow of the execution
     */
    static void demo1()
    {
        try
        {
            throw new RuntimeException();
        }
        finally
        {
            System.out.println("this is inside demo1 finally block");
        }
    }


    /*
     this method tells that if a try block has explicit return statement then also
     finally block will get  executed
     */
    static void demo2()
    {
        try{
            System.out.println("this is inside demo2");
            return;
        }
        finally
        {
            System.out.println("cannot explicitly return without executing this finally block");
        }
    }


    /*
     this method tells that if a try block dose'nt generates exception then also
     the finally block will get executed
     */
    static void demo3()
    {
        try {
            System.out.println("even if the try block dosen't throw exception");
        }
        finally
        {
            System.out.println("finally block of demo3 will be executed");
        }
    }
    public static void main(String args[])
    {
        try{
            demo1();
        }
        catch(Exception e)
        {
            System.out.println("Exception caught of demo1");
        }
        demo2();
        demo3();

    }
}
