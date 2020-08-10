package inner_class;
/**
 * this program shows a inner class inside a method
 * generally we don't use it because we cannot create object of this type of inner
 * class from other place because this class is local to that method
 */
class A {
    private int x;
    A() {
        x = 10;
    }
    void hell() {
        int hello = 300;
        class B {
            private int y;
            void display() {
                System.out.println("this is outer class object" + x);
                System.out.println("this is inner class object" + y);
                //you can access the local variable of the method from the inner class
                System.out.println("this is the local variable of the method" + hello);
            }
        }
        new B().display();
    }
}
public class InsideMethodInnerclass {
    public static void main(String[] args) {
        new A().hell();
    }
}
