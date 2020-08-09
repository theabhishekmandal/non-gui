package abstract_interfaces;

/**
 * This program is an implementation of nested interfaces
 */

/*
    a outer interface having another interface as a member
    both the interface having method definitions
 */
interface A {
    void aHello();
    interface B{
         void bHello();
    }
}

/*
    a class which implements the method of interface A
    and also implements the method of the inner interface by using the outer interface name
*/
class C implements A,A.B {

    public void aHello() {
        System.out.println("aHello");
    }
    public void bHello() {
        System.out.println("bHello");
    }
    interface D{
         void dHello();
    }
}
/*
    a class Nested_interface extending class C and implementing the inner interface D
    If { you don't implement the C.D interface then the dHello() method will be treated as the
         instance method of Nested_interface }
    ELSE {the void dHello method is treated as the method of the interface D}
 */

public class NestedInterfacesDemo extends C implements C.D {
    public void dHello(){
        System.out.println("hello D");
    }
    public static void main(String[] args){
        NestedInterfacesDemo ob=new NestedInterfacesDemo();
        ob.dHello();ob.aHello();ob.bHello();
    }
}
