package INNER_CLASS;

class Outer1{
    int a;
    static int c;

    static class inner1 {
        int b;
        static int d;  //we can make a static variable inside a static class
        void show(){
            Outer1 obj3=new Outer1(); //to access outer class members from static class we create objects of outer class and then access it
            System.out.println("this is outer class instance variable a "+(++obj3.a));
            System.out.println("this is outer class static variable c "+(++obj3.c));
            System.out.println("this is outer class static variable c "+(++c));  //here we call the static member of outer class directly
            System.out.println("this is outer class static variable c "+(++Outer1.c));
            System.out.println("this is inner class instance variable b "+(++b));
            System.out.println("this is inner class static variable d "+(++d));

        }
        static void hello() {
            System.out.println("hello abhishek");
        }
    }
    class inner2 {
        //static int e; we cannot make a static variable inside inner class
        int e;
        void innershow() {
            //here we can call outer class static and non static members directly

            System.out.println("this is static inner class variable e "+(++e));
            System.out.println("this is outer  class variable a "+(++a));
            System.out.println("this is outer  class static variable c "+(++c));
            System.out.println("this is outer  class static variable b "+(++new inner1().b)); //accessing another class
        }
    }
    public static void main(String args[]) {
        Outer1.inner1 obj1=new Outer1.inner1(); //one way to create static inner class object
        inner1 obj2=new inner1();               //another way to create static inner class object
        obj1.b++;                               //accessing inner class member directly
        obj1.show();
        System.out.println("this is outer class static variable c "+(++c));
        System.out.println("this is outer class static variable c "+(++Outer1.c));
        System.out.println("this is outer class static  variable c "+(++new Outer1().c));
        //how you call a instances variables of a class :by creating a object
        //similarly to create object of inner class you create object of outer class first and then
        //object of inner class

        Outer1.inner2 obj4=new Outer1().new inner2();
        obj4.innershow();
        Outer1.inner1 hel=new Outer1.inner1();
        Outer1.inner1.hello();  //calling outer class's static inner class's static method
    }
}