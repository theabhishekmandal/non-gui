package Generics;


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
interface show
{
    void showValue();
}
class A
{
    public void display()
    {
        System.out.println("i am in A class");
    }
}

class B extends A implements show{
    public void display()
    {
        super.display();
        System.out.println("i am in the subclass of A implementing the show interface");
    }
    public void showValue()
    {
        this.display();
    }
}

class gen<T extends A&show>
{
    T ob;
    gen(T ob)
    {
        this.ob=ob;
    }
    public  void gendisplay()
    {
        this.ob.showValue();
    }

}
class demo4
{
    public static void main(String args[])
    {
       // gen<A> arr=new gen<>(new A()); this will not work because it does not implement the show interface
        gen<B> arr1=new gen<>(new B());
        arr1.gendisplay();
    }
}