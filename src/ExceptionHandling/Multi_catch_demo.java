package ExceptionHandling;

/**
 * Using the multi catch feature
 * by using the boolean OR operator we separate the different exception types
 */
public class Multi_catch_demo {
    public static void main(String args[])
    {
        try {
            int a[] = {1, 2, 3};
            int b = 0 / 0;
            int c = a[3];
        }
        catch(ArithmeticException | ArrayIndexOutOfBoundsException e)
        {
            System.out.println(e);
        }
    }
}
