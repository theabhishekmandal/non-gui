package abstract_interfaces;

/**
This program shows what is an interface and how do we implement it
 */

 interface Interface {
     // NOTE: in an interface if you declare a variable then it is final and static implicitly
    int a = 1400;
    void demo();    //method definition inside the interface
}


interface Interface2 {
    void whatsUp();
}


interface Interface3 {
    default String getString() {
        return "i am in getString method";
    }
}


public class InterfaceDemo1 implements Interface, Interface2, Interface3 {
     public void whatsUp() {
         System.out.println("hello there abhishek mandal");
     }

    public void demo() {
         System.out.println("hello how are you");
    }

    public static void main(String[] args){
            System.out.println(a);
            InterfaceDemo1 ob1=new InterfaceDemo1();
            ob1.demo();
            System.out.println(a);  //as the variable is final and static it can be accessed by the classname
            ob1.whatsUp();
    }
}
