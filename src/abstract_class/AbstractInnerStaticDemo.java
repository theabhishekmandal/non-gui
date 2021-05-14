package abstract_class;

/**
 * This program is an example of an abstract class containing another class, abstract class and static abstract
 * class.
 */

abstract class Check {
    static int b = 1200;
    int a = 100;

    Check() {
        System.out.println("this is the constructor of the outer abstract class");
    }

    abstract static class Testing2 {
        static String staticMethod() {
            return "hello abhishek";
        }

        String instanceMethod() {
            return "instance method";
        }
    }

    class Testing {
        int c = 3000;

        String hello() {
            return "hello world";
        }
    }

    abstract class Testing3 {
        String helloThere() {
            return "at last i did it";
        }
    }
}

public class AbstractInnerStaticDemo extends Check {
    public static void main(String[] args) {

        var ob = new AbstractInnerStaticDemo();

        //variable of abstract class check
        System.out.println(ob.a);

        //static variable of abstract class check
        System.out.println(Check.b);

        //variable of inner class testing
        System.out.println(ob.new Testing().c);

        //instance method of inner class testing
        System.out.println(ob.new Testing().hello());

        //static method of static abstract inner class testing2
        System.out.println(Testing2.staticMethod());

        //creating object of static abstract inner class using anonymous class
        System.out.println(new Testing2() {
        }.instanceMethod());

        //creating object of non static inner class using anonymous class
        System.out.println(ob.new Testing3() {
        }.helloThere());
    }
}
