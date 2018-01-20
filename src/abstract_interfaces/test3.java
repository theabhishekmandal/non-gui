package abstract_interfaces;

interface hello
{
    void helloabhishek();

}

abstract class New implements hello
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
