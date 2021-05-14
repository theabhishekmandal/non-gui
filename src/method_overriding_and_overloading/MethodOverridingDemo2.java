package method_overriding_and_overloading;

/**
 * This is an example of method overriding.
 *
 * 1. what happens when we use reference variable of parent class and object variable of child class.
 *
 * 2. what happens when we use reference and object variable of child class.
 */
class A {
    protected final int a = 10;

    protected void showA() {
        System.out.println("I am saying hello from the Class A");
    }

    //if you make it final then it would not get overridden
    protected void show() {
        System.out.println("this is the overridden method of class A");
    }
}

class B extends A {
    protected final int a = 20;

    public void showB() {
        System.out.println("I am saying hello from the subclass B");
    }

    @Override
    public void show() {
        System.out.println("this method will override the superclass method");
    }

    public void superShow() {
        super.show();
    }

    public int getSuperVariable() {
        return super.a;
    }
}

public class MethodOverridingDemo2 {
    public static void main(String[] args) {

        //creating a superclass reference variable but passing the  object of  subclass
        //remember the reverse is not true
        /*
         So when reference variable of parent is used and object variable of child is used then.
         1. Only overridden methods of the child class will be called.
         2. Only non-overridden methods of the parent class will be called.
         3. Only variables of the parent class will be accessible because variables cannot be overridden.
         */
        A a = new B();
        a.show();
        a.showA();
        System.out.println(a.a);

        //creating child class reference as well as the object variable.
        /*
        1. Only able to call it's methods, unless using the super keyword to call the parent's methods.
        2. Can access it's variables, unless using the super keyword to get access to parent's variables
         */
        B b = new B();
        b.show();
        b.showB();
        b.superShow();
        System.out.println(b.a);
        System.out.println(b.getSuperVariable());
    }
}
