package abstract_interfaces.InterfaceDemo1;

/**
This program shows what is an interface and how do we implement it
 */

 interface interfacedemo{
    /*
       NOTE: in an interface if you declare a variable then it is final and static implicitly
     */
    int a=1400;
    void demo();    //method definition inside the interface

}


interface goit {
    void whatsup();
}


interface getString {
    default String getString()
    {
        return "i am in getstring method";
    }
}


public class Main implements interfacedemo,goit, getString{
     public void whatsup(){
         System.out.println("hello there abhishek mandal");
     }

    public void demo(){
        System.out.println("hello how are you");
    }

    public static void main(String args[]){
            System.out.println(a);
            Main ob1=new Main();
            ob1.demo();
            System.out.println(Main.a);  //as the variable is final and static it can be accessed by the classname
            ob1.whatsup();
    }
}
