package Inheritence;

class A
{
    protected int a;
    void showA()
    {
        System.out.println("this is a of superclass:"+a);
    }
    void sayhello()
    {
        System.out.println("hello from A");
    }
}
class B extends A
{
    int b;
    void showB()
    {
        System.out.println("this is b of subclass"+b);
    }
    void showAB() //accessing non private members of superclass a
    {
        System.out.println("this is a of superclass and b of subclass "+a+" "+b);
    }
    void sayhello()
    {
        System.out.println("hello from B");
    }
}
class C extends B
{
    int c;
    void showABC()
    {
        System.out.println("this is a of superclass b of second superclass and c of subclass"+a+" "+b+" "+c);
    }
    void sayhello()
    {
        System.out.println("hello from C");
    }
}
public class Inheritence1 {
    public static void main(String args[])
    {
        A a=new A();
        a.a++;
        a.showA();
        B b=new B();
        b.b++;
        b.a++;
        b.showB();
        b.showAB();
        C c=new C();
        c.a++;
        c.b++;
        c.c++;
        a.sayhello();
        b.sayhello();
        c.sayhello();
    }
}
