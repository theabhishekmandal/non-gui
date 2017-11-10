package Generics;

/**
 *
 * @param <T>
 * @param <V>
 *     This program is an example of two generic demo
 *     here we can use multiple parameter types in a generic type class
 */
class TwoGen<T,V>
{
    T ob;
    V ob1;
    TwoGen(T ob,V ob2)
    {
        this.ob=ob;
        this.ob1=ob2;
    }
    T getvalue1()
    {
        return this.ob;
    }
    V getvalue2()
    {
        return this.ob1;
    }
}
public class demo2 {
    public static void main(String args[])
    {
        TwoGen<Integer,String> arr=new TwoGen<>(10,"hello  i am abhishek");
        int num=arr.getvalue1();
        String value=arr.getvalue2();
        System.out.println(num+"\n"+value);
    }
}
