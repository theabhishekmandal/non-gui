package Generics;

/**
 * The below code is an example of how to create a generic method inside a non-generic class
 * here the isin() method, the type parameter are declared before the return type of the method
 *
 * <T extends Comparable<T>,V extends T> is used if we want to compare the two elements and V extends T is
 * used if we want to compare the subclass object of T
 *
 * If the types of the arguments are not similar in the method then it will show compile time error
 * thus ensuring type safety
 */

public class GenericMethodInNonGenricClassDemo9 {
    /*
    you can also right the below method definition by this:

    private static <T extends Comparable<T> boolean isin(T[] arr, T one)

    But only the same class objects will be able to use this method

    For a class which is the subclass of a class T we have to use the below method definition
    */

    private static<T extends Comparable<T>, V extends T> boolean isin(T[] arr,V one)
    {
     for(int i = 0 ; i < arr.length; i++)
     {
         if(one.equals(arr[i]))
             return true;
     }
        return false;
    }
    public static void main(String args[])
    {
        Integer nums[]={1, 2, 3, 4, 5};

        if(isin(nums,2))
            System.out.println("2 is in nums");

        if(!isin(nums,7))
            System.out.println("7 is not in nums");

        String strs[] = {"one","two","three","four","five"};

        if(isin(strs,"two"))
            System.out.println("two is in strs");

        if(!isin(strs,"seven"))
            System.out.println("seven is not in strs");

        //This will show compile time error
        //System.out.println(isin(nums,"hello"));
    }
}
