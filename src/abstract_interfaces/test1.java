package abstract_interfaces;

/**
This program shows what is an interface and how do we implement it
 */

 interface interfacedemo
{
    /*
       NOTE: in an interface if you declare a variable then it is final and static implicitly
     */
    int a=1400;
    void demo();    //method definition inside the interface

}
interface goit
{
    void whatsup();
}

interface getString
{
    default String getString()
    {
        return "i am in getstring method";
    }
}

public class test1 implements interfacedemo,goit
{
     public void whatsup()
     {
         System.out.println("hello there abhishek mandal");
     }

    public void demo()
    {
        System.out.println("hello how are you");
    }

    public static void main(String args[])
    {
            System.out.println(a);
            test1 ob1=new test1();
            ob1.demo();
            System.out.println(test1.a);  //as the variable is final and static it can be accessed by the classname
            ob1.whatsup();
    }
}
