package inner_class;

class StaticInnerClassDemo {
    static int c;
    int a;

    public static void main(String[] args) {

        //one way to create static inner class object
        Inner1 obj1 = new Inner1();

        //another way to create static inner class object
        Inner1 obj2 = new Inner1();

        obj1.b++;                               //accessing inner class member directly
        obj1.show();

        System.out.println("this is outer class static variable c " + (++c));
        System.out.println("this is outer class static variable c " + (++StaticInnerClassDemo.c));
        System.out.println("this is outer class static  variable c " + (++new StaticInnerClassDemo().c));

        //how you call a instances variables of a class :by creating a object
        //similarly to create object of inner class you create object of outer class first and then
        //object of inner class

        var obj4 = new StaticInnerClassDemo().new Inner2();
        obj4.innerShow();
        //calling outer class's static inner class's static method
        Inner1.hello();
    }

    static class Inner1 {
        //we can make a static variable inside a static class
        static int d;
        int b;

        static void hello() {
            System.out.println("hello abhishek");
        }

        void show() {

            //to access outer class members from static class we create objects of outer class and then access it
            var obj3 = new StaticInnerClassDemo();

            System.out.println("this is outer class instance variable a " + (++obj3.a));
            System.out.println("this is outer class static variable c " + (++c));
            //here we call the static member of outer class directly
            System.out.println("this is outer class static variable c " + (++c));
            System.out.println("this is outer class static variable c " + (++StaticInnerClassDemo.c));
            System.out.println("this is inner class instance variable b " + (++b));
            System.out.println("this is inner class static variable d " + (++d));

        }
    }

    class Inner2 {
        //static int e; we cannot make a static variable inside inner class
        int e;

        void innerShow() {
            //here we can call outer class static and non static members directly

            System.out.println("this is static inner class variable e " + (++e));
            System.out.println("this is outer  class variable a " + (++a));
            System.out.println("this is outer  class static variable c " + (++c));
            //accessing another class
            System.out.println("this is outer  class static variable b " + (++new Inner1().b));
        }
    }
}