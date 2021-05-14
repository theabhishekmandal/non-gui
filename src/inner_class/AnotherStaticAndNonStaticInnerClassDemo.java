package inner_class;

/**
 * you cannot access the inner class members using the outer class objects
 * to access the inner class members first :
 * 1 make outer class object in main method
 * 2 call the outer class method
 * 3 create a inner class object in the outer class method definition
 * 4 now from here you can change the values of inner class variables
 *
 * you cannot directly access outer class members using the inner class objects directly
 * to access the outer class members first:
 * 1 make a inner class object in the main method
 * 2 call the inner class method
 * 3 then in the method definition you can alter the values of outer class members
 * 4 if you directly chose to alter the values of the outerclass members using innerclass objects in the main method
 * then it would through compilation error
 *
 *
 * at last it shows that inner class objects have access to outer class members , through inner class methods
 * and outer class methods cannot access the inner class members , it can only be done through inner class objects
 */

public class AnotherStaticAndNonStaticInnerClassDemo {

    static int b = 0;
    int a = 0;

    public static void main(String[] args) {

        var ob1 = new AnotherStaticAndNonStaticInnerClassDemo();
        ob1.a++;
        AnotherStaticAndNonStaticInnerClassDemo.b++;
        System.out.println(ob1.hell());

        var ob2 = ob1.new Abc();
        ob2.hello();
        ob2.c++;

        var obj5 = new StaticClass();
        StaticClass.black++;
        obj5.changeDemo();
    }

    private void display() {
        System.out.println("this is hello from the outside");
    }

    private int hell() {
        var ob3 = new Abc();
        return ob3.c++;
    }

    static class StaticClass {
        static int black;
        int wow;

        void changeDemo() {
            wow++;
        }
    }

    class Abc {
        private int c;
        void hello() {
            a++;
            b++;
            //cannot access the members of outer class from the object of inner class in main method
            //can only access the outer class members with the help of inner class methods
            display();
        }
        void display() {
            AnotherStaticAndNonStaticInnerClassDemo.this.display();
            System.out.println("this is  hello from the inside");
        }
    }
}


