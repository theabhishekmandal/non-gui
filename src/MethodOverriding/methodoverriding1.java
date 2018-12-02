package MethodOverriding;

class A
{
    void showA()
    {
        System.out.println("I am saying hello from the Class A");
    }
   void show()  //if you make it final then it would not get overridden
    {
        System.out.println("this is the overridden method of class A");
    }
}
class B extends A{
    void showB()
    {
        System.out.println("I am saying hello from the subclass B");

    }
    void show()
    {
        System.out.println("this method will override the superclass method");
    }
}
public class methodoverriding1 {
    public static void main(String args[])
    {
        //creating a superclass reference variable but passing the  object of  subclass
        //remember the reverse is not true
        A a=new B();
        a.show(); //correct, the object type tells which overridden methods will be called in java
        a.showA(); //correct, the reference type of the variable tells which non-overridden methods it can call in java
        //a.showB(); // that is why this statement is wrong

    }
}
