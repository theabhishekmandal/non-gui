package inheritance;

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
    void sayHello() {
        System.out.println("hello from B");
    }
}

class C extends B {
    int cObj;

    void showABC() {
        System.out.println("this is a of superclass b of second superclass and c of subclass" + aObj + " " + bObj + " " + cObj);
    }

    @Override
    void sayHello() {
        System.out.println("hello from C");
    }
}

public class Inheritance1 {
    public static void main(String[] args) {
        A a=new A();
        a.aObj++;
        a.showA();
        B b=new B();
        b.bObj++;
        b.aObj++;
        b.showB();
        b.showAB();
        C c=new C();
        c.aObj++;
        c.bObj++;
        c.cObj++;
        a.sayHello();
        b.sayHello();
        c.sayHello();
    }
}
