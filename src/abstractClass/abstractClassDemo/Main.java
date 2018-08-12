package abstractClass.abstractClassDemo;

/**
    this code is an example of abstract class
    remember : a abstract class can have abstract method ,non abstract method and data members
         : cannot create object of abstract class
         : abstract method has definition only and not body
         : a non abstract class cannot have abstract method because if it were so then we would be able to create
           object of non abstract class and would access that abstract method which is not implemented
 */

abstract class checkone {
    int a;

    void display() {
        System.out.println("hello from the abstract class");
    }

    abstract void show();
}


class checktwo extends checkone {

    void display() {
        super.display();
        System.out.println("hello from the subclass");
    }

    void show() {
        System.out.println("hey this method is defined in the subclass");
    }
}


public class Main {
    public static void main(String args[]) {
        checktwo ob1=new checktwo();
        ob1.display();
        ob1.show();
    }
}
