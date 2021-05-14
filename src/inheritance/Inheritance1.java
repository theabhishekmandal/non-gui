package inheritance;

/**
 * What is inherited in Java Inheritance.
 * -    sub-class inherits all the public, protected and default
 *      (Only if the sub-class is located in the same package as the super class) methods and fields of the super class.
 *
 * What is not inherited in Java Inheritance.
 * -    Private fields and methods of the super class are not inherited by the sub-class and can’t be accessed directly by the subclass.
 *      Constructors of the super-class are not inherited.
 *
 * -    There is a concept of constructor chaining in Java which determines in what order constructors are called in case of inheritance.
 *
 * -    Also, can only expand the access modifier of the overridden method from super class to sub class. Eg: can go from default to protected
 *      but not private
 *
 */
class A {
    protected int aObj;

    void showA() {
        System.out.println("this is a of superclass:" + aObj);
    }

    void sayHello() {
        System.out.println("hello from A");
    }
}

class B extends A {
    int bObj;

    void showB() {
        System.out.println("this is b of subclass" + bObj);
    }

    //accessing non private members of superclass a
    void showAB() {
        System.out.println("this is a of superclass and b of subclass " + aObj + " " + bObj);
    }

    @Override
    protected void sayHello() {
        System.out.println("hello from B");
    }
}

class C extends B {
    int cObj;

    void showABC() {
        System.out.println("this is a of superclass b of second superclass and c of subclass" + aObj + " " + bObj + " " + cObj);
    }

    @Override
    public void sayHello() {
        System.out.println("hello from C");
    }
}

public class Inheritance1 {
    public static void main(String[] args) {
        var a = new A();
        a.aObj++;
        a.showA();

        var b = new B();
        b.bObj++;
        b.aObj++;
        b.showB();
        b.showAB();

        var c = new C();
        c.aObj++;
        c.bObj++;
        c.cObj++;

        a.sayHello();
        b.sayHello();
        c.sayHello();
    }
}
