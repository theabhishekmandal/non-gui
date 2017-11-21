package Generics;

/**
 * Suppose you made a generic class that contains a method that returns the average of array of numbers.
 *
 * Also you want to obtain the average value of floats, doubles and integers
 *
 * Thus you want to pass the type argument which generically contains number.
 *
 *
 * For this you would do declare the class like this:  class Stats<T>, and  call the double value because all the numbers
 * Integers, Floats and Doubles inherit this method.
 *
 * But this will give error on compilation because the compiler doesn't know that you are creating a stats object using
 * numeric types only.
 *
 * So We use the java bound types to ensure that only specific types of type-parameters are allowed
 *
 * Below is the example where we find the average of Numeric values(Integer, Floats, Double).
 *
 * Also if you pass other arguments other than Numeric values then it will show compile time error
 *
 *
 * @param <T>
 */
//class Stats<T>
class Stats<T extends Number>
{
   private T[] nums;
    Stats(T[] ob)
    {
        this.nums=ob;
    }
    double average()
    {
        double ans=0.0;
        for(T i:nums)
            ans+=i.doubleValue();
        return ans/nums.length;
    }
}
public class demo3 {
    public static void main(String args[])
    {
        Integer arr[]={1,2,3,4,5};
        Stats<Integer> iob=new Stats<>(arr);
        double v=iob.average();
        System.out.println(v);

        Double dnums[]={1.1,2.2,3.3,4.4,5.5};
        Stats<Double> dob=new Stats<>(dnums);
        double w=dob.average();
        System.out.println(w);

        /* This will give error because strs is of type String cannot be applied to generic class which extends Number
        String strs[]={"1","2","3","4","5"};
        Stats<String> strob=new Stats<String>(strs);
        double x=strob.average();
        System.out.println(x);
        */


    }
}
