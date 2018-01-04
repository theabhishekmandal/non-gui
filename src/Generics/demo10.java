package Generics;

/**
 * This program is an example of generic interfaces.
 *
 * Here we have created a interface which has two method declarations which returns the max and min
 * element.
 *
 * for a interface to be implemented a class should also have the same bound.
 *
 * Also there is no need to declare bounds again for the interface while implementing
 * like this: class Myclass <T extends Comparable<T>> implements MinMax<T extends Comparable<T>>
 * this is unecessary and wrong.
 */
interface MinMax<T extends Comparable<T>>
{
    T min();
    T max();
}
class Myclass <T extends Comparable<T>> implements MinMax<T>
{
    T[] ob;
    Myclass(T[] ob)
    {
        this.ob = ob;
    }
    public T min()
    {
        T v = ob[0];
        for(int i = 0 ;i < ob.length; i++)
        {
            if(ob[i].compareTo(v) < 0)
                v = ob[i];
        }
        return v;
    }
    public T max() {
        T v = ob[0];
        for(int i = 0 ;i < ob.length; i++)
        {
            if(ob[i].compareTo(v) > 0)
                v = ob[i];
        }
        return v;
    }
}
public class demo10 {
    public static void main(String args[]) {

        String nums[] = {"hello", "how", "are", "you", ""};
        Myclass<String> ob = new Myclass<>(nums);
        System.out.println("maximum is " + ob.max());
        System.out.println("minimum is " + ob.min());

        Integer[] hel = {1, 2, 3, 4, 5};
        Myclass<Integer> ob2 = new Myclass<>(hel);
        System.out.println("maximum is " + ob2.max());
        System.out.println("minimum is " + ob2.min());

    }
}
