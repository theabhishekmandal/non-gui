package AbstractClass.abstractInnerStatic;

/**
 * This program is an example of an abstract class containing another class, abstract class and static abstract
 * class.
 */

abstract class check
{
    int a=100;
    static int b=1200;

    check() {
        System.out.println("this is the constructor of the outer abstract class");
    }

    class testing {

        int c=3000;

        String hello() {
            return "hello world";
        }
    }

    static abstract class testing2 {

        static String staticmethod(){
            return "hello abhishek";
        }

        String  instancemethod() {
            return "instance method";
        }
    }

    abstract class testing3{

        String hellothere() {
            return "at last i did it";
        }
    }
}
public class Main extends check {
    public static void main(String args[]) {

        Main ob=new Main();

        //variable of abstract class check
        System.out.println(ob.a);

        //static variable of abstract class check
        System.out.println(check.b);

        //variable of inner class testing
        System.out.println(ob.new testing().c);

        //instance method of inner class testing
        System.out.println(ob.new testing().hello());

        //static method of static abstract inner class testing2
        System.out.println(Main.testing2.staticmethod());

        //creating object of static abstract inner class using anonymous class
        System.out.println(new check.testing2(){}.instancemethod());

        //creating object of non static inner class using anonymous class
        System.out.println(ob.new testing3() {}.hellothere());
    }
}
