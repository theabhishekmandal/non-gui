package abstractpackage;

/**
 * This program is an example of an abstract class containing another class, abstract class and static abstract
 * class.
 */

abstract class check
{
    int a=100;
    static int b=1200;

    check()
    {
        System.out.println("this is the constructor of the outer abstract class");
    }
    class testing
    {
        int c=3000;
        String hello()
        {
            return "hello world";
        }
    }


    static abstract class testing2
    {
        static String staticmethod()
        {
            return "hello abhishek";
        }
        String  instancemethod()
        {
            return "instance method";
        }
    }

    abstract class testing3
    {
        String hellothere()
        {
            return "at last i did it";
        }
    }
}
public class test2 extends check {
    public static void main(String args[])
    {
        test2 ob=new test2();
        System.out.println(ob.a);//variable of abstract class check

        System.out.println(check.b);//static variable of abstract class check

        System.out.println(ob.new testing().c);//variable of inner class testing

        System.out.println(new test2().new testing().hello());//instance method of inner class testing

        System.out.println(test2.testing2.staticmethod());//static method of static abstract inner class testing2

        System.out.println(new check.testing2(){}.instancemethod());  //creating object of static abstract inner class using anonymous class

        System.out.println(ob.new testing3() {}.hellothere());   //creating object of non static inner class using anonymous class
    }
}
