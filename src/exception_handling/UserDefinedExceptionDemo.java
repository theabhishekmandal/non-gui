package exception_handling;

/**
* 1  to create your exception first you need to inherit the Exception class
* 2  remember that if you extend Exception class and create your own exception
*    then it will be checked exception and for checked exception you directly cannot throw the exception
*    you either declare the exception either by using the throws keyword or use try catch block to handle
*    the exception
* 3  to override the toString method of the Exception class first you need
*    use public access specifier to define the method
 **/


/*
this program has a method of check()
if a number is greater than 10 then it will throw exception
 */
public class UserDefinedExceptionDemo extends Exception {
    UserDefinedExceptionDemo() {
        super("overriding the constructor for user defined exception");
    }

    static void check(int a) {
        try {
            if (a < 10) {
                throw new UserDefinedExceptionDemo();
            } else {
                System.out.println("your number is good");
            }
        } catch (UserDefinedExceptionDemo e) {
            System.err.println(e);
        }
    }

    public static void main(String[] args) throws UserDefinedExceptionDemo {
        check(9);
        check(11);
        throw new UserDefinedExceptionDemo();
    }

    @Override
    public String toString() {
        return "hey your number is not good check again";
    }
}
