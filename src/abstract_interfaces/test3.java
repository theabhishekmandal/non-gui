package abstract_interfaces;

/**
 * This program is an example of abstract class implementing an interface
 */

interface myname
{
    void helloabhishek();

}

abstract class New implements myname
{
    void instancemethod()
    {
        System.out.println("today is 25 of august ");
    }
}

public class test3 extends New{

    public void helloabhishek()
    {
        System.out.println("HELLO ABHISHEK");
    }
    public static void main(String args[])
    {
        test3 t=new test3();
        t.helloabhishek();
        t.instancemethod();

    }
}
