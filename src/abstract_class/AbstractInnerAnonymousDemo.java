package abstract_class;

/**
 * This program is based on abstract class ,abstract inner class and anonymous class.
 * Anonymous class is used when you cannot make object of abstract classes.
 */


abstract class Hello {
    private int a = 0;

    Hello() {
        System.out.println("I am in abstract class constructor");
    }

    void instance() {
        System.out.println("I am inside the instance method of the abstract class with an instance variable a " + a);
    }

    abstract void abstractDemo();

    abstract static class Check {
        static void whatIsThis() {
            System.out.println("I am inside static abstract inner class's static method");
        }
        void whatIsThat() {
            System.out.println("I am inside static abstract class's instance method");
        }
    }

    //making a non-static abstract class inside abstract class
    abstract class AnotherOne {
        void whatTheHell() {
            System.out.println("I am inside inner abstract class's instance method");
        }
    }
}

public class AbstractInnerAnonymousDemo extends Hello {

    //overriding the abstract method in the abstract class hello
    @Override
    void abstractDemo() {
        System.out.println("I am inside the implemented abstract method of abstract class hello ");
    }

    public static void main(String[] args) {
        //this calls the hello() constructor of the abstract class hello
        var d = new AbstractInnerAnonymousDemo();

        //this calls the implemented method of the abstract method of the abstract class hello
        d.abstractDemo();

        //this calls the instance method of the abstract class hello
        d.instance();

        /*
            You can call the static abstract inner class check's  static method whatIsThis()
            by using the name of outer class hello, name then inner class name check and
            then the name of the me
         */
        //or Check.whatIsThis();
        Hello.Check.whatIsThis();

        System.out.println("Calling What is that now");

        /*
            You can call the static abstract inner class check's instance method
            by using the anonymous class because you cannot make the object
            of the abstract class directly so to make an object we
            use anonymous class and then call the instance method whatIsThat.
         */
        new AbstractInnerAnonymousDemo.Check(){}.whatIsThat();

        System.out.println("Hello initialised now calling what the hell");

        /*
            You can call the abstract inner class AnotherOne's instance method by using the object of demo1
            and then making the object of AnotherOne using anonymous class
         */
        d.new AnotherOne(){}.whatTheHell();

        System.out.println();
    }
}