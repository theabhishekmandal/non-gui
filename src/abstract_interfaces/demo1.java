package abstract_interfaces;
/**
 * This program is based on abstract class ,abstract inner class and anonymous class.
 * Anonymous class is used when you cannot make object of some classes.
 */
abstract class hello
{
    private int a = 0;

    //can call the constructor of abstract class by extending it
    hello()
    {
        System.out.println("I am in abstract class constructor");
    }

    //instance method of abstract class
    void instance()
    {
        System.out.println("I am inside the instance method of the abstract class with an instance variable a " + a);
    }

    //making a abstract method inside abstract class
    abstract void abstractdemo();

    //making a static abstract class inside abstract class
    static abstract class check
    {

        static void whatisthis()
        {
            System.out.println("I am inside static abstract inner class's static method");
        }
        void whatisthat()
        {
            System.out.println("I am inside static abstract class's instance method");
        }

    }
    //making a non-static abstract class inside abstract class
    abstract class anotherone
    {
        void whatthehell()
        {
            System.out.println("I am inside inner abstract class's instance method");
        }

    }
}

public class demo1 extends hello {

    //overriding the abstract method in the abstract class hello
    @Override
    void abstractdemo()
    {
        System.out.println("I am inside the implemented abstract method of abstract class hello ");
    }

    public static void main(String args[])
    {
        //this calls the hello() constructor of the abstract class hello
        demo1 d = new demo1();

        //this calls the implemented method of the abstract method of the abstract class hello
        d.abstractdemo();

        //this calls the instance method of the abstract class hello
        d.instance();

        /*
            You can call the static abstract inner class check's  static method whatisthis()
            by using the name of outer class hello, name then inner class name check and
            then the name of the method
         */
        hello.check.whatisthis();  // or check.whatisthis();

        System.out.println("Calling What is that now");

        /*
            You can call the static abstract inner class check's instance method
            by using the anonymous class because you cannot make the object
            of the abstract class directly so to make an object we
            use anonymous class and then call the instance method whatisthat.
         */
        new demo1.check(){}.whatisthat();

        System.out.println("Hello initialised now calling what the hell");

        /*
            You can call the abstract inner class anotherone's instance method by using the object of demo1
            and then making the object of anotherone using anonymous class
         */
        d.new anotherone() {}.whatthehell();

        System.out.println();
    }
}