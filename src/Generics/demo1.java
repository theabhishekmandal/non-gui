package Generics;
class hello<t>
{
    t ob;
    hello(t ob)
    {
        this.ob=ob;

    }
    t get()
    {
        return this.ob;
    }
}
public class demo1 {
    public static void main(String args[])
    {
        hello<Integer> ob=new hello<>(10);
        hello<String> ob2=new hello<>("hello i am abhishek");
        int ten=ob.get();
        String value=ob2.get();
        System.out.println(ten);
        System.out.println(value);


    }
}
