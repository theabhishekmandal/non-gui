package generics;


/**
 * In generics we can apply both classes and interfaces as bound types
 *
 * For eg: In the below example we have created an interface show , a class A and a class B which extends class A and implements
 *         the show interface
 *
 *         Now We have a generic class gen<T extends A&show>, basically this gen class makes an upper bound and allows those
 *         classes which are the subclasses of A and also implements the show interface.
 *
 *         Now if you try to pass the type parameter of A in the gen object then it will show compile time error because
 *         A class does not implement the show interface.
 *
 */
interface Show {
    void showValue();
}

class A {
    public void display() {
        System.out.println("i am in A class in display method");
    }
}

class B extends A implements Show {

    @Override
    public void display() {
        super.display();
        System.out.println("i am in subclass of A in display method");
    }
    public void showValue() {
        System.out.println("Implementing interface showValue");
    }
}

class Gen<T extends A & Show> {
    private final T ob;
    Gen(T ob) {
        this.ob = ob;
    }
    public void genDisplay() {
        this.ob.display();
    }
    public void genShowValue(){
        this.ob.showValue();
    }
}

class ExtendingClassImplementingInterfaceDemo4 {
    public static void main(String[] args) {
       // gen<A> arr = new gen<>(new A()); this will not work because it does not implement the show interface
        Gen<B> arr1 = new Gen<>(new B());
        arr1.genDisplay();
        arr1.genShowValue();
    }
}