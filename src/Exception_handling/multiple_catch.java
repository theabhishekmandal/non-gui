package Exception_handling;
/**
 *
 * Remember that exception subclasses must come before any of their superclasses.
 * This is because a catch statement that uses a superclass will catch exceptions of that type
 * plus any of its subclasses. Thus, a subclass would never be reached if it came after its superclass.


 * A subclass must come before its superclass in
 * a series of catch statements. If not,
 * unreachable code will be created and a
 * compile-time error will result.
 */
public class multiple_catch {
    public static void main(String args[])
    {
        try {
            int a = args.length;
            int b = 10 / a;
            int arr[]=new int[2];
            arr[10]=0;
        }
   /*
      you cannot do this Exception superclass is  before it's subclass so the next catch block is redundant
      catch(Exception e)
        {

        }
        catch(ArithmeticException e)
        {

        }
    */
   catch(ArithmeticException e)
   {
       System.out.println("divide by zero");
   }
   catch(Exception e)
   {
       e.printStackTrace();
   }

    }
}
