package Generics;

/**
 * This program is an example of that a generic class can be a subclass of non-generic class
 */
class nongen
{
    int i;
    nongen(int i) {
        this.i = i;
    }
    int getval()
    {
        return i;
    }
}
class generic<T> extends nongen{
    T ob;
    generic(T ob,int i)
    {
        super(i);
        this.ob = ob;
    }
    int getval()
    {
        return super.getval();
    }
    T getob()
    {
        return ob;
    }
}
public class GenClassExtendingNonGenClassDemo12 {
    public static void main(String argsp[]) {
        generic<String> arr = new generic<>("hello abhishek",12);
        System.out.println(arr.getob());
        System.out.println(arr.getval());
    }
}
