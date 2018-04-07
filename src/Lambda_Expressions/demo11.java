package Lambda_Expressions;

/**
 * This is an example of METHOD REFERENCES to GENERIC METHODS OR CLASSES using Lambda Expressions.
 *
 * In the program, test is a non-generic class that contains a generic method called countMatching().
 * The method returns a count of the elements in an array that match a specified value. Notice how the generic
 * type argument is specified. For example, its first call in main(), shown here:
 *
 * count = myOp(test::<Integer>countMatching, vals, 4)
 *
 * passes the type argument integer. Notice that it occurs after the ::. This syntax can be generalized.
 * When a generic method is specified as a method reference, its type argument comes after the :: and before the
 * method name. It is important to point out, however, that explicitly specifying type argument is not required in
 * this situation.
 */
interface testfunc<T>{
    int func(T[] one, T two);
}
class test{

    // the class is not generic but the method is generic
    // this method is mapped to the interface's method.
    static <T> int countMatching(T[] vals, T v){
        int count = 0;
        for(T i: vals){
            if (i.equals(v))
                count++;
        }
        return  count;
    }
}
public class demo11 {
    static <T> int myOp(testfunc<T> arr, T[] vals, T v){
        return arr.func(vals, v); // the above generic method is mapped into this
    }
    public static void main(String args[]){
        Integer[] vals = {1, 2, 3, 4, 2, 3, 4, 4, 5};
        String[] strs = {"One", "Two", "Three", "Two"};
        int count = 0;

        count = myOp(test::<Integer>countMatching, vals, 4);
        System.out.println("vals contains " + count +" 4s");

        count = myOp(test::<String>countMatching, strs, "Two");
        System.out.println("strs contains " + count +" Twos");
    }
}
